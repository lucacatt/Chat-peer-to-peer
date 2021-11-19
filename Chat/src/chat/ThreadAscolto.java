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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca Cattaneo
 */
public class ThreadAscolto extends Thread {

    private DatagramSocket server;

    public ThreadAscolto() throws SocketException {
        server = new DatagramSocket(12345);
    }

    @Override
    public void run() {
        while (true) {
            byte[] buffer = new byte[1500];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                try {
                    server.receive(packet);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadAscolto.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte[] dataReceived = packet.getData();
                String messaggioRicevuto = new String(dataReceived, 0, packet.getLength());
                String res = messaggioRicevuto.trim();
                int porta = packet.getPort();
                InetAddress ip = packet.getAddress();
                System.out.println(ip.toString());
                try {
                    Gestione.getInstance("", ip).Connessione(res, porta, ip);
                } catch (SocketException ex) {
                    Logger.getLogger(ThreadAscolto.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadAscolto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
