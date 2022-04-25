package org.example.dao;

public class DaoFactory {

    private static AccountDao accountDao;
    private static CustomerDao customerDao;
    private static EmployeeDao employeeDao;
    private static AccApplicationsDao accApplicationsDao;

    // private constructor, intentionally disallow instantiation of this class:
    private DaoFactory() {}

    public static AccountDao getAccountDao() {
        if (accountDao == null) {
            accountDao = new AccountDaoImpl();
        }
        return accountDao;
    }

    public static CustomerDao getCustomerDao() {
        if (customerDao == null) {
            customerDao = new CostumerDaoImpl();
        }
        return customerDao;
    }

    public static EmployeeDao getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    public static AccApplicationsDao getAccApplicationsDao() {
        if (accApplicationsDao == null) {
            accApplicationsDao = new AccApplicationsDaoImpl();
        }
        return accApplicationsDao;
    }

}
