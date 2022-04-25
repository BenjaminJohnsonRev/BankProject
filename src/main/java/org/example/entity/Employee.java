package org.example.entity;

import java.util.Random;

public class Employee implements User {

    String username;
    String password;

    boolean employeeStatus = true;

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void login(String username, String password) {
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

    public boolean isEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(boolean employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
