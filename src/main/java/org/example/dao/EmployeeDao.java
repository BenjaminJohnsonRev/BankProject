package org.example.dao;

import org.example.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    public void insert(Employee employee);
    public Employee getEmployeeByUsername(String username);
    public List<Employee> getAllEmployees();
    public void delete(String username);
}