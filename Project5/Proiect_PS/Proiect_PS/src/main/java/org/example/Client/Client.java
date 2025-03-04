package org.example.Client;

import org.example.Client.Controller.AutentificareController;
import org.example.Client.Services.ClientService;
import org.example.Client.View.AutentificareGUI;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            ClientService clientService = new ClientService("localhost", 12345);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    AutentificareGUI autentificareGUI = new AutentificareGUI();
                    new AutentificareController(autentificareGUI, clientService);
                    autentificareGUI.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
