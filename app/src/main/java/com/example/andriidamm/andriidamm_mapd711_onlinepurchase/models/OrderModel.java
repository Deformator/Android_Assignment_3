package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models;

/**
 * Created by andriidamm on 2017-12-10.
 */

public class OrderModel {

    private int orderId;
    private int customerId;
    private int productId;
    private int employeeId;
    private String orderDate;
    private String status;

    public OrderModel(int orderId, int customerId, int productId, int employeeId, String orderDate, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
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
