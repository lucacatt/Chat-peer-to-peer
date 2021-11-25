/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Luca Cattaneo
 */
public class Messaggi {

    public ArrayList<String> Chat;
    private static Messaggi INSTANCE;
    private JFrame frame;

    private Messaggi(JFrame f) {
        Chat = new ArrayList();
        frame = f;

    }

    public static Messaggi getInstance(JFrame frame) throws SocketException {
        if (INSTANCE == null) {
            INSTANCE = new Messaggi(frame);
        }
        return INSTANCE;
    }

    public synchronized void Aggiugi(String mess) {
        Chat.add(mess);
        frame.paint(frame.getGraphics());
    }

    public void Disconnessione() {
        Chat = new ArrayList();
    }

}
