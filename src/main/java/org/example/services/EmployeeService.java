package org.example.services;

import com.sun.tools.jdeprscan.scan.Scan;
import org.example.dao.AccApplicationsDao;
import org.example.dao.AccountDao;
import org.example.dao.DaoFactory;
import org.example.dao.LogsDao;
import org.example.entity.Account;
import org.example.entity.Logs;

import java.sql.SQLOutput;
import java.util.Scanner;

public class EmployeeService {
    AccApplicationsDao accApplicationsDao = DaoFactory.getAccApplicationsDao();

    public void viewAccountApplications(){


        System.out.println(accApplicationsDao.getAllAccounts());
    }

    public void confirmAccount(){
        System.out.println("Choose an application number to confirm the application: ");
        Scanner scanner = new Scanner(System.in);
        int accountid = scanner.nextInt();

        Account account = accApplicationsDao.getAccountByNumber(accountid);

        AccountDao accountDao = DaoFactory.getAccountDao();
        accountDao.insert(account);

        System.out.println("Application has been approved. Newly opened account is owned by: " + account.getAccUsername() + " and has a balance of " + account.getBalance());

        accApplicationsDao.delete(accountid);

    }

    public void rejectAccount(){
        System.out.println("Choose an application number to deny the application: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        Account account = accApplicationsDao.getAccountByNumber(id);
        accApplicationsDao.delete(id);

        System.out.println("Application has been denied. Application will be deleted.");

    }

    public void viewLogs(){

        System.out.println("Logs: ");

        LogsDao logsDao = DaoFactory.getLogsDao();

        System.out.println(logsDao.getAllLogs());



    }
}
