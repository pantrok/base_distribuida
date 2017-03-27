/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.vista;

import basedistribuida.beans.InformacionPersona;
import basedistribuida.connection.Connection;
import basedistribuida.coordinator.Coordinador;
import basedistribuida.model.Colonia;
import basedistribuida.model.Direccion;
import basedistribuida.model.Estado;
import basedistribuida.model.Municipio;
import basedistribuida.model.Persona;
import basedistribuida.model.controller.PersonaCtrl;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author USUARIO
 */
public class Main {

    public static void main(String[] args) {

        Coordinador coordinador = new Coordinador();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            
        }
        Estado estado = coordinador.obtenerEstadoById(3);
        System.out.println(estado);
        
        /*List<Estado> estados = coordinador.obtenerEstados();
        for (Estado edo : estados) {
            System.out.println(edo);
            coordinador.borrarEstado(edo);
        }*/
        
        //Probar personas junto con direcciones
        /*Estado estado = coordinador.obtenerEstadoById(3);
        Municipio municipio = coordinador.obtenerMunicipioById(6);
        municipio.setNombre("Tuxtla Gutierrez");
        coordinador.updateMunicipio(municipio);
        
        Colonia colonia = coordinador.obtenerColoniaById(1);
        colonia.setNombre("U.H. Loma Bella");
        coordinador.updateColonia(colonia);*/
        
        /*System.out.println(estado);
        Persona persona = new Persona();
        persona.setNombre("Daniel");
        persona.setApellidoPaterno("Sanchez");
        persona.setApellidoMaterno("Ruiz");
        persona.setFechaNacimiento(new Date());
        persona.setGenero(Persona.Genero.Masculino);
        Direccion direccion = new Direccion();
        direccion.setCalle("Cto. 101 Pte.");
        direccion.setCp(72490);
        direccion.setIdEstado(estado.getId());
        direccion.setIdMunicipio(municipio.getId());
        direccion.setIdColonia(Integer.MIN_VALUE);
        coordinador.insertarPersona(persona, direccion, estado);*/
        
        /*List<InformacionPersona> informacionPersonas = coordinador.obtenerPersonasByMunicipio(1);
        for (InformacionPersona ip : informacionPersonas) {
            System.out.println(ip);
        }*/
        
        coordinador.cerrarConexiones();
        System.exit(0);

    }

}
