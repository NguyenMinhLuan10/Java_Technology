package org.example.ProductDAO;

import org.example.Product.Phone;
import org.example.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class PhoneDAO implements Repository<Phone, Integer> {

    private final SessionFactory sessionFactory;

    public PhoneDAO() {
        Configuration configuration = new Configuration().configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public boolean add(Phone p) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Phone get(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Phone p = (Phone) session.get(Phone.class, id);
            session.getTransaction().commit();
            return p;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Phone> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Phone> ps = session.createQuery("from Phone").list();
            session.getTransaction().commit();
            return ps;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Phone p = (Phone) session.get(Phone.class, id);
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

    public boolean remove(Phone p){
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Phone p) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Phone getPhoneWithHighestPrice(){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "SELECT * FROM mobilephone ORDER BY price DESC LIMIT 1";
            NativeQuery<Phone> nativeQuery = session.createNativeQuery(sql, Phone.class);

            Phone highestPrice = nativeQuery.uniqueResult();
            session.getTransaction().commit();
            return highestPrice;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Phone> getPhoneCountry(){
        List<Phone> phones = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "SELECT * FROM mobilephone ORDER BY country ASC, price DESC";
            phones = session.createNativeQuery(sql, Phone.class).list();
            session.getTransaction().commit();
            return phones;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isPhoneAbove50(){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "SELECT * FROM mobilephone WHERE price > 50000000 LIMIT 1";
            NativeQuery<Phone> nativeQuery = session.createNativeQuery(sql, Phone.class);
            if (nativeQuery.uniqueResult() != null) {
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Phone getPhonePink(){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "SELECT * FROM mobilephone WHERE color = 'Pink' AND price > 15000000 LIMIT 1";
            NativeQuery<Phone> nativeQuery = session.createNativeQuery(sql, Phone.class);
            Phone phonePink = nativeQuery.uniqueResult();
            session.getTransaction().commit();
            return phonePink;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
