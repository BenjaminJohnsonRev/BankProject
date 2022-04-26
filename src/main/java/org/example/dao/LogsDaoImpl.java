package org.example.dao;

import org.example.entity.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogsDaoImpl implements LogsDao{

    Connection connection;

    public LogsDaoImpl() {
        // when we instantiate this class, we get the connection
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Logs logs) {
        // question marks are placeholders for the real values:
        String sql = "insert into logs (type, accountid1, accountid2, amount, date) values (?, ?, ?, ?, DEFAULT);";

        try {
            // if anything goes wrong here, we will catch the exception:

            // we use our connection to prepare a statement to send to the database, pass in the string that we made, as well as a flag
            // that returns the generated keys (id)
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // fill in the values with the data from our logs object:
            preparedStatement.setString(1, logs.getType());
            preparedStatement.setInt(2, logs.getAccountid1());
            preparedStatement.setInt(3, logs.getAccountid2());
            preparedStatement.setDouble(4, logs.getAmount());
            // now that our statement is prepared, we can execute it:
            // count is how many rows are affected (optimally we would have 1, we are inserting a single logs)
            int count = preparedStatement.executeUpdate();
            if(count == 1) {
                System.out.println("logs added successfully!");
                // first, we get the result set
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                // increment to the first element of the result set
                resultSet.next();
                // extract the id from the result set
                int id = resultSet.getInt(1);
                System.out.println("Generated id is: " + id);
            }
            else {
                System.out.println("Something went wrong when adding the logs!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Logs getLogsByAccount1(int accountid1) {
        String sql = "select * from logs where accountid1 = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set the id using the id that we passed in:
            preparedStatement.setInt(1, accountid1);
            ResultSet resultSet = preparedStatement.executeQuery();
            // checking, do we have a logs from this query
            if (resultSet.next()) {
                // extract out the data
                Logs logs = getLogs(resultSet);
                // probably don't need this conditional:
//                if(idData != id) {
//                    System.out.println("Something went wrong here. Id's don't match!");
//                    return null;
//                }
                return logs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Logs getLogsByAccount2(int accountid2) {
        String sql = "select * from logs where accountid2 = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set the id using the id that we passed in:
            preparedStatement.setInt(1, accountid2);
            ResultSet resultSet = preparedStatement.executeQuery();
            // checking, do we have a logs from this query
            if (resultSet.next()) {
                // extract out the data
                Logs logs = getLogs(resultSet);
                // probably don't need this conditional:
//                if(idData != id) {
//                    System.out.println("Something went wrong here. Id's don't match!");
//                    return null;
//                }
                return logs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Logs getLogsByType(String type) {
        String sql = "select * from logs where type = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set the id using the id that we passed in:
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            // checking, do we have a logs from this query
            if (resultSet.next()) {
                // extract out the data
                Logs logs = getLogs(resultSet);
                // probably don't need this conditional:
//                if(idData != id) {
//                    System.out.println("Something went wrong here. Id's don't match!");
//                    return null;
//                }
                return logs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Logs> getAllLogs() {
        // create a list of logs to store our results:
        List<Logs> logs = new ArrayList<>();
        String sql = "select * from logs;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // we don't need to set any parameters because we're getting all logs:
            ResultSet resultSet = preparedStatement.executeQuery();
            // we use a while loop because there are multiple results:
            while(resultSet.next()) {
                Logs log = getLogs(resultSet);
                // add logs to list of logs
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    // get logs from a result set:
    public Logs getLogs(ResultSet resultSet) {
        try {
            String type = resultSet.getString("type");
            int accountid1 = resultSet.getInt("accountid1");
            int accountid2 = resultSet.getInt("accountid2");
            double amount = resultSet.getInt("amount");
            String date = resultSet.getString("date");

            return new Logs(type, accountid1, accountid2, amount, date);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(){
        String sql = "delete from logs;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
