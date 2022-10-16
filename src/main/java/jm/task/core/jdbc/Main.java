package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        for (int i = 1; i <= 4; i++) {
            us.saveUser("Name" + i, "Lastname" + i, (byte) (i + 30));
            System.out.println(us.getAllUsers());
        }
       us.cleanUsersTable();
       us.dropUsersTable();
    }
}
