/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.connection;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author USUARIO
 */
public abstract class HibernateSession {

    private SessionFactory sessionFactory;
    
    public HibernateSession() {
         sessionFactory = Connection.getSessionFactory();
    }
    
    public HibernateSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    protected int insert(Object clazz) {
        Session session = sessionFactory.openSession();
        int id = -1;
        try {
            session.beginTransaction();
            id = (int) session.save(clazz);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return id;
    }
    
    protected boolean update(Object clazz) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(clazz);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return true;
    }
    
    protected boolean delete(Object clazz) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(clazz);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return true;
    }
    
    protected <T> T getById(Class<T> clazz,int id) {
        Session session = sessionFactory.openSession();
        T generic;
        try {
            session.beginTransaction();
            generic = session.get(clazz, id);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return generic;
    }
    
    protected <T> T getById(Class<T> clazz,String id) {
        Session session = sessionFactory.openSession();
        T generic;
        try {
            session.beginTransaction();
            generic = session.get(clazz, id);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return generic;
    }
    
    //TODO: Analizar que excepcion da cuando se desconecta la maquina
    protected <T> List<T> getAll(Class<T> clazz) {
        Session session = sessionFactory.openSession();
        List<T> generics;
        try {
            session.beginTransaction();
            // UPDATED: Create CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // UPDATED: Create CriteriaQuery
            CriteriaQuery<T> criteria = builder.createQuery(clazz);

            // UPDATED: Specify criteria root
            criteria.from(clazz);

            // UPDATED: Execute query
             generics = session.createQuery(criteria).getResultList();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return generics;
    }
    
    protected <T> List<T> getAllByQuery(Class<T> clazz, String query) {
        Session session = sessionFactory.openSession();
        List<T> generics;
        try {
            session.beginTransaction();
            
            generics = session.createSQLQuery(query).addEntity( clazz ).list();
            
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return generics;
    }
    
    protected <T> T getFirstByQuery(Class<T> clazz, String query) {
        Session session = sessionFactory.openSession();
        T generic = null;
        try {
            session.beginTransaction();
            
            List list = session.createSQLQuery(query).addEntity( clazz ).list();
            if (list != null && !list.isEmpty()) {
                generic = (T)list.get(0);
            }
            
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return generic;
    }
    
}
