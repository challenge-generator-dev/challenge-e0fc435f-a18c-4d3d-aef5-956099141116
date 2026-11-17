package com.pragma.order.features;

import com.pragma.order.adapters.dynamodb.DynamoDBOrderRepository;
import com.pragma.order.models.Order;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WriteOrderScenario {

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
    void should_persist_order_in_dynamodb() {
        Order order = new Order(
                "ORD-001",
                "CUST-001",
                LocalDateTime.now(),
                "PENDING",
                Map.of("ITEM-001", 2, "ITEM-002", 1),
                150.00
        );

        when(dynamoDBClient.putItem(any(PutItemRequest.class)))
                .thenReturn(PutItemResponse.builder().build());

        repository.saveOrder(order);

        verify(dynamoDBClient, times(1)).putItem(argThat((PutItemRequest request) -> 
                "orders".equals(request.tableName()) &&
                "ORD-001".equals(request.item().get("orderId").s()) &&
                "CUST-001".equals(request.item().get("customerId").s()) &&
                "PENDING".equals(request.item().get("status").s())
        ));
    }

    @Test
    void should_fail_when_dynamodb_is_unavailable() {
        Order order = new Order(
                "ORD-002",
                "CUST-002",
                LocalDateTime.now(),
                "PENDING",
                Map.of("ITEM-001", 1),
                75.00
        );

        when(dynamoDBClient.putItem(any(PutItemRequest.class)))
                .thenThrow(DynamoDbException.builder()
                        .message("Connection refused")
                        .build());

        try {
            repository.saveOrder(order);
        } catch (DynamoDbException e) {
            verify(dynamoDBClient, times(1)).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected DynamoDbException to be thrown");
    }

    @Test
    void should_fail_when_order_is_invalid() {
        Order orderWithNullId = new Order(
                null,
                "CUST-003",
                LocalDateTime.now(),
                "PENDING",
                Map.of("ITEM-001", 1),
                50.00
        );

        try {
            repository.saveOrder(orderWithNullId);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_fail_when_customer_id_is_blank() {
        Order orderWithBlankCustomer = new Order(
                "ORD-004",
                "",
                LocalDateTime.now(),
                "PENDING",
                Map.of("ITEM-001", 1),
                50.00
        );

        try {
            repository.saveOrder(orderWithBlankCustomer);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_fail_when_status_is_null() {
        Order orderWithNullStatus = new Order(
                "ORD-005",
                "CUST-005",
                LocalDateTime.now(),
                null,
                Map.of("ITEM-001", 1),
                50.00
        );

        try {
            repository.saveOrder(orderWithNullStatus);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }

    @Test
    void should_fail_when_order_is_null() {
        try {
            repository.saveOrder(null);
        } catch (IllegalArgumentException e) {
            verify(dynamoDBClient, never()).putItem(any(PutItemRequest.class));
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException to be thrown");
    }
}