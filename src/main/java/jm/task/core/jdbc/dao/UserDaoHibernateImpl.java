package jm.task.core.jdbc.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }
    Transaction transaction = null;
    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE if not exists user ("
                    + "`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`name` VARCHAR(20) NOT NULL,"
                    + "`lastName` VARCHAR(50) NOT NULL,"
                    + "`age` MEDIUMINT(120) UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (`id`));";
            NativeQuery<User> query = session.createNativeQuery(sql,User.class);
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
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            String sql = "DROP TABLE if exists user";
            transaction = session.beginTransaction();
            NativeQuery<User> query = session.createNativeQuery(sql,User.class);
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
        try(Session session = Util.getSessionFactory().openSession()) {
            User user = new User(name,lastName,age);
            transaction = session.beginTransaction();
            session.persist(user);
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
        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        try(Session session = Util.getSessionFactory().openSession()) {
            String hql = "select user from user";
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            Query<User> query = session.createQuery(criteriaQuery);
            listOfUsers = query.getResultList();
            listOfUsers.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            String hql = "delete from user";
            transaction = session.beginTransaction();
            Query<User> query = session.createNativeQuery(hql,User.class);
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
