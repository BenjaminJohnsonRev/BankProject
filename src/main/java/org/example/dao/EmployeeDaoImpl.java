package org.example.dao;

import org.example.entity.Customer;
import org.example.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl extends EmployeeDao{

    Connection connection;

    public EmployeeDaoImpl() {
        // when we instantiate this class, we get the connection
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Employee employee) {
        // question marks are placeholders for the real values:
        String sql = "insert into employee (username, password) values (?, ?);";

        try {
            // if anything goes wrong here, we will catch the exception:

            // we use our connection to prepare a statement to send to the database, pass in the string that we made, as well as a flag
            // that returns the generated keys (id)
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // fill in the values with the data from our employee object:
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single employee)
            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("employee added successfully!");
                // first, we get the result set
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                // increment to the first element of the result set
                resultSet.next();
                // extract the id from the result set
                int id = resultSet.getInt(1);
                System.out.println("Generated id is: " + id);
            }
            else {
                System.out.println("Something went wrong when adding the employee!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        String sql = "select * from employee where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set the id using the id that we passed in:
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            // checking, do we have a employee from this query
            if (resultSet.next()) {
                // extract out the data
                Employee employee = getEmployee(resultSet);
                // probably don't need this conditional:
//                if(idData != id) {
//                    System.out.println("Something went wrong here. Id's don't match!");
//                    return null;
//                }
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        // create a list of employees to store our results:
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employee;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // we don't need to set any parameters because we're getting all employees:
            ResultSet resultSet = preparedStatement.executeQuery();
            // we use a while loop because there are multiple results:
            while(resultSet.next()) {
                Employee employee = getEmployee(resultSet);
                // add employee to list of employees
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // get employee from a result set:
    public Employee getEmployee(ResultSet resultSet) {
        try {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            return new Employee(username, password);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Employee employee) {
        String sql = "update employee set name = ?, author = ?, description = ?, year = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getAuthor());
            preparedStatement.setString(3, employee.getDescription());
            preparedStatement.setInt(4, employee.getYear());
            preparedStatement.setInt(5, employee.getId());
            int count = preparedStatement.executeUpdate();
            if(count == 1) System.out.println("Update successful!");
            else System.out.println("Something went wrong with the update!");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id){
        String sql = "delete from employee where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
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


