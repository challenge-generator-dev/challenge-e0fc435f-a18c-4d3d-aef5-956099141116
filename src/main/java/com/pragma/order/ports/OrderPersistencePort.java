package com.pragma.order.ports;

import com.pragma.order.models.Order;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

/**
 * Puerto que define el contrato para la persistencia de órdenes.
 * Las implementaciones de este puerto deben manejar la conexión con DynamoDB
 * y las operaciones de lectura/escritura de órdenes.
 */
public interface OrderPersistencePort {

    /**
     * Guarda una orden en DynamoDB.
     * @param order La orden a persistir.
     * @throws DynamoDbException Si ocurre un error durante la operación.
     */
    void saveOrder(Order order) throws DynamoDbException;

    /**
     * Recupera una orden por su ID.
     * @param orderId El ID de la orden a recuperar.
     * @return La orden recuperada, o null si no existe.
     * @throws DynamoDbException Si ocurre un error durante la operación.
     */
    Order findOrderById(String orderId) throws DynamoDbException;

    /**
     * Elimina una orden por su ID.
     * @param orderId El ID de la orden a eliminar.
     * @throws DynamoDbException Si ocurre un error durante la operación.
     */
    void deleteOrder(String orderId) throws DynamoDbException;
}