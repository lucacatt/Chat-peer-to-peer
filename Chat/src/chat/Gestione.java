/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.util.ArrayList;

/**
 *
 * @author Luca Cattaneo
 */
public class Gestione {

    private static Gestione INSTANCE;
    private String nome;
    private String ipDestinatario;
    private ArrayList<String> connnessioniSospese;
    private int portaDestinatario;
    private boolean isConnesso;

    private Gestione(String n) {
        nome = n;
        ipDestinatario = "";
        connnessioniSospese = new ArrayList();
        isConnesso = false;
    }

    public static Gestione getInstance(String n) {
        if (INSTANCE == null) {
            INSTANCE = new Gestione(n);
        }
        return INSTANCE;
    }

    public synchronized void Connessione(String res, int porta, String ip) {
        if (isConnesso) {
            //thread invio occupato
        } else {
            ipDestinatario = ip;
            portaDestinatario = porta;
            isConnesso = true;
            //thread invio libero
        }

    }
}
