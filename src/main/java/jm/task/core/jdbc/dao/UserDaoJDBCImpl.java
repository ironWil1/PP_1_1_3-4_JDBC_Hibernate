package jm.task.core.jdbc.dao;

import com.mysql.cj.protocol.Resultset;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
    }

    public void dropUsersTable() {
    }

    public void saveUser(String name, String lastName, byte age) {
    }

    public void removeUserById(long id) {
    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        return listOfUsers;
    }

    public void cleanUsersTable() {

    }
}
