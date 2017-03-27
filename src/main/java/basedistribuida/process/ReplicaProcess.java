/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.process;

/**
 *
 * @author USUARIO
 */
public class ReplicaProcess extends Thread{

    
    
    @Override
    public void run() {
        
        //TODO: Definir logica que se seguira para poder checar que todas las replicas y los nodos destinados a cierta informacion esten sincronizados
        
        //Recibir como parametro ip y datos de base (usuario, contraseña), ver si se pasan en linea de parametros o se tienen ya definidos 
        //De alguna forma poder iterar dentro de cada una de las tablas
        //De alguna forma checar la informacion o los bytes o fechas de modificacion de cada una de las tablas en ambas bases
            //Si los bytes o la ultima fecha de modificacion no coincide hacer un dump de la base con los mayor bytes o con la fecha mas reciente
             //en la otra base que tiene menos bytes o una fecha menos reciente, si se da este caso terminar la corrida del proceso
        
    }
    
}
