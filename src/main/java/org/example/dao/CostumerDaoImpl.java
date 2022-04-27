package org.example.dao;

import org.example.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CostumerDaoImpl implements CustomerDao{

    Connection connection;

    public CostumerDaoImpl() {
        // when we instantiate this class, we get the connection
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Customer customer) {
        // question marks are placeholders for the real values:
        String sql = "insert into customer (username, password) values (?, ?);";

        try {
            // if anything goes wrong here, we will catch the exception:

            // we use our connection to prepare a statement to send to the database, pass in the string that we made, as well as a flag
            // that returns the generated keys (id)
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // fill in the values with the data from our customer object:
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single customer)
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Customer getCustomerByUsername(String username) {
        String sql = "select * from customer where username = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set the id using the id that we passed in:
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            // checking, do we have a customer from this query
            if (resultSet.next()) {
                // extract out the data
                Customer customer = getCustomer(resultSet);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        // create a list of customers to store our results:
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customer;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // we don't need to set any parameters because we're getting all customers:
            ResultSet resultSet = preparedStatement.executeQuery();
            // we use a while loop because there are multiple results:
            while(resultSet.next()) {
                Customer customer = getCustomer(resultSet);
                // add customer to list of customers
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // get customer from a result set:
    public Customer getCustomer(ResultSet resultSet) {
        try {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            return new Customer(username, password);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String username){
        String sql = "delete from customer where username = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("Deletion successful!");
            }
            else {
                System.out.println("Something went wrong with the deletion!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }



    }

}
