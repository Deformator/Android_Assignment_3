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
    private String productNames;
    private String employeeId;
    private String orderDate;
    private int price;
    private String status;

    public Order(int orderId, String customerId, String productNames, String employeeId, String orderDate, int price, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productNames = productNames;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.price = price;
        this.status = status;
    }

    public Order(String customerId, String productNames, String employeeId, int price, String status) {
        this.customerId = customerId;
        this.productNames = productNames;
        this.employeeId = employeeId;
        this.price = price;
        this.status = status;
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

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
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
