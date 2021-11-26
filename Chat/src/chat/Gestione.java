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
import javax.swing.JOptionPane;

/**
 *
 * @author Luca Cattaneo
 */
public class Gestione {

    private static Gestione INSTANCE;
    private String nome;
    private String nomeDestinatario;
    private InetAddress ipDestinatario;
    private ArrayList<String> connnessioniSospese;
    private int portaDestinatario;
    private boolean Connesso;
    private boolean Attesa;
    private boolean Chatting;

    private Gestione(String n, InetAddress ip) throws SocketException {
        nome = n;
        ipDestinatario = ip;
        connnessioniSospese = new ArrayList();
        Connesso = false;
        nomeDestinatario = "";
        Attesa = false;
        Chatting = false;
    }

    public static Gestione getInstance(String n, InetAddress ip) throws SocketException {
        if (INSTANCE == null) {
            INSTANCE = new Gestione(n, ip);
        }
        return INSTANCE;
    }

    public void setIpDestinatario(InetAddress ipDestinatario) {
        this.ipDestinatario = ipDestinatario;
    }

    public synchronized void Connessione(String res, int porta, InetAddress ip) throws IOException {
        if (Attesa) {
            String[] splitted = res.split(";");
            if (ipDestinatario.equals(ip) && splitted[0].equals("y")) {
                Attesa = false;
                Chatting = true;
                nomeDestinatario = splitted[1];
                Invio.Invia("y;", ip);
            } else {
                Invio.Invia("n;", ip);
            }
        } else {
            if (Connesso) {
                Invio.Invia("n;", ip);
            } else {
                String[] splitted = res.split(";");
                if (splitted[0].equals("a")) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Accettare la connessione da " + splitted[1], "Warning", dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        ipDestinatario = ip;
                        portaDestinatario = porta;
                        nomeDestinatario = splitted[1];
                        Connesso = true;
                        Chatting = true;
                        Attesa = false;
                        Invio.Invia("y;" + nome + ";", ip);
                        System.out.println("connesso");
                    } else {
                        Invio.Invia("n;", ip);
                    }

                } else {
                    Invio.Invia("c;", ip);
                }
            }
        }

    }

    public boolean isChatting() {
        return Chatting;
    }

    public InetAddress getIpDestinatario() {
        return ipDestinatario;
    }

    void Connetti() throws IOException {
        Invio.Invia("a;" + nome + ";", ipDestinatario);
        Attesa = true;
    }

    void Disconnetti() throws IOException {
        Invio.Invia("c;", ipDestinatario);
        INSTANCE = new Gestione(nome, null);
    }

    void Disconnessione() throws IOException {
        INSTANCE = new Gestione(nome, null);
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

}
