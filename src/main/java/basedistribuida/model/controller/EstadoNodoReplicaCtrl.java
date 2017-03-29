/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.model.controller;

import basedistribuida.connection.HibernateSession;
import basedistribuida.model.EstadoNodoReplica;
import org.hibernate.SessionFactory;

/**
 *
 * @author daniel
 */
public class EstadoNodoReplicaCtrl extends HibernateSession {

    public EstadoNodoReplicaCtrl() {
        super();
    }

    public EstadoNodoReplicaCtrl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public EstadoNodoReplica findEstadoNodoReplicaById(String nombre) {
        EstadoNodoReplica estadoNodoReplica = getById(EstadoNodoReplica.class, nombre);
        return estadoNodoReplica;
    }

    public boolean updateEstadoNodoReplica(EstadoNodoReplica estadoNodoReplica) {
        return update(estadoNodoReplica);
    }

}
