/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.coordinator;

import basedistribuida.beans.InformacionPersona;
import basedistribuida.connection.Connection;
import basedistribuida.coordinator.Nodo.Maquina;
import basedistribuida.model.Colonia;
import basedistribuida.model.Direccion;
import basedistribuida.model.Estado;
import basedistribuida.model.Estado.Zona;
import basedistribuida.model.Municipio;
import basedistribuida.model.Persona;
import basedistribuida.model.controller.ColoniaCtrl;
import basedistribuida.model.controller.DireccionCtrl;
import basedistribuida.model.controller.EstadoCtrl;
import basedistribuida.model.controller.MunicipioCtrl;
import basedistribuida.model.controller.PersonaCtrl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author USUARIO
 */
public class Coordinador {

    private List<Nodo> nodos;

    public Coordinador() {
        iniciarNodos();
    }

    private void iniciarNodos() {
        nodos = new ArrayList<>();
        for (Maquina maquina : Nodo.Maquina.values()) {
            nodos.add(new Nodo(maquina));
        }
    }

    //Algoritmo de fragmentacion
    //Maquina 1: Tablas de estados, municipios y colonias en maquina de catalogos //Replica de personas zona sur
    //Maquina 2: Personas, direccion fragmentada de forma horizontal que vivan en zona sur //Replica de personas de zona centro
    //Maquina 3: Personas, direccion fragmentada de forma horizontal que vivan en zona centro //Replica de personas de zona norte
    //Maquina 4: Personas, direccion fragmentada de forma horizontal que vivan en zona norte //Replica de catalogos
    private Nodo getNodo(Maquina maquina) {
        for (Nodo nodo : nodos) {
            if (nodo.getMaquina() == maquina) {
                return nodo;
            }
        }
        return null;
    }

