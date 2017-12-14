package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models;

/**
 * Created by andriidamm on 2017-12-10.
 */

public class CustomerModel {

    private int customerId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String postalCode;

    public CustomerModel(int customerId, String userName, String password, String firstName, String lastName, String city, String postalCode) {
        this.customerId = customerId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.postalCode = postalCode;
    }

    public CustomerModel(String userName, String password, String firstName, String lastName, String city, String postalCode) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.postalCode = postalCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
