/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Luca Cattaneo
 */
public class Invio {

    public static void Invia(String risposta, InetAddress ip) throws SocketException, IOException {
        DatagramSocket server = new DatagramSocket();
        byte[] responseBuffer = risposta.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
        responsePacket.setAddress(ip);
        responsePacket.setPort(12345);
        System.out.println(risposta);
        server.send(responsePacket);
    }
}
