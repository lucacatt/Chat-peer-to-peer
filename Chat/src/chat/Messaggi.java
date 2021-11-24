/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Luca Cattaneo
 */
public class Messaggi {

    private ArrayList<String> Messaggi;
    private static Messaggi INSTANCE;

    private Messaggi() {
        Messaggi = new ArrayList();
    }
    public static Messaggi getInstance() throws SocketException {
        if (INSTANCE == null) {
            INSTANCE = new Messaggi();
        }
        return INSTANCE;
    }
}
