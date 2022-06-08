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
/*        userService.saveUser("Антон", "Чехов", (byte) 44);
        userService.saveUser("Александр", "Пушкин", (byte) 37);
        userService.saveUser("Лев", "Толстой", (byte) 82);
        userService.saveUser("Михаил", "Лермонтов", (byte) 26);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();*/
        // реализуйте алгоритм здесь
    }
}
