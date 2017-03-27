/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.model.controller;

import basedistribuida.connection.HibernateSession;
import basedistribuida.model.Estado;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author USUARIO
 */
public class EstadoCtrl extends HibernateSession {
    
    public EstadoCtrl() {
        super();
    }
    
    public EstadoCtrl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public Estado saveEstado(Estado estado) {
        int id = insert(estado);
        estado.setId(id);
        return estado;
    }
    
    public boolean updateEstado(Estado estado) {
        return update(estado);
    }
    
    public boolean deleteEstado(Estado estado) {
        return delete(estado);
    }
    
    public Estado findEstadoById(int id) {
        Estado estado = getById(Estado.class, id);
        return estado;
    }
    
    public List<Estado> obtenerTodos() {
        return getAll(Estado.class);
    }
    
}
