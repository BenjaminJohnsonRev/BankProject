package org.example.dao;

public class DaoFactory {

    private static AccountDao accountDao;
    private static UserDao userDao;

    // private constructor, intentionally disallow instantiation of this class:
    private DaoFactory() {}

    public static AccountDao getAccountDao() {
        if (accountDao == null) {
            accountDao = new AccountDaoImpl();
        }
        return accountDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }



}
