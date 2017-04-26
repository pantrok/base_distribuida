/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.coordinator;

import basedistribuida.connection.Connection;
import org.hibernate.SessionFactory;

/**
 *
 * @author USUARIO
 */
public class Nodo {

    public enum Maquina {
        MAQUINA_1, MAQUINA_2, MAQUINA_3, MAQUINA_4;
    }

    private SessionFactory conexion;
    private SessionFactory conexionReplica;
    private final Maquina maquina;

    public Nodo(Maquina maquina) {
        this.maquina = maquina;
        iniciarConexiones();
    }
    
    private void iniciarConexiones() {
        switch (maquina) {
            case MAQUINA_1: {
                try {
                    conexion = Connection.getSessionFactory("hibernate.cfg.xml");
                } catch (Exception ex) {
                    //Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    //conexionReplica = Connection.getSessionFactory("hibernate2.cfg.xml");
                    conexionReplica = Connection.getSessionFactory("hibernateReplica.cfg.xml");
                } catch (Exception ex) {
                    //Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            }
            case MAQUINA_2: {
                try {
                    conexion = Connection.getSessionFactory("hibernate2.cfg.xml");
                } catch (Exception ex) {
                    //Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                }
                //conexionReplica = Connection.getSessionFactory("hibernate.cfg.xml");
                try {
                    conexionReplica = Connection.getSessionFactory("hibernateReplica2.cfg.xml");
                } catch (Exception ex) {
                    //Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            }
            case MAQUINA_3: {
                try {
                    conexion = Connection.getSessionFactory("hibernate3.cfg.xml");
                } catch (Exception ex) {
                    //Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    conexionReplica = Connection.getSessionFactory("hibernateReplica3.cfg.xml");
                } catch (Exception ex) {
                    //Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            }
            case MAQUINA_4: {
                try {
                    conexion = Connection.getSessionFactory("hibernate4.cfg.xml");
                } catch (Exception ex) {
                    //Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    conexionReplica = Connection.getSessionFactory("hibernateReplica4.cfg.xml");
                } catch (Exception ex) {
                    //Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }

    public SessionFactory getConexion() {
        return conexion;
    }

    public void setConexion(SessionFactory conexion) {
        this.conexion = conexion;
    }

    public SessionFactory getConexionReplica() {
        return conexionReplica;
    }

    public void setConexionReplica(SessionFactory conexionReplica) {
        this.conexionReplica = conexionReplica;
    }

    public Maquina getMaquina() {
        return maquina;
    }

}
