package org.example.Server;

import org.example.Server.Model.User;
import org.example.Server.Services.ServerService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        System.out.println("Main-ul Server");

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started and listening on port 12345");
            ServerService serverService = new ServerService();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                BufferedReader in = null;
                PrintWriter out = null;

                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received from client: " + inputLine);
                        if (inputLine.startsWith("login")) {
                            String[] parts = inputLine.split(" ");
                            String email = parts[1];
                            String password = parts[2];
                            User user = serverService.authenticateUser(email, password);
                            if (user != null) {
                                out.println("true " + user.getRole());
                            } else {
                                out.println("false");
                            }
                        } else if (inputLine.equals("Attempt Visitor")) {
                            out.println("Clientul este Vizitator");
                        } else if (inputLine.equals("Attempt Employee")) {
                            out.println("Clientul este Angajat");
                        } else if (inputLine.equals("Attempt Admin")) {
                            out.println("Clientul este Admin");
                        } else {
                            out.println("Unknown command");
                        }
                    }
                } catch (SocketException e) {
                    System.out.println("Client disconnected");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (in != null) in.close();
                        if (out != null) out.close();
                        clientSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}