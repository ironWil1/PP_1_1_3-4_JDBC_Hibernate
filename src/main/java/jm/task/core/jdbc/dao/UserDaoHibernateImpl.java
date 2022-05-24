package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.sql.internal.NativeQueryImpl;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
@SuppressWarnings( "deprecation" )
public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "CREATE TABLE IF NOT EXISTS jdbc_kata_task.user (" +
                    " id INTEGER not null AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastName VARCHAR(255)," +
                    " age INTEGER, " + "PRIMARY KEY(id))";
            transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "DROP TABLE if exists jdbc_kata_task.user;";
            transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "INSERT INTO  jdbc_kata_task.user (name, lastName, age ) VALUE ( '"
                    + name + "', " + "'"
                    + lastName + "'," + "'"
                    + age + "');";
            transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
            System.out.println("User с именем " + name + " сохранен в БД");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "DELETE FROM jdbc_kata_task.user WHERE ID =" + id + ";";
            transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "SELECT * FROM jdbc_kata_task.user;";
            transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery(sql);
            List<Object[]> users = query.list();
            for (Object [] someUser: users) {
                User user = new User();
                user.setId(Long.parseLong(someUser[0].toString()));
                user.setName(someUser[1].toString());
                user.setLastName(someUser[2].toString());
                user.setAge(Byte.parseByte(someUser[3].toString()));
                listOfUsers.add(user);
                System.out.println(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            String sql = "TRUNCATE TABLE jdbc_kata_task.user;";
            transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
