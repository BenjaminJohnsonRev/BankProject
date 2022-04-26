package org.example.services;

import org.example.dao.AccountDao;
import org.example.dao.DaoFactory;
import org.example.dao.PostDao;
import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.dao.CustomerDao;
import org.example.entity.Post;

import java.sql.SQLOutput;
import java.util.Scanner;

import static org.example.entity.AccountFactory.makeNewAccount;
import static org.example.entity.UserFactory.makeNewUser;

public class CustomerService {



    public void insertCustomer(){
        makeNewUser(false);
    }

    public void postTransfer(Customer customer){

        System.out.println("Enter the number of the account that you want to transfer money from: ");

        Account account1 = getAccountNumber();

        if(!(compareUsernames(customer, account1))){
            System.out.println("This is not one of your accounts.");
            postTransfer(customer);
        }

        System.out.println("Enter the number of the account that you want to transfer money to: ");

        Account account2 = getAccountNumber();

        System.out.println("Enter the amount you would like to transfer: ");
        Scanner scanner = new Scanner(System.in);

        double transfer = scanner.nextDouble();


        int accountid1 = account1.getAccountNumber();
        int accountid2 = account2.getAccountNumber();

        Post post = new Post(accountid1, accountid2, transfer);

        PostDao postDao = DaoFactory.getPostDao();
        postDao.insert(post);

    }

    public void postReceive(Customer customer){
        String username = customer.getUsername();
        AccountDao accountDao = DaoFactory.getAccountDao();
        accountDao.getAccountByName(username);

        System.out.println("Here are that transfers that you can receive: ");

        //TODO get ALL accounts with username into list, then iterate through list to find ALL posts with those account ids


        int accountid2 =

        PostDao postDao = DaoFactory.getPostDao();
        System.out.println(postDao.getPostsByAccountid2(accountid2));




    }

    public void applyForAccount(){
        makeNewAccount(true);
    }

    public void viewAccount(){

        System.out.println("Enter account number of the account that you want to view: ");

        System.out.println(getAccountNumber());
    }

    public void depositMoney(Customer customer){
        System.out.println("Enter account number of the account you want to deposit money into. ");

        Account account = getAccountNumber();

        if (compareUsernames(customer, account)){
            System.out.println("Enter the amount you would like to deposit: ");
            Scanner scanner = new Scanner(System.in);
            double deposit = scanner.nextDouble();
            if(deposit> 0) {
                double current = account.getBalance();
                double newBalance = current + deposit;
                account.setBalance(newBalance);
                AccountDao accountDao = DaoFactory.getAccountDao();
                accountDao.update(account);
            } else{
                System.out.println("You must deposit a positive amount.");
                depositMoney(customer);
            }
        } else {
            System.out.println("This is not one of your accounts. You must post a transfer if you want to deposit into this account. ");
        }

    }

    public void withdrawMoney(Customer customer){
        System.out.println("Enter account number of the account you want to withdraw money from. ");

        Account account = getAccountNumber();

        if (compareUsernames(customer, account)){
            System.out.println("Enter the amount you would like to deposit: ");
            Scanner scanner = new Scanner(System.in);
            double withdrawal = scanner.nextDouble();
            if(withdrawal> 0) {
                double current = account.getBalance();
                double newBalance = current - withdrawal;
                if(newBalance<0){
                    System.out.println("You cannot withdraw more money than you have in your account! ");
                    withdrawMoney(customer);
                } else {
                    account.setBalance(newBalance);
                    AccountDao accountDao = DaoFactory.getAccountDao();
                    accountDao.update(account);
                }
            } else{
                System.out.println("You must withdraw a positive amount.");
                depositMoney(customer);
            }
        } else {
            System.out.println("This is not one of your accounts.");
        }

    }

    public Account getAccountNumber(){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        AccountDao accountDao = DaoFactory.getAccountDao();
        Account account = accountDao.getAccountByNumber(number);
        return account;
    }

    public boolean compareUsernames(Customer customer, Account account){
        return customer.getUsername().equals(account.getAccUsername());
    }

}
