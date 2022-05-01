//TODO reuse this for project1


package org.example.services;

import org.example.dao.DaoFactory;

import org.example.dao.CustomerDao;
import org.example.dao.EmployeeDao;
import org.example.entity.Customer;
import org.example.entity.Employee;
import org.example.entity.User;
import org.example.menus.CustomerMenu;
import org.example.menus.EmployeeMenu;
import org.example.menus.InitialMenu;
import org.example.menus.Menu;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Login {

    String username;
    String password;

    public void login1(boolean employeeCheck){


        // could create MenuFactory
        InitialMenu initialMenu = new InitialMenu();
        CustomerMenu customerMenu = new CustomerMenu();
        EmployeeMenu employeeMenu = new EmployeeMenu();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        boolean validLogin = validateLogin(employeeCheck, username, password);

        if(validLogin){
            if(employeeCheck){
                employeeMenu.menu();
            } else{
                User customer = new Customer(username, password);
                customerMenu.menu(customer);
            }
        }
    }

    public static boolean validateLogin(boolean employeeCheck, String username, String password){

        if (employeeCheck){
            //getemployeename and getemployeepassword from employee table

            EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
            Employee employee = employeeDao.getEmployeeByUsername(username);
            //System.out.println("Here is the employee: " + employee.toString());

            if(employee.getUsername().equals(username) && employee.getPassword().equals(password)){
                return true;
            } else {
                System.out.println("Your username and password were invalid, please try again.");
                return false;
            }

        } else{
            //getcustomername and getcustomerpassword from customer table

            CustomerDao customerDao = DaoFactory.getCustomerDao();
            Customer customer = customerDao.getCustomerByUsername(username);
            //System.out.println("Here is the customer: " + customer.toString());

            if(customer.getUsername().equals(username) && customer.getPassword().equals(password)){
                return true;
            } else {
                System.out.println("Your username and password were invalid, please try again.");
                return false;
            }
        }
    }
}
