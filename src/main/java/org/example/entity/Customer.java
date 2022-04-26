package org.example.entity;

import java.util.Random;

public class Customer implements User {

    private String username;
    private String password;
    private int userId;

    boolean employeeStatus = false;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Customer(String username, String password, int accountId) {
        this.username = username;
        this.password = password;
        this.userId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void login(String username, String password) {

    }
}
