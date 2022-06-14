package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }
    Connection connection = Util.getConnection();

    public void createUsersTable() {
        String sql = "CREATE TABLE `jdbc_kata_task`.`user` ("
                + "`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "`name` VARCHAR(20) NOT NULL,"
                + "`lastName` VARCHAR(50) NOT NULL,"
                + "`age` MEDIUMINT(120) UNSIGNED NOT NULL,"
                + "PRIMARY KEY (`id`));";
        try {

            connection.createStatement().executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(e.getMessage());
                }
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE user;";
        try {
            connection.createStatement().executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(e.getMessage());
                }
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO  `user`(name, lastName, age ) VALUE (?, ?, ?);";
        try {
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastName);
            prepStatement.setInt(3, age);
            prepStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем - " + name + " был добавлен в БД");
        } catch(SQLException e){
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(e.getMessage());
                }
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE ID = ?;";
        try {
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, (int) id);
            prepStatement.executeUpdate();
            connection.commit();
            System.out.println("User с ID " + id + " удален из таблицы");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(e.getMessage());
                }
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();

        String sql = "SELECT * FROM user;";

        try {
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            ResultSet resultset = prepStatement.executeQuery();
            while (resultset.next()) {
                User user = new User();
                user.setId(resultset.getLong("id"));
                user.setName(resultset.getString("name"));
                user.setLastName(resultset.getString("lastName"));
                user.setAge(resultset.getByte("age"));
                listOfUsers.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE user;";
        try {
            connection.createStatement().executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }


    }
}
