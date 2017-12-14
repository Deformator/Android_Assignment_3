package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models;

/**
 * Created by andriidamm on 2017-12-10.
 */

public class ClerkModel {

    private int employeeId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    public ClerkModel(int employeeId, String userName, String password, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ClerkModel(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
}
