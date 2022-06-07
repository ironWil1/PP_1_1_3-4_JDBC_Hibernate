package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/jdbc_kata_task";
    private static final String name = "root";
    private static final String psw = "9677313531";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, name, psw);
        } catch (SQLException ex) {
            System.out.println("Connection to BD failed");
        }

        return connection;
    }
    // реализуйте настройку соеденения с БД
}
