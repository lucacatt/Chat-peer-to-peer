/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca Cattaneo
 */
public class ThreadAscolto extends Thread {

    public ThreadAscolto() {
    }

    @Override
    public void run() {
        while (true) {
            DatagramSocket server = null;
            try {
                server = new DatagramSocket(420);
            } catch (SocketException ex) {
                Logger.getLogger(ThreadAscolto.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] buffer = new byte[1500];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                try {
                    server.receive(packet);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadAscolto.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte[] dataReceived = packet.getData(); // copia del buffer dichiarato sopra
                String messaggioRicevuto = new String(dataReceived, 0, packet.getLength());
                String res = messaggioRicevuto.trim();
                int porta = packet.getPort();
                String ip = packet.getAddress().toString();
                Gestione.getInstance("").Connessione(res, porta, ip);
            }
        }
    }
}
