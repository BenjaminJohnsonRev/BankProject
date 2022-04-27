package org.example.dao;

import org.example.entity.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao{

    Connection connection;

    public AccountDaoImpl() {
        // when we instantiate this class, we get the connection
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Account account) {
        // question marks are placeholders for the real values:
        String sql = "insert into account (username, accountid, balance) values (?, DEFAULT, ?);";

        try {
            // if anything goes wrong here, we will catch the exception:

            // we use our connection to prepare a statement to send to the database, pass in the string that we made, as well as a flag
            // that returns the generated keys (id)
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // fill in the values with the data from our account object:
            preparedStatement.setString(1, account.getAccUsername());
            preparedStatement.setDouble(2, account.getBalance());
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
                int accountid = resultSet.getInt(2);
                System.out.println("Generated account number is: " + accountid);
            }
            else {
                System.out.println("Something went wrong when adding the account!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertStoredProcedure(Account account) {
        // question marks are placeholders for the real values:
        String sql = "call insert_account (?::varchar , ?::numeric);";

        try {
            // if anything goes wrong here, we will catch the exception:

            // we use our connection to prepare a statement to send to the database, pass in the string that we made, as well as a flag
            // that returns the generated keys (id)
            CallableStatement preparedStatement = connection.prepareCall(sql);
            // fill in the values with the data from our account object:
            preparedStatement.setString(1, account.getAccUsername());
            preparedStatement.setDouble(2, account.getBalance());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single account)
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()) {
//                System.out.println("account added successfully!");
//                // extract the id from the result set
//                int accountid = resultSet.getInt(2);
//                System.out.println("Generated account number is: " + accountid);
//            }
//            else {
//                System.out.println("Something went wrong when adding the account!");
//            }
            System.out.println("account added successfully!");

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
    public Account getAccountByNumber(int number) {
        String sql = "select * from account where accountid = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set the id using the id that we passed in:
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            // checking, do we have a account from this query
            if (resultSet.next()) {
                // extract out the data
                Account account = getAccount(resultSet);
                return account;
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

    public List<Account> getAllAccountsByName(String username) {
        // create a list of accounts to store our results:
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from account where username = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
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

    // get account from a result set:
    public Account getAccount(ResultSet resultSet) {
        try {
            String username = resultSet.getString("username");
            int accountNumber = resultSet.getInt("accountid");
            double balance= resultSet.getDouble("balance");
            return new Account(username, accountNumber, balance);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Account account) {
        String sql = "update account set username = ?, balance = ? where accountid = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getAccUsername());
            preparedStatement.setInt(3, account.getAccountNumber());
            preparedStatement.setDouble(2, account.getBalance());
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
