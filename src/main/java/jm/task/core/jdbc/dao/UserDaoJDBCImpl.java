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
        String user_table = "CREATE TABLE user (" +
                " id INTEGER not null AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255)," +
                " age INTEGER, " + "PRIMARY KEY(id))";
        try(Connection con = Util.getConnection();
            Statement statement = con.createStatement()) {
            statement.executeUpdate(user_table);
            System.out.println("Таблица Юзеров создана");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String delete_table = "DROP TABLE user;";
        try(Connection con = Util.getConnection();
            Statement statement = con.createStatement()) {
            statement.executeUpdate(delete_table);
            System.out.println("Таблица удалена из БД");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String add_User = "INSERT INTO  `user`(name, lastName, age ) VALUE ( '"
                + name + "', " + "'"
                + lastName + "'," + "'"
                + age + "');";
        try(Connection con = Util.getConnection();
            Statement statement = con.createStatement()) {
            statement.executeUpdate(add_User);
            System.out.println("User с именем - " + name + " был добавлен в БД");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String remove_User = "DELETE FROM user WHERE ID =" + id + ";";
        try(Connection con = Util.getConnection()) {
            Statement statement = con.createStatement();
            statement.executeUpdate(remove_User);
            System.out.println("User с ID " + id + " удален из таблицы");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();

        String get_all_users = "SELECT * FROM user;";

        try(Connection con = Util.getConnection()) {
            PreparedStatement prepareStatement = con.prepareStatement(get_all_users);
            ResultSet resultset = prepareStatement.executeQuery();
            while(resultset.next()) {
                User user = new User();
                user.setId(resultset.getLong("id"));
                user.setName(resultset.getString("name"));
                user.setLastName(resultset.getString("lastName"));
                user.setAge(resultset.getByte("age"));
                listOfUsers.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listOfUsers;
    }

    public void cleanUsersTable() {
        String clean_table = "TRUNCATE TABLE user;";
        try(Connection con = Util.getConnection()) {
            Statement statement = con.createStatement();
            statement.executeUpdate(clean_table);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
