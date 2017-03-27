/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.model.controller;

import basedistribuida.connection.HibernateSession;
import basedistribuida.model.Direccion;
import org.hibernate.SessionFactory;

/**
 *
 * @author USUARIO
 */
public class DireccionCtrl extends HibernateSession {
    
    public DireccionCtrl() {
        super();
    }
    
    public DireccionCtrl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public Direccion saveDireccion(Direccion direccion) {
        int id = insert(direccion);
        direccion.setId(id);
        return direccion;
    }
    
    public boolean updateDireccion(Direccion direccion) {
        return update(direccion);
    }
    
    public boolean deleteDireccion(Direccion direccion) {
        return delete(direccion);
    }
    
    public Direccion findDireccionById(int id) {
        Direccion direccion = getById(Direccion.class, id);
        return direccion;
    }
    
    public Direccion findDireccionByPersonaId(int idPersona) {
        Direccion direccion = getFirstByQuery(Direccion.class, "SELECT * FROM direcciones WHERE idPersona = " + idPersona);
        return direccion;
    }
    
}