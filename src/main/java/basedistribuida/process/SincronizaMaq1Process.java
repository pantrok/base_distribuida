/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.process;

import basedistribuida.coordinator.Nodo;
import basedistribuida.model.Colonia;
import basedistribuida.model.Estado;
import basedistribuida.model.EstadoNodoReplica;
import basedistribuida.model.Municipio;
import basedistribuida.model.controller.ColoniaCtrl;
import basedistribuida.model.controller.EstadoCtrl;
import basedistribuida.model.controller.EstadoNodoReplicaCtrl;
import basedistribuida.model.controller.MunicipioCtrl;
import java.util.List;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author USUARIO
 */
public class SincronizaMaq1Process extends Thread {

    //Proceso que checa catalogos
    @Override
    public void run() {

        try {
            Nodo nodo = new Nodo(Nodo.Maquina.MAQUINA_1);
            if (nodo.getConexionReplica() == null) {
                System.out.println("Replica de " + nodo.getMaquina() + " abajo");
            } else {
                try {
                    EstadoNodoReplica estadoNodoReplica = new EstadoNodoReplicaCtrl(nodo.getConexionReplica()).findEstadoNodoReplicaById("M1");
                    if (estadoNodoReplica != null && estadoNodoReplica.getSincronizada() == 0) {
                        //La replica no esta actualizada, checamos si la particion primaria ya esta arriba
                        if (nodo.getConexion() != null) {
                            // Se pudo establecer conexion, intentamos sincronizar
                            // Checamos estados
                            List<Estado> estados = new EstadoCtrl(nodo.getConexionReplica()).obtenerTodos();
                            for (Estado edo : estados) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Estado edoNodoPrimario = new EstadoCtrl(nodo.getConexion()).findEstadoById(edo.getId());
                                if (edoNodoPrimario == null) {
                                    //No existe asi que no lo insertamos
                                    new EstadoCtrl(nodo.getConexion()).saveEstado(edo);
                                } else //Ya existe, checamos si el checksum es distinto
                                {
                                    if (!edo.getChecksum().equals(edoNodoPrimario.getChecksum())) {
                                        //Actualizamos
                                        new EstadoCtrl(nodo.getConexion()).updateEstado(edo);
                                    }
                                }
                            }
                            estados = new EstadoCtrl(nodo.getConexion()).obtenerTodos();
                            for (Estado edo : estados) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Estado edoNodoReplica = new EstadoCtrl(nodo.getConexionReplica()).findEstadoById(edo.getId());
                                if (edoNodoReplica == null) {
                                    //No existe en la replica asi que lo borramos
                                    new EstadoCtrl(nodo.getConexion()).deleteEstado(edo);
                                }
                            }
                            // Checamos municipios
                            List<Municipio> municipios = new MunicipioCtrl(nodo.getConexionReplica()).obtenerTodos();
                            for (Municipio mun : municipios) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Municipio munNodoPrimario = new MunicipioCtrl(nodo.getConexion()).findMunicipioById(mun.getId());
                                if (munNodoPrimario == null) {
                                    //No existe asi que no lo insertamos
                                    new MunicipioCtrl(nodo.getConexion()).saveMunicipio(mun);
                                } else //Ya existe, checamos si el checksum es distinto
                                {
                                    if (!mun.getChecksum().equals(munNodoPrimario.getChecksum())) {
                                        //Actualizamos
                                        new MunicipioCtrl(nodo.getConexion()).updateMunicipio(mun);
                                    }
                                }
                            }
                            municipios = new MunicipioCtrl(nodo.getConexion()).obtenerTodos();
                            for (Municipio edo : municipios) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Municipio munNodoReplica = new MunicipioCtrl(nodo.getConexionReplica()).findMunicipioById(edo.getId());
                                if (munNodoReplica == null) {
                                    //No existe en la replica asi que lo borramos
                                    new MunicipioCtrl(nodo.getConexion()).deleteMunicipio(edo);
                                }
                            }
                            // Checamos colonias
                            List<Colonia> colonias = new ColoniaCtrl(nodo.getConexionReplica()).obtenerTodos();
                            for (Colonia col : colonias) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Colonia colNodoPrimario = new ColoniaCtrl(nodo.getConexion()).findColoniaById(col.getId());
                                if (colNodoPrimario == null) {
                                    //No existe asi que no lo insertamos
                                    new ColoniaCtrl(nodo.getConexion()).saveColonia(col);
                                } else //Ya existe, checamos si el checksum es distinto
                                {
                                    if (!col.getChecksum().equals(colNodoPrimario.getChecksum())) {
                                        //Actualizamos
                                        new ColoniaCtrl(nodo.getConexion()).updateColonia(col);
                                    }
                                }
                            }
                            colonias = new ColoniaCtrl(nodo.getConexion()).obtenerTodos();
                            for (Colonia col : colonias) {
                                //Primero vemos si hay que insertar nuevos o actualizar
                                Colonia colNodoReplica = new ColoniaCtrl(nodo.getConexionReplica()).findColoniaById(col.getId());
                                if (colNodoReplica == null) {
                                    //No existe en la replica asi que lo borramos
                                    new ColoniaCtrl(nodo.getConexion()).deleteColonia(col);
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
                    System.out.println("Se perdio la conexion a la replica");
                }
            }

        } finally {
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        new SincronizaMaq1Process().start();
    }

}
