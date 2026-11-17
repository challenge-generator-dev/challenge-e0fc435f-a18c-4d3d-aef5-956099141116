package com.pragma.order.features;

import com.pragma.order.adapters.dynamodb.DynamoDBOrderRepository;
import com.pragma.order.models.Order;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReadOrderScenario {

    @Mock
    private DynamoDbClient dynamoDBClient;

    private DynamoDBOrderRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        OnStage.setTheStage(new OnlineCast());
        repository = new DynamoDBOrderRepository(dynamoDBClient);
    }

    @Test
    void should_retrieve_order_from_dynamodb() {
        String orderId = "ORD-001";
        
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("orderId", AttributeValue.builder().s(orderId).build());
        item.put("customerId", AttributeValue.builder().s("CUST-001").build());
        item.put("orderDate", AttributeValue.builder().s("2024-01-15T10:30:00").build());
        item.put("status", AttributeValue.builder().s("PENDING").build());
        
        Map<String, AttributeValue> itemsMap = new HashMap<>();
        itemsMap.put("ITEM-001", AttributeValue.builder().n("2").build());
        item.put("items", AttributeValue.builder().m(itemsMap).build());
        
        item.put("totalAmount", AttributeValue.builder().n("150.00").build());

        GetItemResponse response = GetItemResponse.builder()
                .item(item)
                .build();

        when(dynamoDBClient.getItem(any(GetItemRequest.class)))
                .thenReturn(response);

        Order result = repository.findOrderById(orderId);

        assertThat(result, is(notNullValue()));
        assertThat(result.getOrderId(), equalTo(orderId));
        assertThat(result.getCustomerId(), equalTo("CUST-001"));
        assertThat(result.getStatus(), equalTo("PENDING"));
        assertThat(result.getTotalAmount(), equalTo(150.00));
        
        verify(dynamoDBClient, times(1)).getItem(any(GetItemRequest.class));
    }

    @Test
    void should_fail_when_order_does_not_exist() {
        String orderId = "NON-EXISTENT";
        
        GetItemResponse response = GetItemResponse.builder()
                .item(new HashMap<>())
                .build();

        when(dynamoDBClient.getItem(any(GetItemRequest.class)))
                .thenReturn(response);

        try {
            repository.findOrderById(orderId);
        } catch (ResourceNotFoundException e) {
            verify(dynamoDBClient, times(1)).getItem(any(GetItemRequest.class));
            return;
        }
        throw new AssertionError("Expected ResourceNotFoundException to be thrown");
    }

    @Test
    void should_fail_when_dynamodb_is_unavailable() {
        String orderId = "ORD-002";

        when(dynamoDBClient.getItem(any(GetItemRequest.class)))
                .thenThrow(DynamoDbException.builder()
                        .message("Service unavailable")
                        .build());

        try {
            repository.findOrderById(orderId);
        } catch (DynamoDbException e) {
            verify(dynamoDBClient, times(1)).getItem(any(GetItemRequest.class));
            return;
        }
        throw new AssertionError("Expected DynamoDbException to be thrown");
    }

    @Test
    void should_fail_when_order_id_is_blank() {
        try {
            repository.findOrderById("");
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).getItem(any(GetItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_fail_when_order_id_is_null() {
        try {
            repository.findOrderById(null);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).getItem(any(GetItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_handle_order_with_null_fields() {
        String orderId = "ORD-003";
        
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("orderId", AttributeValue.builder().s(orderId).build());
        item.put("customerId", AttributeValue.builder().s("CUST-003").build());
        item.put("orderDate", AttributeValue.builder().s("2024-01-15T10:30:00").build());
        item.put("status", AttributeValue.builder().s("PENDING").build());
        item.put("items", AttributeValue.builder().m(new HashMap<>()).build());
        item.put("totalAmount", AttributeValue.builder().n("0.0").build());

        GetItemResponse response = GetItemResponse.builder()
                .item(item)
                .build();

        when(dynamoDBClient.getItem(any(GetItemRequest.class)))
                .thenReturn(response);

        Order result = repository.findOrderById(orderId);

        assertThat(result, is(notNullValue()));
        assertThat(result.getOrderId(), equalTo(orderId));
        assertThat(result.getItems(), is(notNullValue()));
        assertThat(result.getTotalAmount(), equalTo(0.0));
    }
}