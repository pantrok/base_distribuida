/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.model.controller;

import basedistribuida.connection.HibernateSession;
import basedistribuida.model.Colonia;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author USUARIO
 */
public class ColoniaCtrl extends HibernateSession {
    
    public ColoniaCtrl() {
        super();
    }
    
    public ColoniaCtrl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public Colonia saveColonia(Colonia colonia) {
        int id = insert(colonia);
        colonia.setId(id);
        return colonia;
    }
    
    public boolean updateColonia(Colonia colonia) {
        return update(colonia);
    }
    
    public boolean deleteColonia(Colonia colonia) {
        return delete(colonia);
    }
    
    public Colonia findColoniaById(int id) {
        Colonia colonia = getById(Colonia.class, id);
        return colonia;
    }
    
    public List<Colonia> obtenerTodosPorMunicipio(int idMunicipio) {
        return getAllByQuery(Colonia.class, "SELECT * FROM colonias WHERE idMunicipio = " + idMunicipio);
    }
    
}
