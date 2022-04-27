package org.example.entity;

import junit.framework.TestCase;
import org.example.dao.CustomerDao;
import org.example.dao.DaoFactory;
import org.example.dao.EmployeeDao;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class UserFactoryTest extends TestCase {

    @Test
    public void testNewCustomer(){
        //username: customer1
        //password: password1

        Customer customer = new Customer("customer1", "password1");

        CustomerDao customerDao = DaoFactory.getCustomerDao();

        customerDao.insert(customer);

        System.out.println("New customer account created. Log in to continue. ");

        Customer customer2 = customerDao.getCustomerByUsername("customer1");

        assertTrue(customer2.getUsername().equals("customer1") && customer2.getPassword().equals("password1"));

        customerDao.delete("customer1");
    }
}