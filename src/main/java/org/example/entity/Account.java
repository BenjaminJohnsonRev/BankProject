package org.example.entity;

public class Account {

    int accountId;
    double balance;
    int userId;
    int accountNumber;

    public Account(int accountId, double balance, int userId, int accountNumber) {
        this.accountId = accountId;
        this.balance = balance;
        this.userId = userId;
        this.accountNumber = accountNumber;
    }

    public Account(int userId) {
        this.userId = userId;
    }




    //getters and setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}

