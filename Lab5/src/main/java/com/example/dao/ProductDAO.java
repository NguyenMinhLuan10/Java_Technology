package com.example.dao;

import com.example.Repository;
import com.example.model.Product;
import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO implements Repository<Product, Integer> {

    private final SessionFactory sessionFactory;

    public ProductDAO() {
        Configuration configuration = new Configuration().configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public boolean add(Product p) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product get(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product p = (Product) session.get(Product.class, id);
            session.getTransaction().commit();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Product> ps = session.createQuery("from Product").list();
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
            Product p = (Product) session.get(Product.class, id);
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

    public boolean remove(Product p) {
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
    public boolean update(Product p) {
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
}