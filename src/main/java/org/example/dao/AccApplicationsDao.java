package org.example.dao;

import org.example.entity.Account;

import java.util.List;


//All dao and daoimpl based heavily on Rory's book demo: https://github.com/220411-rory/demos

public interface AccApplicationsDao {
    public void insert(Account account);
    public Account getAccountByNumber(int number);
    public Account getAccountByName(String username);
    public List<Account> getAllAccounts();
    public void delete(int id);
}
