package org.example.menus;

import org.example.services.Login;

import java.lang.runtime.SwitchBootstraps;
import java.util.Scanner;

import static org.example.entity.UserFactory.makeNewUser;

public class InitialMenu{
    public void iMenu(){

        boolean running = true;
        Login login = new Login();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("Select an option: ");
        System.out.println("1: Log in as an existing customer ");
        System.out.println("2: Log in as an employee ");
        System.out.println("3: Create a new account ");
        System.out.println("4: Quit ");

        switch(choice){
            case 1:
                //logs in user and passes them to customer menu
                login.login1(false);
                break;
            case 2:
                //logs in user and passes them to employee menu
                login.login1(true);
                break;
            case 3:
                makeNewUser(false);
                break;
            case 4:
                System.out.println("You have closed the app.");
                running = false;
                break;
            default:
                System.out.println("You must enter a number (1-4) from the menu. Enter 4 to quit. ");
        }

        if(running){
            iMenu();
        }
    }
}
