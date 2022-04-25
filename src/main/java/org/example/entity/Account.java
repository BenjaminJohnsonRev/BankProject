package org.example.entity;

public class Account {

    String accUsername;
    int accountNumber;
    double balance;


    public Account(String accUsername, int accountNumber, double balance) {
        this.accUsername = accUsername;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccUsername() {
        return accUsername;
    }

    public void setAccUsername(String accUsername) {
        this.accUsername = accUsername;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "AccUsername='" + accUsername + '\'' +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                '}';
    }
}

