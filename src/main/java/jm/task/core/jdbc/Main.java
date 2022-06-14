package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Александр", "Пушкин", (byte) 37);
        userService.saveUser("Александр", "Пушкин", (byte) 37);
        userService.saveUser("Александр", "Пушкин", (byte) 37);
        userService.saveUser("Александр", "Пушкин", (byte) 37);
        userService.saveUser("Александр", "Пушкин", (byte) 37);
        userService.getAllUsers();
        userService.dropUsersTable();
        if(Util.getSessionFactory() != null) {
            Util.getSessionFactory().close();
        }
    }
}
