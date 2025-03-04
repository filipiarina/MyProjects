package org.example.Server.Model.Repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/BDtema3";
    private static final String USER = "root";
    private static final String PASS = "root";


    private static ConnectionFactory singleInstance = new ConnectionFactory();
    private Connection connection;

    /**

     Constructs a new {@code ConnectionFactory} object.
     It loads the MySQL JDBC driver.
     */

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**

     Creates a connection to the database.
     @return the established database connection
     */

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**

     Retrieves the single instance of the {@code ConnectionFactory}.
     @return the single instance of the connection factory
     */

    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**

     Closes the database connection.
     @param connection the database connection to be closed
     */

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }


    /**

     Closes the statement used for executing SQL queries.
     @param statement the statement to be closed
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**

     Closes the result set obtained from executing an SQL query.
     @param resultSet the result set to be closed
     */

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }

    public ResultSet getTable(String commandSQL) {
        ResultSet resultSet = null;
        try {
            this.createConnection();
            Statement statement = this.connection.createStatement();
            resultSet = statement.executeQuery(commandSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
