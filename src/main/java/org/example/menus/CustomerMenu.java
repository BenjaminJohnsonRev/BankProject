package org.example.menus;

import org.example.entity.Customer;
import org.example.entity.User;
import org.example.services.CustomerService;

import java.util.Scanner;

public class CustomerMenu implements Menu {
    CustomerService customerService = new CustomerService();
    InitialMenu initialMenu = new InitialMenu();

    public void menu(User user){

        boolean loggedIn = true;
        Customer customer = (Customer) user;
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("Select an option: ");
        System.out.println("1: Apply for a new bank account with a starting balance ");
        System.out.println("2: View the balance of a specific account ");
        System.out.println("3: Make a withdrawal from a specific account ");
        System.out.println("4: Make a deposit to a specific account ");
        System.out.println("5: Accept a money transfer from another account ");
        System.out.println("6: Post a money transfer to another account ");
        System.out.println("7: Log out ");


        switch(choice) {
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
                customerService.postReceive(customer);

                break;
            case 6:
                customerService.postTransfer(customer);

                break;
            case 7:
                System.out.println("You are logged out. Have a nice day!");
                break;
            default:
                System.out.println("You must enter a number (1-7) from the menu. Enter 7 to log out.");
                break;
        }

        if(loggedIn){
            menu(customer);
        } else {
            initialMenu.iMenu();
        }
    }
}
