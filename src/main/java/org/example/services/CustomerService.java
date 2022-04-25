package org.example.services;

import org.example.dao.AccountDao;
import org.example.dao.DaoFactory;
import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.dao.CustomerDao;

import java.util.Scanner;

import static org.example.entity.AccountFactory.makeNewAccount;
import static org.example.entity.UserFactory.makeNewUser;

public class CustomerService {



    public void insertCustomer(){
        makeNewUser(false);
    }

    public void postTransfer(Customer customer){}

    public void postReceive(Customer customer){}

    public void applyForAccount(){
        makeNewAccount(true);
    }

    public void viewAccount(){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println("Enter account number of the account that you want to view: ");

        AccountDao accountDao = DaoFactory.getAccountDao();
        Account account = accountDao.getAccountByNumber(number);
    }

    public void depositMoney(Customer customer){
        //TODO in order to validate this is their account, pass in customer object created at login.
    }

    public void withdrawMoney(Customer customer){}

}
