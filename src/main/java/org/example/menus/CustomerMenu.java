package org.example.menus;

import org.example.entity.Customer;
import org.example.entity.User;
import org.example.services.CustomerService;

import java.util.Scanner;

public class CustomerMenu implements Menu {
    CustomerService customerService = new CustomerService();

    public void menu(){
        System.out.println("Select an option: ");
        System.out.println("1: apply for a new bank account with a starting balance ");
        System.out.println("2: view the balance of a specific account ");
        System.out.println("3: make a withdrawal from a specific account ");
        System.out.println("4: make a deposit to a specific account ");
        System.out.println("5: post a money transfer to another account ");
        System.out.println("6: accept a money transfer from another account ");
    }

    public void menu(User user){

        Customer customer = (Customer) user;
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("Select an option: ");
        System.out.println("1: apply for a new bank account with a starting balance ");
        System.out.println("2: view the balance of a specific account ");
        System.out.println("3: make a withdrawal or deposit to a specific account ");
        System.out.println("4: post a money transfer to another account ");
        System.out.println("5: accept a money transfer from another account ");
        System.out.println("6: make a withdrawal or deposit to a specific account ");


        switch(choice){
            case 1:
                customerService.applyForAccount();
                break;
            case 2:
                customerService.viewAccount();
                break;
            case 3:
                customerService.withdrawMoney(customer);
                break;
            case 4:
                customerService.depositMoney(customer);
                break;
            case 5:



        }
    }

}
