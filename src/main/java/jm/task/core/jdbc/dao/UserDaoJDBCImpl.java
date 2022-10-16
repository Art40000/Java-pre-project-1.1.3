package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String TABLE_NAME = "myTable";
    private final Connection conn;

    public UserDaoJDBCImpl() throws SQLException {
        conn = Util.getConnection();
    }

    public void createUsersTable() throws SQLException {

        String sql = "CREATE TABLE " + TABLE_NAME + " (" + "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(50), " + "lastname VARCHAR(50), " + "age  TINYINT)";
        DatabaseMetaData dbm = conn.getMetaData();

        try( Statement st = conn.createStatement();
             ResultSet tables = dbm.getTables(null, null, TABLE_NAME, null))
        {
            if (tables.next()) {
                System.out.println("table already exists");
            } else {
                st.execute(sql);
                System.out.println("table is created ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("table doesn't created");
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE " + TABLE_NAME;
        DatabaseMetaData dbm = conn.getMetaData();

        try( Statement st = conn.createStatement();
             ResultSet tables = dbm.getTables(null, null, TABLE_NAME, null) )
        {
            if (tables.next()) {
                st.execute(sql);
                System.out.println("table is dropped ");
            } else {
                System.out.println("table isn't exists ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("table doesn't dropped");
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        String sql = "INSERT INTO " + TABLE_NAME + " (id, name, lastName, age) VALUES(id, ?, ?, ?)";

        try( PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("table doesn't save user");
        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

        try( PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("table doesn't remove user");
        }
    }

    public List<User> getAllUsers() throws SQLException {

        String sql = "SELECT * FROM " + TABLE_NAME;
        List<User> res = new LinkedList<>();

        try( Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()) {
                User user = new User(rs.getString("name"),
                        rs.getString("lastName"), rs.getByte("age"));
                user.setId(rs.getLong("id"));
                res.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE " + TABLE_NAME;

        try( Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("table doesn't truncate");
        }
    }
}
