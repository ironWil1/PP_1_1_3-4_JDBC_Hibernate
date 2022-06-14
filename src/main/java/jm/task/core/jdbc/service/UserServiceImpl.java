package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userJDBCimpl = new UserDaoJDBCImpl();

    public void createUsersTable()  {userJDBCimpl.createUsersTable();}

    public void dropUsersTable() {
        userJDBCimpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userJDBCimpl.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        userJDBCimpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userJDBCimpl.getAllUsers();
    }

    public void cleanUsersTable() {
        userJDBCimpl.cleanUsersTable();
    }
}
