package org.example.dao;

import org.example.entity.User;

import java.util.List;

public interface UserDao {
    public void insert(User user);
    public User getUserById(int id);
    public User getUserByName(String username);
    public List<User> getAllUsers();
    public void update(User user);
    public void delete(int id);
}
