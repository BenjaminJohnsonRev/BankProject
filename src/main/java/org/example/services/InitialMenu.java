package org.example.services;

public class InitialMenu implements Menu{
    public void menu(){
        System.out.println("Select an option: ");
        System.out.println("1: Log in as an existing customer ");
        System.out.println("2: Log in as an employee ");
        System.out.println("3: Create a new account ");
    }
}
