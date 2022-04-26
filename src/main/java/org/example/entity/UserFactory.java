package org.example.entity;


import org.example.dao.CustomerDao;
import org.example.dao.DaoFactory;
import org.example.dao.EmployeeDaoImpl;

import java.util.Random;
import java.util.Scanner;

public class UserFactory {


    EmployeeDaoImpl employeeDao;

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
                Employee employee = new Employee(username, password);
                System.out.println("New employee account created.");

            } else {
//                boolean userIdUnavailable = false;
//                int userId;
//                do{
//                    Random r = new Random();
//                    userId = r.nextInt(10000);
//                    userIdUnavailable = checkUnavailability(userId);
//                } while (userIdUnavailable);
//
                Customer customer = new Customer(username, password);

                CustomerDao customerDao = DaoFactory.getCustomerDao();

                customerDao.insert(customer);

                System.out.println("New customer account created.");
            }
        } else {
            System.out.println("Password does not match. ");
            makeNewUser(employeeStatus);
        }

    }

}
