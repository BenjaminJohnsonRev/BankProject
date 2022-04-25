package org.example.menus;

public class EmployeeMenu implements Menu {
    public void menu(){
        System.out.println("Select an option: ");
        System.out.println("1: apply for a new bank account with a starting balance ");
        System.out.println("2: view the balance of a specific account ");
        System.out.println("3: make a withdrawal or deposit to a specific account ");
    }
}
