package org.example.menus;

import org.example.services.Login;

import java.lang.runtime.SwitchBootstraps;
import java.util.Scanner;

import static org.example.entity.UserFactory.makeNewUser;

public class InitialMenu implements Menu {
    public void menu(){
        Login login = new Login();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("Select an option: ");
        System.out.println("1: Log in as an existing customer ");
        System.out.println("2: Log in as an employee ");
        System.out.println("3: Create a new account ");

        switch(choice){
            case 1:
                login.login1(false);
                break;
            case 2:
                login.login1(true);
                break;
            case 3:
                makeNewUser(false);
                break;

        }

    }
}
