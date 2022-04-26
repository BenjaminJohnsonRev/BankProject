package org.example.dao;

import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.entity.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao{
    Connection connection;

    public PostDaoImpl() {
        // when we instantiate this class, we get the connection
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Post post) {
        // question marks are placeholders for the real values:
        String sql = "insert into account (id, accountid1, accountid2, transfer) values (DEFAULT, ?, ?, ?);";

        try {
            // if anything goes wrong here, we will catch the exception:

            // we use our connection to prepare a statement to send to the database, pass in the string that we made, as well as a flag
            // that returns the generated keys (id)
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // fill in the values with the data from our account object:
            preparedStatement.setInt(1, post.getAccountid1());
            preparedStatement.setInt(2, post.getAccountid2());
            preparedStatement.setDouble(3, post.getTransfer());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single account)
            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("account added successfully!");
                // first, we get the result set
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                // increment to the first element of the result set
                resultSet.next();
                // extract the id from the result set
                int id = resultSet.getInt(1);
                System.out.println("Generated id is: " + id);
            }
            else {
                System.out.println("Something went wrong when adding the account!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Account getAccountByName(String username) {
        String sql = "select * from account where username = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set the id using the id that we passed in:
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            // checking, do we have a account from this query
            if (resultSet.next()) {
                // extract out the data
                Account account = getAccount(resultSet);
                // probably don't need this conditional:
//                if(idData != id) {
//                    System.out.println("Something went wrong here. Id's don't match!");
//                    return null;
//                }
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Post getPostByAccountid2(int accountid2) {
        String sql = "select * from account where accountid = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set the id using the id that we passed in:
            preparedStatement.setInt(1, accountid2);
            ResultSet resultSet = preparedStatement.executeQuery();
            // checking, do we have a account from this query
            if (resultSet.next()) {
                // extract out the data
                Post post = getPost(resultSet);
                return post;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        // create a list of accounts to store our results:
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from account;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // we don't need to set any parameters because we're getting all accounts:
            ResultSet resultSet = preparedStatement.executeQuery();
            // we use a while loop because there are multiple results:
            while(resultSet.next()) {
                Account account = getAccount(resultSet);
                // add account to list of accounts
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Post> getAllPosts() {
        // create a list of customers to store our results:
        List<Post> posts = new ArrayList<>();
        String sql = "select * from post;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // we don't need to set any parameters because we're getting all customers:
            ResultSet resultSet = preparedStatement.executeQuery();
            // we use a while loop because there are multiple results:
            while(resultSet.next()) {
                Post post = getPost(resultSet);
                // add customer to list of customers
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Post> getAllPostsForAccount(int accountid2) {
        // create a list of customers to store our results:
        List<Post> posts = new ArrayList<>();
        String sql = "select * from post where accountid2 = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set the id using the id that we passed in:
            preparedStatement.setInt(1, accountid2);
            ResultSet resultSet = preparedStatement.executeQuery();
            // we use a while loop because there are multiple results:
            while(resultSet.next()) {
                Post post = getPost(resultSet);
                // add customer to list of customers
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    // get account from a result set:
    public Post getPost(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            int accountid1 = resultSet.getInt("accountid1");
            int accountid2= resultSet.getInt("accountid2");
            double transfer = resultSet.getDouble("transfer");
            return new Post(accountid1, accountid2, transfer);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Account account) {
        String sql = "update account set username = ?, accountid = ?, balance = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getAccUsername());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.setDouble(3, account.getBalance());
            int count = preparedStatement.executeUpdate();
            if(count == 1) System.out.println("Update successful!");
            else System.out.println("Something went wrong with the update!");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int accountid){
        String sql = "delete from account where accountid = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accountid);
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
