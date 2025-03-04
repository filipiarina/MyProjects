package org.example.Server.Model.Repository;
import org.example.Server.Model.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository extends Subject {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/";
    private static final String TIMEZONE = "?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static String BAZA;

    private Connection connection;

    public Repository() {
        try {
            Class.forName(DRIVER);
            BAZA = "BDtema3";
            String dbUrl = DBURL + BAZA + TIMEZONE;
            this.connection = DriverManager.getConnection(dbUrl, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Repository(String dataBaseName) {
        try {
            Class.forName(DRIVER);
            BAZA = dataBaseName;
            String dbUrl = DBURL + dataBaseName + TIMEZONE;
            this.connection = DriverManager.getConnection(dbUrl, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void openingConnection() {
        try {
            if (this.connection != null && this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(DBURL + BAZA + TIMEZONE, USER, PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closingConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean commandSQL(String commandSQL) {
        boolean result = true;
        try {
            this.openingConnection();
            Statement statement = this.connection.createStatement();
            if (statement.executeUpdate(commandSQL) == 0) {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        } finally {
            this.closingConnection();
        }
        return result;
    }

    public ResultSet getTable(String commandSQL) {
        ResultSet resultSet = null;
        try {
            this.openingConnection();
            Statement statement = this.connection.createStatement();
            resultSet = statement.executeQuery(commandSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}