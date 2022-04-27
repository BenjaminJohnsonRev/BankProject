package org.example.entity;

public class Post {
    private int id;
    private int accountid1;
    private int accountid2;
    private double transfer;

    public Post(int id, int accountid1, int accountid2, double transfer) {
        this.id = id;
        this.accountid1 = accountid1;
        this.accountid2 = accountid2;
        this.transfer = transfer;
    }

    public Post(int accountid1, int accountid2, double transfer) {
        this.accountid1 = accountid1;
        this.accountid2 = accountid2;
        this.transfer = transfer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTransfer() {
        return transfer;
    }

    public void setTransfer(double transfer) {
        this.transfer = transfer;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", accountid1=" + accountid1 +
                ", accountid2=" + accountid2 +
                ", transfer=" + transfer +
                '}';
    }
}
