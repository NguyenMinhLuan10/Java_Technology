package com.example.dao;

import com.example.Repository;
import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Repository<User, Integer> {

    private final SessionFactory sessionFactory;

    public UserDAO() {
        Configuration configuration = new Configuration().configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public boolean add(User p) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public User get(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User p = (User) session.get(User.class, id);
            session.getTransaction().commit();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> ps = session.createQuery("from User").list();
            session.getTransaction().commit();
            return ps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User p = (User) session.get(User.class, id);
            if (p != null) {
                session.delete(p);
                session.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(User p) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User p) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isValidUser(String username, String password) {
        Session session = sessionFactory.openSession();
        boolean isValid = false;

        try {
            String sql = "SELECT * FROM user WHERE username = :username AND password = :password";
            Query<User> query = session.createNativeQuery(sql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            User user = query.uniqueResult();

            if (user != null) {
                isValid = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return isValid;
    }

}