/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.process;

import basedistribuida.connection.Connection;
import basedistribuida.coordinator.Nodo;
import basedistribuida.model.Colonia;
import basedistribuida.model.Direccion;
import basedistribuida.model.Estado;
import basedistribuida.model.EstadoNodoReplica;
import basedistribuida.model.Municipio;
import basedistribuida.model.Persona;
import basedistribuida.model.controller.ColoniaCtrl;
import basedistribuida.model.controller.DireccionCtrl;
import basedistribuida.model.controller.EstadoCtrl;
import basedistribuida.model.controller.EstadoNodoReplicaCtrl;
import basedistribuida.model.controller.MunicipioCtrl;
import basedistribuida.model.controller.PersonaCtrl;
import java.util.List;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author daniel
 */
public class SincronizaMaq2Process extends Thread {

    @Override
    public void run() {

        Nodo nodo = new Nodo(Nodo.Maquina.MAQUINA_2);
        try {
            if (nodo.getConexionReplica() == null) {
                System.out.println("Replica de " + nodo.getMaquina() + " abajo");
            } else {
                try {
                    EstadoNodoReplica estadoNodoReplica = new EstadoNodoReplicaCtrl(nodo.getConexionReplica()).findEstadoNodoReplicaById("M2");
                    if (estadoNodoReplica != null && estadoNodoReplica.getSincronizada() == 0) {
                        //La replica no esta actualizada, checamos si la particion primaria ya esta arriba
                        if (nodo.getConexion() != null) {
                            //Tenemos que checar personas y direcciones
                            List<Persona> personas = new PersonaCtrl(nodo.getConexionReplica()).obtenerTodos();
                            for (Persona p : personas) {
                                Direccion d = new DireccionCtrl(nodo.getConexionReplica()).findDireccionByPersonaId(p.getId());
                                Persona pNodoPrimario = new PersonaCtrl(nodo.getConexion()).findPersonaById(p.getId());
                                if (pNodoPrimario == null) {
                                    new PersonaCtrl(nodo.getConexion()).savePersona(p);
                                    new DireccionCtrl(nodo.getConexion()).saveDireccion(d);
                                } else if (!p.getChecksum().equals(pNodoPrimario.getChecksum())) {
                                    new PersonaCtrl(nodo.getConexion()).updatePersona(p);
                                    new DireccionCtrl(nodo.getConexion()).updateDireccion(d);
                                }
                            }
                            personas = new PersonaCtrl(nodo.getConexion()).obtenerTodos();
                            for (Persona p : personas) {
                                Direccion d = new DireccionCtrl(nodo.getConexion()).findDireccionByPersonaId(p.getId());
                                Persona pNodoReplica = new PersonaCtrl(nodo.getConexionReplica()).findPersonaById(p.getId());
                                if (pNodoReplica == null) {
                                    new PersonaCtrl(nodo.getConexion()).deletePersona(p);
                                    new DireccionCtrl(nodo.getConexion()).deleteDireccion(d);
                                }
                            }
                            
                            //Despues sincronizamos la replica que esta en la maquina, lo del nodo primario se va a esta maquina
                            Nodo nodoReplica = new Nodo(Nodo.Maquina.MAQUINA_1);
                            // Checamos estados
                            List<Estado> estados = new EstadoCtrl(nodoReplica.getConexion()).obtenerTodos();
                            for (Estado edo : estados) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Estado edoNodoReplica = new EstadoCtrl(nodoReplica.getConexionReplica()).findEstadoById(edo.getId());
                                if (edoNodoReplica == null) {
                                    //No existe asi que no lo insertamos
                                    new EstadoCtrl(nodoReplica.getConexionReplica()).saveEstado(edo);
                                } else //Ya existe, checamos si el checksum es distinto
                                {
                                    if (!edo.getChecksum().equals(edoNodoReplica.getChecksum())) {
                                        //Actualizamos
                                        new EstadoCtrl(nodoReplica.getConexionReplica()).updateEstado(edo);
                                    }
                                }
                            }
                            estados = new EstadoCtrl(nodoReplica.getConexionReplica()).obtenerTodos();
                            for (Estado edo : estados) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Estado edoNodoPrimario = new EstadoCtrl(nodoReplica.getConexion()).findEstadoById(edo.getId());
                                if (edoNodoPrimario == null) {
                                    //No existe en la replica asi que lo borramos
                                    new EstadoCtrl(nodoReplica.getConexionReplica()).deleteEstado(edo);
                                }
                            }
                            // Checamos municipios
                            List<Municipio> municipios = new MunicipioCtrl(nodoReplica.getConexion()).obtenerTodos();
                            for (Municipio mun : municipios) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Municipio munNodoReplica = new MunicipioCtrl(nodoReplica.getConexionReplica()).findMunicipioById(mun.getId());
                                if (munNodoReplica == null) {
                                    //No existe asi que no lo insertamos
                                    new MunicipioCtrl(nodoReplica.getConexionReplica()).saveMunicipio(mun);
                                } else //Ya existe, checamos si el checksum es distinto
                                {
                                    if (!mun.getChecksum().equals(munNodoReplica.getChecksum())) {
                                        //Actualizamos
                                        new MunicipioCtrl(nodoReplica.getConexionReplica()).updateMunicipio(mun);
                                    }
                                }
                            }
                            municipios = new MunicipioCtrl(nodoReplica.getConexionReplica()).obtenerTodos();
                            for (Municipio edo : municipios) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Municipio munNodoPrincipal = new MunicipioCtrl(nodoReplica.getConexion()).findMunicipioById(edo.getId());
                                if (munNodoPrincipal == null) {
                                    //No existe en la replica asi que lo borramos
                                    new MunicipioCtrl(nodoReplica.getConexionReplica()).deleteMunicipio(edo);
                                }
                            }
                            // Checamos colonias
                            List<Colonia> colonias = new ColoniaCtrl(nodoReplica.getConexion()).obtenerTodos();
                            for (Colonia col : colonias) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Colonia colNodoReplica = new ColoniaCtrl(nodoReplica.getConexionReplica()).findColoniaById(col.getId());
                                if (colNodoReplica == null) {
                                    //No existe asi que no lo insertamos
                                    new ColoniaCtrl(nodoReplica.getConexionReplica()).saveColonia(col);
                                } else //Ya existe, checamos si el checksum es distinto
                                {
                                    if (!col.getChecksum().equals(colNodoReplica.getChecksum())) {
                                        //Actualizamos
                                        new ColoniaCtrl(nodoReplica.getConexionReplica()).updateColonia(col);
                                    }
                                }
                            }
                            colonias = new ColoniaCtrl(nodoReplica.getConexionReplica()).obtenerTodos();
                            for (Colonia col : colonias) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Colonia colNodoPrimario = new ColoniaCtrl(nodoReplica.getConexion()).findColoniaById(col.getId());
                                if (colNodoPrimario == null) {
                                    //No existe en la replica asi que lo borramos
                                    new ColoniaCtrl(nodoReplica.getConexionReplica()).deleteColonia(col);
                                }
                            }
                            
                            estadoNodoReplica.setSincronizada(1);
                            new EstadoNodoReplicaCtrl(nodo.getConexionReplica()).updateEstadoNodoReplica(estadoNodoReplica);

                        } else {
                            System.out.println("Aun no se reestablece la conexion al nodo principal");
                        }

                    } else {
                        System.out.println("La base esta actualizada");

                    }
                } catch (JDBCConnectionException ex) {
                    System.out.println("Se perdio la conexion a la replica o al nodo primario");
                }
            }

        } finally {
            if (nodo.getConexion() != null) {
                Connection.dispose(nodo.getConexion());
            }
            if (nodo.getConexionReplica()!= null) {
                Connection.dispose(nodo.getConexionReplica());
            }
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        new SincronizaMaq2Process().start();
    }

}
