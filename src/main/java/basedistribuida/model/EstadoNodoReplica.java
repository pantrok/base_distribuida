/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author daniel
 */
@Entity(name = "estado_nodos_replica")
public class EstadoNodoReplica implements Serializable {
    
    @Id
    private String replica;
    @Column
    private Integer sincronizada;

    public EstadoNodoReplica() {
    }

    public EstadoNodoReplica(String replica, Integer sincronizada) {
        this.replica = replica;
        this.sincronizada = sincronizada;
    }

    public String getReplica() {
        return replica;
    }

    public void setReplica(String replica) {
        this.replica = replica;
    }

    public Integer getSincronizada() {
        return sincronizada;
    }

    public void setSincronizada(Integer sincronizada) {
        this.sincronizada = sincronizada;
    }

    @Override
    public String toString() {
        return "EstadoNodoReplica{" + "replica=" + replica + ", sincronizada=" + sincronizada + '}';
    }
    
}
