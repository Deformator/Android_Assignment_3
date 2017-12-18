package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models;

/**
 * Model class for Order.
 */

public class Order {

    public enum OrderStatus {
        PENDING, PROCESSED
    }

    private int orderId;
    private String customerId;
    private String productIds;
    private String employeeId;
    private String orderDate;
    private int price;
    private String status;

    public Order(int orderId, String customerId, String productIds, String employeeId, String orderDate, int price, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productIds = productIds;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.price = price;
        this.status = status;
    }

    public Order(String customerId, String productIds, String employeeId, int price, String status) {
        this.customerId = customerId;
        this.productIds = productIds;
        this.employeeId = employeeId;
        this.price = price;
        this.status = status;
    }

    public Order(int orderId, int price) {
        this.orderId = orderId;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
