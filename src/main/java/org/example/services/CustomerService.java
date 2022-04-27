package org.example.services;

import org.example.dao.*;
import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.entity.Logs;
import org.example.entity.Post;
import org.example.menus.CustomerMenu;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

import static org.example.entity.AccountFactory.makeNewAccount;
import static org.example.entity.UserFactory.makeNewUser;

public class CustomerService {

    LogsDao logsDao = DaoFactory.getLogsDao();

    public void viewMyAccounts(Customer customer){
        AccountDao accountDao = DaoFactory.getAccountDao();
        System.out.println(accountDao.getAllAccountsByName(customer.getUsername()));
    }

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
        List<Account> accounts = accountDao.getAllAccountsByName(username);

        //checks if any accounts exist for this user
        if(accounts.size() > 0) {
            System.out.println("Here are that transfers that you can receive: ");

            //iterates through list of receivable posts for each account this user owns
            for(int i = 0; i < accounts.size(); i++){
                int accountid2 = accounts.get(i).getAccountNumber();
                PostDao postDao = DaoFactory.getPostDao();
                System.out.println(postDao.getAllPostsForAccount(accountid2));
            }

            System.out.println("Enter the id of the transfer you would like to receive: ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();

            PostDao postDao = DaoFactory.getPostDao();
            Post post = postDao.getPostById(id);

            int accountNumber2 = post.getAccountid2();
            Account account2 = accountDao.getAccountByNumber(accountNumber2);

            int accountNumber1 = post.getAccountid1();
            Account account1 = accountDao.getAccountByNumber(accountNumber1);

            double transfer = post.getTransfer();

            double current = account1.getBalance();
            double newBalance = current - transfer;



            if(newBalance < 0){
                System.out.println("Cannot complete transfer, insufficient funds in poster's account. ");

            } else {
                account1.setBalance(newBalance);
                accountDao.update(account1);

                current = account2.getBalance();
                newBalance = current + transfer;
                accountDao.update(account2);

                //create log
                logsDao.insert(new Logs("Transfer", accountNumber1, accountNumber2, transfer));

                postDao.delete(id);

                System.out.println("Transfer completed! New balance: " + newBalance);
            }
        } else {
            System.out.println("There are no transfers posted to your accounts. ");

        }

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

                System.out.println("Deposit successful! New balance: " + newBalance);

                //create log
                logsDao.insert(new Logs("Deposit", account.getAccountNumber(), account.getAccountNumber(), deposit));

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
            System.out.println("Enter the amount you would like to withdraw: ");
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

                    System.out.println("Withdrawal successful! New balance: " + newBalance);

                    //create log
                    logsDao.insert(new Logs("Withdrawal", account.getAccountNumber(), account.getAccountNumber(), withdrawal));

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
