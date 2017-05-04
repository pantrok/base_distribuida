/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.broadcast;

import basedistribuida.vista.Colonias;
import basedistribuida.vista.Estados;
import basedistribuida.vista.Municipios;
import basedistribuida.vista.Panel;
import basedistribuida.vista.Personas;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author daniel
 */
public class ServidorLocal extends Thread {

    Object formClazz;
    
    public ServidorLocal(String name, Object formClazz) {
        super(name);
        this.formClazz = formClazz;
    }
    
    @Override
    public void run() {

        MulticastSocket mcSocket = null;
        InetAddress mcIPAddress = null;
        try {
            int mcPort = 12346;
            String mcIPStr = "230.1.1.1";
            mcIPAddress = InetAddress.getByName(mcIPStr);
            mcSocket = new MulticastSocket(mcPort);
            System.out.println("Servidor local corriendo en :"
                    + mcSocket.getLocalSocketAddress());
            mcSocket.joinGroup(mcIPAddress);

            System.out.println("Iniciando thread");
            
            while (!Thread.currentThread().isInterrupted()) {
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                System.out.println("Esperando por mensaje de broadcast...");
                mcSocket.receive(packet);
                String msg = new String(packet.getData(), packet.getOffset(),
                        packet.getLength());
                System.out.println("Mensaje de broadcast recibido :" + msg);
                //TODO:Si el mensaje es de cierto tipo se recarga la interfaz
                if (msg.equals("Operacion")) {
                    if (formClazz instanceof Estados) {
                        ((Estados) formClazz).cargarEstados();
                    } else if (formClazz instanceof Municipios) {
                        ((Municipios) formClazz).cargarMunicipios();
                    } else if (formClazz instanceof Colonias) {
                        ((Colonias) formClazz).cargarColonias();
                    } else if (formClazz instanceof Personas) {
                        ((Personas) formClazz).cargarPersonas();
                    }
                }
                
            }

            System.out.println("Thread detenido, terminando ejecucion");
            mcSocket.leaveGroup(mcIPAddress);
            mcSocket.close();

        } catch (IOException e) { }

    }

    /**public static void main(String[] args) throws Exception {
        new ServidorLocal("server").start();
    }*/

}
