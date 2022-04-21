package org.example.entity;

import java.util.Scanner;

public class UserFactory {
    public static void makeNewUser(boolean employeeStatus){
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Enter new username: ");
        String password = scanner.nextLine();
        System.out.println("Enter new password: ");
        String passwordConfirm = scanner.nextLine();
        System.out.println("Confirm new password: ");

        if(password.equals(passwordConfirm)){
            if (employeeStatus){
                Employee employee = new Employee();
                System.out.println("New employee account created.");
            } else {
                Customer customer = new Customer();
                System.out.println("New customer account created.");
            }
        } else {
            System.out.println("Password does not match. ");
            makeNewUser(employeeStatus);
        }

    }
}
