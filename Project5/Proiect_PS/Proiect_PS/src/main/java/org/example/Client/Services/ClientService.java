package org.example.Client.Services;

import org.example.Client.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientService {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientService(String serverAddress, int serverPort) throws Exception {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        public void sendAttemptCoordonator() throws Exception {
            sendRequest("Attempt Coordonator");
        }

        public void sendAttemptEmployee() throws Exception {
            sendRequest("Attempt Employee");
        }

        public void sendAttemptAdmin() throws Exception {
            sendRequest("Attempt Admin");
        }

        public void sendRequest(String request) throws Exception {
            out.println(request);
        }

        public String readResponse() throws Exception {
            return in.readLine();
        }

        public String sendLoginRequest(String email, String password) throws Exception {
            String loginRequest = "login " + email + " " + password;
            sendRequest(loginRequest);
            String response = readResponse();
            if (response.startsWith("true")) {
                return response.split(" ")[1]; // Returnează rolul
            } else {
                return null; // Autentificare eșuată
            }
        }

        public void close() throws Exception {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        }

}
