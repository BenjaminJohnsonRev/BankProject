package org.example.dao;

import org.example.entity.Customer;

import java.util.List;

public interface CustomerDao {
    public void insert(Customer customer);
    public Customer getCustomerByUsername(String username);
    public List<Customer> getAllCustomers();
    public void delete(String username);
}
