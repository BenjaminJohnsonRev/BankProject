package org.example.entity;

public class Employee implements User {

    String username;
    String password;
    boolean employeeStatus = true;



    @Override
    public void login(String username, String password) {

    }
}
