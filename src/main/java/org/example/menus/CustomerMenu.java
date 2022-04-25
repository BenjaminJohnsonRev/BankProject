package org.example.menus;

public class CustomerMenu implements Menu {
    public void menu(){
        System.out.println("Select an option: ");
        System.out.println("1: apply for a new bank account with a starting balance ");
        System.out.println("2: view the balance of a specific account ");
        System.out.println("3: make a withdrawal or deposit to a specific account ");
        System.out.println("4: post a money transfer to another account ");
        System.out.println("5: accept a money transfer from another account ");
        System.out.println("6: make a withdrawal or deposit to a specific account ");
    }
}
