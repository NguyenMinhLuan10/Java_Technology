package org.example.ProductDAO;

import org.example.Product.Manufacture;
import org.example.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ManufactureDAO implements Repository<Manufacture, Integer> {

    private final SessionFactory sessionFactory;

    public ManufactureDAO() {
        Configuration configuration = new Configuration().configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public boolean add(Manufacture p) {
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
    public Manufacture get(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Manufacture p = (Manufacture) session.get(Manufacture.class, id);
            session.getTransaction().commit();
            return p;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Manufacture> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Manufacture> ps = session.createQuery("from Manufacture").list();
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
            Manufacture p = (Manufacture) session.get(Manufacture.class, id);
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

    public boolean remove(Manufacture p){
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
    public boolean update(Manufacture p) {
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

    public boolean checkMoreThan100(){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "SELECT * FROM manufacture WHERE employee > 100 LIMIT 1";
            NativeQuery<Manufacture> query = session.createNativeQuery(sql, Manufacture.class);
            if(!query.getResultList().isEmpty()){
                return true;
            }
            session.getTransaction().commit();
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int sumOfAllEmployees(){
        int totalEmployees = 0;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "SELECT SUM(employee) FROM manufacture";
            NativeQuery<?> query = session.createNativeQuery(sql);
            Object result = query.getSingleResult();
            session.getTransaction().commit();
            totalEmployees = (result != null) ? ((Number) result).intValue() : 0;
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return totalEmployees;
    }

    public Manufacture getLastManufacturerInUS(){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "SELECT * FROM manufacture WHERE location = 'US' ORDER BY id DESC LIMIT 1";
            NativeQuery<Manufacture> query = session.createNativeQuery(sql, Manufacture.class);
            Manufacture p = (Manufacture) query.getSingleResult();
            session.getTransaction().commit();
            return p;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
