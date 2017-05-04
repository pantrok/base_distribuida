/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.process;

import basedistribuida.connection.Connection;
import basedistribuida.coordinator.Nodo;
import basedistribuida.model.Direccion;
import basedistribuida.model.EstadoNodoReplica;
import basedistribuida.model.Persona;
import basedistribuida.model.controller.DireccionCtrl;
import basedistribuida.model.controller.EstadoNodoReplicaCtrl;
import basedistribuida.model.controller.PersonaCtrl;
import java.util.List;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author daniel
 */
public class SincronizaMaq4Process extends Thread {

    @Override
    public void run() {

        Nodo nodo = new Nodo(Nodo.Maquina.MAQUINA_4);
        try {
            if (nodo.getConexionReplica() == null) {
                System.out.println("Replica de " + nodo.getMaquina() + " abajo");
            } else {
                try {
                    EstadoNodoReplica estadoNodoReplica = new EstadoNodoReplicaCtrl(nodo.getConexionReplica()).findEstadoNodoReplicaById("M4");
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
                            Nodo nodoReplica = new Nodo(Nodo.Maquina.MAQUINA_3);
                            //Tenemos que checar personas y direcciones
                            personas = new PersonaCtrl(nodoReplica.getConexion()).obtenerTodos();
                            for (Persona p : personas) {
                                Direccion d = new DireccionCtrl(nodoReplica.getConexion()).findDireccionByPersonaId(p.getId());
                                Persona pNodoReplica = new PersonaCtrl(nodoReplica.getConexionReplica()).findPersonaById(p.getId());
                                if (pNodoReplica == null) {
                                    new PersonaCtrl(nodoReplica.getConexionReplica()).savePersona(p);
                                    new DireccionCtrl(nodoReplica.getConexionReplica()).saveDireccion(d);
                                } else if (!p.getChecksum().equals(pNodoReplica.getChecksum())) {
                                    new PersonaCtrl(nodoReplica.getConexionReplica()).updatePersona(p);
                                    new DireccionCtrl(nodoReplica.getConexionReplica()).updateDireccion(d);
                                }
                            }
                            personas = new PersonaCtrl(nodoReplica.getConexionReplica()).obtenerTodos();
                            for (Persona p : personas) {
                                Direccion d = new DireccionCtrl(nodoReplica.getConexionReplica()).findDireccionByPersonaId(p.getId());
                                Persona pNodoPrimario = new PersonaCtrl(nodoReplica.getConexion()).findPersonaById(p.getId());
                                if (pNodoPrimario == null) {
                                    new PersonaCtrl(nodoReplica.getConexionReplica()).deletePersona(p);
                                    new DireccionCtrl(nodoReplica.getConexionReplica()).deleteDireccion(d);
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
            if (nodo.getConexionReplica() != null) {
                Connection.dispose(nodo.getConexionReplica());
            }
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        new SincronizaMaq4Process().start();
    }

}
