package org.example.menus;

import org.example.entity.Employee;
import org.example.entity.User;
import org.example.services.EmployeeService;

import java.lang.runtime.SwitchBootstraps;
import java.util.Scanner;

public class EmployeeMenu implements Menu {
    EmployeeService employeeService = new EmployeeService();
    InitialMenu initialMenu = new InitialMenu();

    public void menu(){
        boolean loggedIn = true;

        System.out.println("Select an option: ");
        System.out.println("1: View all account applications ");
        System.out.println("2: Approve account application ");
        System.out.println("3: Deny account application ");
        System.out.println("4: View all logs ");
        System.out.println("5: Log out ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                employeeService.viewAccountApplications();
                break;
            case 2:
                employeeService.confirmAccount();
                break;
            case 3:
                employeeService.rejectAccount();
                break;
            case 4:
                employeeService.viewLogs();
                break;
            case 5:
                System.out.println("You have logged out.");
                loggedIn = false;
                break;
            default:
                System.out.println("You must enter a number (1-5) from the menu. Enter 5 to log out. ");
                break;
        }

        if(loggedIn){
            menu();
        } else {
            initialMenu.iMenu();
        }

    }

    public void menu(User user){}
}
