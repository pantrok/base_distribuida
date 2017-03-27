/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.connection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author USUARIO
 */
public class Connection {

    public static SessionFactory getSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory(String config) throws Exception {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(config)
                .build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    //TODO: Metodo para cerar conexion de session factory
    public static void dispose(SessionFactory sessionFactory) {
        sessionFactory.close();
    }
    
}
