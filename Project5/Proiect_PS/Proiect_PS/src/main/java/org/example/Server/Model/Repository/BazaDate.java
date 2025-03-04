package org.example.Server.Model.Repository;

import org.example.Server.Model.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BazaDate extends Subject {
    private static final String url = "jdbc:mysql://localhost:3306/BDtema3";
    private static final String user = "root";
    private static final String password = "root";


    public static Connection conectareBD() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexiunea la baza de date a fost realizată cu succes!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Nu s-a putut realiza conexiunea la baza de date.");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = conectareBD();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
