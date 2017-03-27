/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.model.controller;

import basedistribuida.connection.HibernateSession;
import basedistribuida.model.Municipio;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author USUARIO
 */
public class MunicipioCtrl extends HibernateSession {
    
    public MunicipioCtrl() {
        super();
    }
    
    public MunicipioCtrl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public Municipio saveMunicipio(Municipio municipio) {
        int id = insert(municipio);
        municipio.setId(id);
        return municipio;
    }
    
    public boolean updateMunicipio(Municipio municipio) {
        return update(municipio);
    }
    
    public boolean deleteMunicipio(Municipio municipio) {
        return delete(municipio);
    }
    
    public Municipio findMunicipioById(int id) {
        Municipio municipio = getById(Municipio.class, id);
        return municipio;
    }
    
    public List<Municipio> obtenerTodosPorEstado(int idEstado) {
        return getAllByQuery(Municipio.class, "SELECT * FROM municipios WHERE idEstado = " + idEstado);
    }
    
}
