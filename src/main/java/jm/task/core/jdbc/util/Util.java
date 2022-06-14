package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Util(){}
    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/jdbc_kata_task";
    private static final String name = "root";
    private static final String psw = "9677313531";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, name, psw);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Connection to BD failed");
            }
        }
        return connection;
    }
    // реализуйте настройку соеденения с БД
}
