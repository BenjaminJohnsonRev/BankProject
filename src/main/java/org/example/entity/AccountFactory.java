package org.example.entity;

import org.example.dao.AccApplicationsDao;
import org.example.dao.AccountDao;
import org.example.dao.DaoFactory;

import java.util.Scanner;

public class AccountFactory {

    public static void makeNewAccount(boolean application){
        Scanner scanner = new Scanner(System.in);
        String accUsername = scanner.nextLine();
        System.out.println("Enter name of account owner: ");
        double balance = scanner.nextDouble();
        System.out.println("Enter starting balance of the account: ");

        Account account = new Account(accUsername, balance);

        if(application){
            AccApplicationsDao accApplicationsDao = DaoFactory.getAccApplicationsDao();
            accApplicationsDao.insert(account);
            System.out.println("New account application successfully created! It must be approved first.");

        } else {
            AccountDao accountDao = DaoFactory.getAccountDao();
            accountDao.insert(account);
            System.out.println("New account successfully created!.");
        }
    }
}
