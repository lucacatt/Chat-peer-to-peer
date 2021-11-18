/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Luca Cattaneo
 */
public class Gestione {

    private static Gestione INSTANCE;
    private String nome;
    private InetAddress ipDestinatario;
    private ArrayList<String> connnessioniSospese;
    private int portaDestinatario;
    private boolean isConnesso;

    private Gestione(String n, InetAddress ip) throws SocketException {
        nome = n;
        ipDestinatario = null;
        connnessioniSospese = new ArrayList();
        isConnesso = false;
    }

    public static Gestione getInstance(String n, InetAddress ip) throws SocketException {
        if (INSTANCE == null) {
            INSTANCE = new Gestione(n, ip);
        }
        return INSTANCE;
    }

    public synchronized void Connessione(String res, int porta, InetAddress ip) throws IOException {
        if (isConnesso) {
            Invio.Invia("n;", ip);
        } else {
            ipDestinatario = ip;
            portaDestinatario = porta;
            isConnesso = true;
            Invio.Invia("y;", ip);
        }

    }

    void Connetti() {
        
    }
    
}
