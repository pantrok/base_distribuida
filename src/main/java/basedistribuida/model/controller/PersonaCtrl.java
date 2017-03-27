/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.model.controller;

import basedistribuida.connection.HibernateSession;
import basedistribuida.model.Persona;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author USUARIO
 */
public class PersonaCtrl extends HibernateSession {
    
    public PersonaCtrl() {
        super();
    }
    
    public PersonaCtrl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public Persona savePersona(Persona persona) {
        int id = insert(persona);
        persona.setId(id);
        return persona;
    }
    
    public boolean updatePersona(Persona persona) {
        return update(persona);
    }
    
    public boolean deletePersona(Persona persona) {
        return delete(persona);
    }
    
    public Persona findPersonaById(int id) {
        Persona persona = getById(Persona.class, id);
        return persona;
    }
    
    public List<Persona> obtenerTodos() {
        return getAll(Persona.class);
    }
    
    public List<Persona> obtenerPersonasPorEstado(int idEstado) {
        return getAllByQuery(Persona.class, "SELECT p.* FROM personas p LEFT JOIN direcciones d ON d.idPersona = p.id WHERE d.idEstado = " + idEstado);
    }
    
    public List<Persona> obtenerPersonasPorMunicipio(int idMunicipio) {
        return getAllByQuery(Persona.class, "SELECT p.* FROM personas p LEFT JOIN direcciones d ON d.idPersona = p.id WHERE d.idMunicipio = " + idMunicipio);
    }
    
    public List<Persona> obtenerPersonasPorColonia(int idColonia) {
        return getAllByQuery(Persona.class, "SELECT p.* FROM personas p LEFT JOIN direcciones d ON d.idPersona = p.id WHERE d.idColonia = " + idColonia);
    }
    
    public int getLastId() {
        Persona persona = getFirstByQuery(Persona.class, "SELECT * FROM personas ORDER BY id DESC");
        return persona == null ? 0 : persona.getId();
    }
    
}
