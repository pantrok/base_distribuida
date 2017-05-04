/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author daniel
 */
public class BroadcastUtils {

    public static void mensajeAServidorRemoto(String mensaje) {
        int mcPort = 12345;
        String mcIPStr = "230.1.1.1";
        try (DatagramSocket udpSocket = new DatagramSocket()) {
            InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
            byte[] msg = mensaje.getBytes();
            DatagramPacket packet = new DatagramPacket(msg, msg.length);
            packet.setAddress(mcIPAddress);
            packet.setPort(mcPort);
            udpSocket.send(packet);
            System.out.println("Mensaje enviado a servidor remoto.");
            udpSocket.close();
        } catch (UnknownHostException e) {
            System.out.println("Error al enviar mensaje a servidor remoto");
        } catch (IOException e) {
            System.out.println("Error al enviar mensaje a servidor remoto");
        }
    }

}
