package org.example.services;

import org.example.dao.DaoFactory;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Login {

    String username;
    String password;

    public void loginS(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

    }

    public void validateLogin(){

    }
}
