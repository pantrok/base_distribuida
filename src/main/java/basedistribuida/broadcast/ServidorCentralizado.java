/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author daniel
 */
public class ServidorCentralizado extends Thread {

    public ServidorCentralizado(String name) {
        super(name);
    }

    @Override
    public void run() {

        MulticastSocket mcSocket = null;
        MulticastSocket mcSocket2 = null;
        InetAddress mcIPAddress = null;
        try {
            int mcPort = 12345;
            int mcPort2 = 12346;
            String mcIPStr = "230.1.1.1";
            mcIPAddress = InetAddress.getByName(mcIPStr);
            mcSocket = new MulticastSocket(mcPort);
            mcSocket2 = new MulticastSocket(mcPort2);
            System.out.println("Servidor Centralizado corriendo en :"
                    + mcSocket.getLocalSocketAddress());
            mcSocket.joinGroup(mcIPAddress);

            while (true) {
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

                System.out.println("Esperando por un mensaje...");
                mcSocket.receive(packet);
                String msg = new String(packet.getData(), packet.getOffset(),
                        packet.getLength());
                System.out.println("[Mensaje recibido:" + msg);

                byte[] msgB = msg.getBytes();
                packet = new DatagramPacket(msgB, msgB.length);
                packet.setAddress(mcIPAddress);
                packet.setPort(mcPort2);
                mcSocket2.send(packet);

            }

        } catch (IOException e) {
            if (mcSocket != null) {
                //mcSocket.leaveGroup(mcIPAddress);
                mcSocket.close();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new ServidorCentralizado("server").start();
    }

}
