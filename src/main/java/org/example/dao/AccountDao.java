package org.example.dao;

import org.example.entity.Account;

import java.util.List;

public interface AccountDao {
    public void insert(Account account);
    public void insertStoredProcedure(Account account);
    public Account getAccountByNumber(int number);
    public Account getAccountByName(String username);
    public List<Account> getAllAccounts();
    public List<Account> getAllAccountsByName(String username);
    public void update(Account account);
    public void delete(int number);
}
