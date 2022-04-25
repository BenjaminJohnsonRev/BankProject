package org.example.dao;

import org.example.entity.Account;

import java.util.List;

public interface AccApplicationsDao {
    public void insert(Account account);
    public Account getAccountById(int id);
    public Account getAccountByNumber(int number);
    public Account getAccountByName(String username);
    public List<Account> getAllAccounts();
    public void update(Account account);
    public void delete(int id);
}
