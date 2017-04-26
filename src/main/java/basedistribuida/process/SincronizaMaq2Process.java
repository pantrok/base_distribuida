/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.process;

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
public class SincronizaMaq2Process extends Thread {

    @Override
    public void run() {

        Nodo nodo = new Nodo(Nodo.Maquina.MAQUINA_2);
        if (nodo.getConexionReplica() == null) {
            System.out.println("Replica de " + nodo.getMaquina() + " abajo");
        } else {
            try {
                EstadoNodoReplica estadoNodoReplica = new EstadoNodoReplicaCtrl(nodo.getConexionReplica()).findEstadoNodoReplicaById("M1");
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
                            Persona pNodoReplica = new PersonaCtrl(nodo.getConexion()).findPersonaById(p.getId());
                            if (pNodoReplica == null) {
                                new PersonaCtrl(nodo.getConexion()).deletePersona(p);
                                new DireccionCtrl(nodo.getConexion()).deleteDireccion(d);
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

        System.exit(0);
        
    }

    public static void main(String[] args) {
        new SincronizaMaq2Process().start();
    }

}
