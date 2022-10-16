package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl udji = new UserDaoJDBCImpl();

    public UserServiceImpl() throws SQLException {
    }

    public void createUsersTable() throws SQLException {
        udji.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        udji.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        udji.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        udji.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return udji.getAllUsers();
    }

    public void cleanUsersTable() {
        udji.cleanUsersTable();
    }
}
