// TODO(reto): archivo stubbeado; implementa los cuerpos.
package com.pragma.order.adapters.dynamodb;

import com.pragma.order.models.Order;
import com.pragma.order.ports.OrderPersistencePort;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DynamoDBOrderRepository implements OrderPersistencePort {

    private static final String TABLE_NAME = "orders";
    private final DynamoDbClient dynamoDBClient;

    public DynamoDBOrderRepository(DynamoDbClient dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
    }

    @Override
    public void saveOrder(Order order) throws DynamoDbException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getOrderId() == null || order.getOrderId().isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }
        if (order.getCustomerId() == null || order.getCustomerId().isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        try {
            PutItemRequest request = PutItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .item(toItem(order))
                    .build();
            dynamoDBClient.putItem(request);
        } catch (DynamoDbException e) {
            throw DynamoDbException.builder()
                    .message("Failed to persist order: " + order.getOrderId())
                    .cause(e)
                    .build();
        }
    }

    @Override
    public Order findOrderById(String orderId) throws DynamoDbException {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }

        try {
            GetItemRequest request = GetItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                    .build();

            GetItemResponse response = dynamoDBClient.getItem(request);

            if (!response.hasItem() || response.item().isEmpty()) {
                throw ResourceNotFoundException.builder()
                        .message("Order not found with ID: " + orderId)
                        .build();
            }

            return fromItem(response.item());
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (DynamoDbException e) {
            throw DynamoDbException.builder()
                    .message("Failed to retrieve order: " + orderId)
                    .cause(e)
                    .build();
        }
    }

    @Override
    public void deleteOrder(String orderId) throws DynamoDbException {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }

        try {
            DeleteItemRequest request = DeleteItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                    .build();
            dynamoDBClient.deleteItem(request);
        } catch (DynamoDbException e) {
            throw DynamoDbException.builder()
                    .message("Failed to delete order: " + orderId)
                    .cause(e)
                    .build();
        }
    }

    public Map<String, AttributeValue> toItem(Order order) {
      throw new UnsupportedOperationException("TODO(reto): toItem");
    }

    public Order fromItem(Map<String, AttributeValue> item) {
      throw new UnsupportedOperationException("TODO(reto): fromItem");
    }
}