package com.pragma.order.models;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * Modelo de dominio que representa una orden.
 * Contiene los atributos necesarios para persistir en DynamoDB.
 */
public class Order {
    private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private String status;
    private Map<String, Integer> items;
    private double totalAmount;

    public Order() {
    }

    public Order(String orderId, String customerId, LocalDateTime orderDate, String status,
                 Map<String, Integer> items, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    // Getters y setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.totalAmount, totalAmount) == 0 &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(status, order.status) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, orderDate, status, items, totalAmount);
    }
}