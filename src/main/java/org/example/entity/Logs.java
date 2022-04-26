package org.example.entity;

public class Logs {
    private String type;
    private int accountid1;
    private int accountid2;
    private double amount;
    private String date;

    public Logs(String type, int accountid1, int accountid2, double amount, String date) {
        this.type = type;
        this.accountid1 = accountid1;
        this.accountid2 = accountid2;
        this.amount = amount;
        this.date = date;
    }

    public Logs(String type, int accountid1, int accountid2, double amount) {
        this.type = type;
        this.accountid1 = accountid1;
        this.accountid2 = accountid2;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccountid1() {
        return accountid1;
    }

    public void setAccountid1(int accountid1) {
        this.accountid1 = accountid1;
    }

    public int getAccountid2() {
        return accountid2;
    }

    public void setAccountid2(int accountid2) {
        this.accountid2 = accountid2;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "type='" + type + '\'' +
                ", accountid1=" + accountid1 +
                ", accountid2=" + accountid2 +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
