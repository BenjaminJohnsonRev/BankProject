package org.example.entity;

import java.util.Scanner;

public class AccountFactory {

    public static Account makeNewAccount(boolean employeeStatus){
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Enter new username: ");
        String password = scanner.nextLine();
        System.out.println("Enter new password: ");
        String passwordConfirm = scanner.nextLine();
        System.out.println("Confirm new password: ");
        if(password.equals(passwordConfirm)){

            Account account = new Account();

        }
        System.out.println("New account successfully created! Log in to access account.");
    }
}
