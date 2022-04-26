package org.example.dao;

import org.example.entity.Logs;
import org.example.entity.Post;

import java.util.List;

public interface LogsDao {
    public void insert(Logs logs);
    public Logs getLogsByType(String type);
    public Logs getLogsByAccount1(int accountid1);
    public Logs getLogsByAccount2(int accountid2);
    public List<Logs> getAllLogs();
    public void delete();
}