    public List<Estado> obtenerEstados() {
        List<Estado> estados = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            estados = new EstadoCtrl(nodo.getConexion()).obtenerTodos();
        } catch (Exception e) {
            //TODO: Poner excepcion que identifique que se perdio la conexion
            estados = new EstadoCtrl(nodo.getConexionReplica()).obtenerTodos();
        }
        return estados;
    }

    public Estado obtenerEstadoById(int id) {
        Estado estado = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);

        if (nodo.getConexion() == null) {
            estado = new EstadoCtrl(nodo.getConexionReplica()).findEstadoById(id);
        } else {
            try {
                estado = new EstadoCtrl(nodo.getConexion()).findEstadoById(id);
            } catch (JDBCConnectionException ex) {
                System.out.println("Se desconecto el nodo principal");
                estado = new EstadoCtrl(nodo.getConexionReplica()).findEstadoById(id);
            }
        }
        return estado;
    }

    public Municipio obtenerMunicipioById(int id) {
        Municipio municipio = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            municipio = new MunicipioCtrl(nodo.getConexion()).findMunicipioById(id);
        } catch (Exception e) {
            //TODO: Poner excepcion que identifique que se perdio la conexion
            municipio = new MunicipioCtrl(nodo.getConexionReplica()).findMunicipioById(id);
        }
        return municipio;
    }

    public List<Municipio> obtenerMunicipiosByEstado(int idEstado) {
        List<Municipio> municipios = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            municipios = new MunicipioCtrl(nodo.getConexion()).obtenerTodosPorEstado(idEstado);
        } catch (Exception e) {
            //TODO: Poner excepcion que identifique que se perdio la conexion
            municipios = new MunicipioCtrl(nodo.getConexionReplica()).obtenerTodosPorEstado(idEstado);
        }
        return municipios;
    }

    public List<Colonia> obtenerColoniasByMunicipio(int idMunicipio) {
        List<Colonia> colonias = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            colonias = new ColoniaCtrl(nodo.getConexion()).obtenerTodosPorMunicipio(idMunicipio);
        } catch (Exception e) {
            //TODO: Poner excepcion que identifique que se perdio la conexion
            colonias = new ColoniaCtrl(nodo.getConexionReplica()).obtenerTodosPorMunicipio(idMunicipio);
        }
        return colonias;
    }

    public Colonia obtenerColoniaById(int id) {
        Colonia colonia = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            colonia = new ColoniaCtrl(nodo.getConexion()).findColoniaById(id);
        } catch (Exception e) {
            //TODO: Poner excepcion que identifique que se perdio la conexion
            colonia = new ColoniaCtrl(nodo.getConexionReplica()).findColoniaById(id);
        }
        return colonia;
    }

    public Estado insertarEstado(Estado estado) {
        Estado edo = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            edo = new EstadoCtrl(nodo.getConexion()).saveEstado(estado);
            edo = new EstadoCtrl(nodo.getConexionReplica()).saveEstado(estado);
        } catch (Exception e) {
            edo = new EstadoCtrl(nodo.getConexionReplica()).saveEstado(estado);
        }
        return edo;
    }

    public Municipio insertarMunicipio(Municipio municipio) {
        Municipio m = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            m = new MunicipioCtrl(nodo.getConexion()).saveMunicipio(municipio);
            m = new MunicipioCtrl(nodo.getConexionReplica()).saveMunicipio(municipio);
        } catch (Exception e) {
            m = new MunicipioCtrl(nodo.getConexionReplica()).saveMunicipio(municipio);
        }
        return m;
    }

    public Colonia insertarColonia(Colonia colonia) {
        Colonia c = null;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            c = new ColoniaCtrl(nodo.getConexion()).saveColonia(colonia);
            c = new ColoniaCtrl(nodo.getConexionReplica()).saveColonia(colonia);
        } catch (Exception e) {
            c = new ColoniaCtrl(nodo.getConexionReplica()).saveColonia(colonia);
        }
        return c;
    }

    public boolean updateEstado(Estado estado) {
        boolean result;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            result = new EstadoCtrl(nodo.getConexion()).updateEstado(estado);
            result = new EstadoCtrl(nodo.getConexionReplica()).updateEstado(estado);
        } catch (Exception e) {
            result = new EstadoCtrl(nodo.getConexionReplica()).updateEstado(estado);
        }
        return result;
    }

    public boolean updateMunicipio(Municipio municipio) {
        boolean result;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            result = new MunicipioCtrl(nodo.getConexion()).updateMunicipio(municipio);
            result = new MunicipioCtrl(nodo.getConexionReplica()).updateMunicipio(municipio);
        } catch (Exception e) {
            result = new MunicipioCtrl(nodo.getConexionReplica()).updateMunicipio(municipio);
        }
        return result;
    }

    public boolean updateColonia(Colonia colonia) {
        boolean result;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            result = new ColoniaCtrl(nodo.getConexion()).updateColonia(colonia);
            result = new ColoniaCtrl(nodo.getConexionReplica()).updateColonia(colonia);
        } catch (Exception e) {
            result = new ColoniaCtrl(nodo.getConexionReplica()).updateColonia(colonia);
        }
        return result;
    }

    //Borrar Estados
    public boolean borrarEstado(Estado estado) {
        boolean result;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            result = new EstadoCtrl(nodo.getConexion()).deleteEstado(estado);
            result = new EstadoCtrl(nodo.getConexionReplica()).deleteEstado(estado);
        } catch (Exception e) {
            result = new EstadoCtrl(nodo.getConexionReplica()).deleteEstado(estado);
        }
        return result;
    }

    //Borrar Municipios
    public boolean borrarMunicipio(Municipio municipio) {
        boolean result;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            result = new MunicipioCtrl(nodo.getConexion()).deleteMunicipio(municipio);
            result = new MunicipioCtrl(nodo.getConexionReplica()).deleteMunicipio(municipio);
        } catch (Exception e) {
            result = new MunicipioCtrl(nodo.getConexionReplica()).deleteMunicipio(municipio);
        }
        return result;
    }

    //Borrar Colonias
    public boolean borrarColonia(Colonia colonia) {
        boolean result;
        Nodo nodo = getNodo(Maquina.MAQUINA_1);
        try {
            result = new ColoniaCtrl(nodo.getConexion()).deleteColonia(colonia);
            result = new ColoniaCtrl(nodo.getConexionReplica()).deleteColonia(colonia);
        } catch (Exception e) {
            result = new ColoniaCtrl(nodo.getConexionReplica()).deleteColonia(colonia);
        }
        return result;
    }

    public boolean insertarPersona(Persona persona, Direccion direccion, Estado estado) {
        boolean result = false;
        Nodo nodo;
        if (estado.getZona() == Zona.Sur) {
            nodo = getNodo(Maquina.MAQUINA_2);
        } /*else if (estado.getZona() == Zona.Centro) {
            nodo = getNodo(Maquina.MAQUINA_3);
        }*/ else {
            nodo = getNodo(Maquina.MAQUINA_3);
        }
        try {
            int lastId = obtenerUltimoIdPersona();
            persona.setId(lastId + 1);
            Persona p = new PersonaCtrl(nodo.getConexion()).savePersona(persona);
            new PersonaCtrl(nodo.getConexionReplica()).savePersona(persona);
            direccion.setIdPersona(p.getId());
            direccion.setId(p.getId());
            Direccion d = new DireccionCtrl(nodo.getConexion()).saveDireccion(direccion);
            new DireccionCtrl(nodo.getConexionReplica()).saveDireccion(direccion);
            result = p != null && d != null;
        } catch (Exception e) {
            int lastId = obtenerUltimoIdPersona();
            persona.setId(lastId + 1);
            Persona p = new PersonaCtrl(nodo.getConexionReplica()).savePersona(persona);
            direccion.setIdPersona(p.getId());
            direccion.setId(p.getId());
            Direccion d = new DireccionCtrl(nodo.getConexionReplica()).saveDireccion(direccion);
            result = p != null && d != null;
        }
        return result;
    }

    public boolean editarPersona(Persona persona, Direccion direccion, Estado estado) {
        boolean result = false;
        Nodo nodo;
        if (estado.getZona() == Zona.Sur) {
            nodo = getNodo(Maquina.MAQUINA_2);
        } /*else if (estado.getZona() == Zona.Centro) {
            nodo = getNodo(Maquina.MAQUINA_3);
        }*/ else {
            nodo = getNodo(Maquina.MAQUINA_3);
        }
        try {
            new PersonaCtrl(nodo.getConexion()).updatePersona(persona);
            new PersonaCtrl(nodo.getConexionReplica()).updatePersona(persona);
            new DireccionCtrl(nodo.getConexion()).updateDireccion(direccion);
            new DireccionCtrl(nodo.getConexionReplica()).updateDireccion(direccion);
            result = true;
        } catch (Exception e) {
            new PersonaCtrl(nodo.getConexionReplica()).updatePersona(persona);
            new DireccionCtrl(nodo.getConexionReplica()).updateDireccion(direccion);
            result = true;
        }
        return result;
    }

    //BorrarPersona
    //ObtenerPersonas
    public List<InformacionPersona> obtenerPersonas() {
        List<InformacionPersona> personas = null;
        Nodo nodo1 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo2 = getNodo(Maquina.MAQUINA_3);
        //Nodo nodo3 = getNodo(Maquina.MAQUINA_4);
        try {
            personas = new ArrayList<>();
            for (Persona persona : new PersonaCtrl(nodo1.getConexion()).obtenerTodos()) {
                Direccion direccion = new DireccionCtrl(nodo1.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            for (Persona persona : new PersonaCtrl(nodo2.getConexion()).obtenerTodos()) {
                Direccion direccion = new DireccionCtrl(nodo2.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            /*for (Persona persona : new PersonaCtrl(nodo3.getConexion()).obtenerTodos()) {
                Direccion direccion = new DireccionCtrl(nodo3.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }*/
            //TODO: Ordenamos por id
        } catch (Exception e) {
            //TODO: Poner excepcion que identifique que se perdio la conexion
            personas = new ArrayList<>();
            for (Persona persona : new PersonaCtrl(nodo1.getConexionReplica()).obtenerTodos()) {
                Direccion direccion = new DireccionCtrl(nodo1.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerTodos()) {
                Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            /*for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerTodos()) {
                Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }*/
        }
        return personas;
    }

    //ObtenerPersonasPorEstado
    public List<InformacionPersona> obtenerPersonasByEstado(int idEstado) {
        List<InformacionPersona> personas = null;
        Nodo nodo1 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo2 = getNodo(Maquina.MAQUINA_3);
        //Nodo nodo3 = getNodo(Maquina.MAQUINA_4);
        try {
            personas = new ArrayList<>();
            for (Persona persona : new PersonaCtrl(nodo1.getConexion()).obtenerPersonasPorEstado(idEstado)) {
                Direccion direccion = new DireccionCtrl(nodo1.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            for (Persona persona : new PersonaCtrl(nodo2.getConexion()).obtenerPersonasPorEstado(idEstado)) {
                Direccion direccion = new DireccionCtrl(nodo2.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            /*for (Persona persona : new PersonaCtrl(nodo3.getConexion()).obtenerPersonasPorEstado(idEstado)) {
                Direccion direccion = new DireccionCtrl(nodo3.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }*/
            //Ordenamos por id
        } catch (Exception e) {
            //TODO: Poner excepcion que identifique que se perdio la conexion

            System.err.println(e);

            personas = new ArrayList<>();
            for (Persona persona : new PersonaCtrl(nodo1.getConexionReplica()).obtenerPersonasPorEstado(idEstado)) {
                Direccion direccion = new DireccionCtrl(nodo1.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerPersonasPorEstado(idEstado)) {
                Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            /*for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerPersonasPorEstado(idEstado)) {
                Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }*/
        }
        return personas;
    }

    //ObtenerPersonasPorMunicipio
    public List<InformacionPersona> obtenerPersonasByMunicipio(int idMunicipio) {
        List<InformacionPersona> personas = null;
        Nodo nodo1 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo2 = getNodo(Maquina.MAQUINA_3);
        //Nodo nodo3 = getNodo(Maquina.MAQUINA_4);

        personas = new ArrayList<>();
        if (nodo1.getConexion() == null) {
            for (Persona persona : new PersonaCtrl(nodo1.getConexionReplica()).obtenerPersonasPorMunicipio(idMunicipio)) {
                Direccion direccion = new DireccionCtrl(nodo1.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo1.getConexion()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo1.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (Exception ex) {
                Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (nodo2.getConexion() == null) {
            for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerPersonasPorMunicipio(idMunicipio)) {
                Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
        } else {
            try {
                for (Persona persona : new PersonaCtrl(nodo2.getConexion()).obtenerPersonasPorMunicipio(idMunicipio)) {
                    Direccion direccion = new DireccionCtrl(nodo2.getConexion()).findDireccionByPersonaId(persona.getId());
                    InformacionPersona info = new InformacionPersona(persona, direccion);
                    personas.add(info);
                }
            } catch (Exception ex) {
                Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*for (Persona persona : new PersonaCtrl(nodo3.getConexion()).obtenerPersonasPorMunicipio(idMunicipio)) {
                Direccion direccion = new DireccionCtrl(nodo3.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }*/
        //Ordenamos por id
        return personas;
    }

    //ObtenerPersonasPorColonia
    public List<InformacionPersona> obtenerPersonasByColonia(int idColonia) {
        List<InformacionPersona> personas = null;
        Nodo nodo1 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo2 = getNodo(Maquina.MAQUINA_3);
        //Nodo nodo3 = getNodo(Maquina.MAQUINA_4);
        try {
            personas = new ArrayList<>();
            for (Persona persona : new PersonaCtrl(nodo1.getConexion()).obtenerPersonasPorColonia(idColonia)) {
                Direccion direccion = new DireccionCtrl(nodo1.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            for (Persona persona : new PersonaCtrl(nodo2.getConexion()).obtenerPersonasPorColonia(idColonia)) {
                Direccion direccion = new DireccionCtrl(nodo2.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            /*for (Persona persona : new PersonaCtrl(nodo3.getConexion()).obtenerPersonasPorColonia(idColonia)) {
                Direccion direccion = new DireccionCtrl(nodo3.getConexion()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }*/
            //Ordenamos por id
        } catch (Exception e) {
            //TODO: Poner excepcion que identifique que se perdio la conexion
            personas = new ArrayList<>();
            for (Persona persona : new PersonaCtrl(nodo1.getConexionReplica()).obtenerPersonasPorColonia(idColonia)) {
                Direccion direccion = new DireccionCtrl(nodo1.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            for (Persona persona : new PersonaCtrl(nodo2.getConexionReplica()).obtenerPersonasPorColonia(idColonia)) {
                Direccion direccion = new DireccionCtrl(nodo2.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }
            /*for (Persona persona : new PersonaCtrl(nodo3.getConexionReplica()).obtenerPersonasPorColonia(idColonia)) {
                Direccion direccion = new DireccionCtrl(nodo3.getConexionReplica()).findDireccionByPersonaId(persona.getId());
                InformacionPersona info = new InformacionPersona(persona, direccion);
                personas.add(info);
            }*/
        }
        return personas;
    }

    public boolean borrarInformacionPersona(Persona persona, Direccion direccion, Estado estado) {
        boolean result = false;
        Nodo nodo;
        if (estado.getZona() == Zona.Sur) {
            nodo = getNodo(Maquina.MAQUINA_2);
        } /*else if (estado.getZona() == Zona.Centro) {
            nodo = getNodo(Maquina.MAQUINA_3);
        }*/ else {
            nodo = getNodo(Maquina.MAQUINA_3);
        }
        try {
            new PersonaCtrl(nodo.getConexion()).deletePersona(persona);
            new DireccionCtrl(nodo.getConexion()).deleteDireccion(direccion);
            new PersonaCtrl(nodo.getConexionReplica()).deletePersona(persona);
            new DireccionCtrl(nodo.getConexionReplica()).deleteDireccion(direccion);
            result = true;
        } catch (Exception e) {
            new PersonaCtrl(nodo.getConexionReplica()).deletePersona(persona);
            new DireccionCtrl(nodo.getConexionReplica()).deleteDireccion(direccion);
            result = true;
        }
        return result;
    }

    //Metodos o forma de obtener ultimo id de persona y de direccion, aunque id de direccion y de persona deberia de ser el mismo siempre
    private int obtenerUltimoIdPersona() {
        int lastId = 0;
        Nodo nodo1 = getNodo(Maquina.MAQUINA_2);
        Nodo nodo2 = getNodo(Maquina.MAQUINA_3);
        //Nodo nodo3 = getNodo(Maquina.MAQUINA_4);
        try {
            int maq1Id = new PersonaCtrl(nodo1.getConexion()).getLastId();
            int maq2Id = new PersonaCtrl(nodo2.getConexion()).getLastId();
            //int maq3Id = new PersonaCtrl(nodo3.getConexionReplica()).getLastId();
            //lastId = Math.max(maq1Id, Math.max(maq2Id, maq3Id));
            lastId = Math.max(maq1Id, maq2Id);
            //Ordenamos por id
        } catch (Exception e) {
            //TODO: Poner excepcion que identifique que se perdio la conexion
            int maq1Id = new PersonaCtrl(nodo1.getConexionReplica()).getLastId();
            int maq2Id = new PersonaCtrl(nodo2.getConexionReplica()).getLastId();
            //int maq3Id = new PersonaCtrl(nodo3.getConexionReplica()).getLastId();
            //lastId = Math.max(maq1Id, Math.max(maq2Id, maq3Id));
            lastId = Math.max(maq1Id, maq2Id);
        }
        return lastId;
    }

    public void cerrarConexiones() {
        for (Nodo nodo : nodos) {
            if (nodo.getConexion() != null) {
                Connection.dispose(nodo.getConexion());
            }
            if (nodo.getConexionReplica() != null) {
                Connection.dispose(nodo.getConexionReplica());
            }
        }
    }

}
