package org.example.entity;

import junit.framework.TestCase;
import org.example.dao.AccountDao;
import org.example.dao.CustomerDao;
import org.example.dao.DaoFactory;
import org.junit.Test;

public class AccountFactoryTest extends TestCase {

    @Test
    public void testFakeAccount(){

        //create fake user
        Customer customer = new Customer("customer1", "password1");

        CustomerDao customerDao = DaoFactory.getCustomerDao();

        customerDao.insert(customer);

        //create fake account
        Account account = new Account("customer1", 999.99);

        AccountDao accountDao = DaoFactory.getAccountDao();
        accountDao.insert(account);
        System.out.println("New account successfully created!.");

        Account account2 = accountDao.getAccountByName("customer1");

        assertTrue(account2.getBalance() == 999.99);

        customerDao.delete("customer1");

        accountDao.delete(account2.getAccountNumber());
    }
}