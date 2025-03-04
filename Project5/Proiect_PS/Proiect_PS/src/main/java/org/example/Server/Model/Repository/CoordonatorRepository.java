package org.example.Server.Model.Repository;

import org.example.Server.Model.Coordonator;
import org.example.Server.Model.Subject;
import org.example.Server.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoordonatorRepository extends Subject implements UserRepository{
    private Repository repository;

    public CoordonatorRepository() {
        this.repository = new Repository();
    }

    public CoordonatorRepository(String dataURL) {
        this.repository = new Repository(dataURL);
    }

    private Connection connection;

    public CoordonatorRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean addCoordonator(Coordonator coordonator) {
        String commandSQL = "INSERT INTO Coordonator (nume, email, parola) VALUES ('" +
                coordonator.getNume() +
                "','" + coordonator.getEmail() +
                "','" + coordonator.getParola() + "')";
        return this.repository.commandSQL(commandSQL);
    }


    public boolean updateCoordonator(Coordonator coordonator) {
        String commandSQL = "UPDATE Coordonator SET nume = '" + coordonator.getNume() +
                "', email = '" + coordonator.getEmail() +
                "', parola = '" + coordonator.getParola() +
                "' WHERE coordonator_id = " + coordonator.getId();
        return this.repository.commandSQL(commandSQL);
    }


    public boolean deleteCoordonator(int coordonatorId) {
        String commandSQL = "DELETE FROM Coordonator WHERE coordonator_id = " + coordonatorId;
        return this.repository.commandSQL(commandSQL);
    }


    public Coordonator getCoordonatorById(int coordonatorId) {
        String query = "SELECT * FROM Coordonator WHERE coordonator_id = " + coordonatorId;
        ResultSet resultSet = this.repository.getTable(query);
        Coordonator coordonator = null;
        try {
            if (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email");
                String parola = resultSet.getString("parola");
                coordonator = new Coordonator(coordonatorId, nume, email, parola);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordonator;
    }


    public Coordonator getACoordonatorByEmailAndPassword(String email, String parola) {
        String query = "SELECT * FROM Coordonator WHERE email = '" + email + "' AND parola = '" + parola + "'";

        ResultSet resultSet = this.repository.getTable(query);
        Coordonator coordonator = null;
        try {
            if (resultSet != null && resultSet.next()) {
                Integer coordonatorId = resultSet.getInt("coordonator_id");
                String nume = resultSet.getString("nume");
                coordonator = new Coordonator(coordonatorId, nume, email, parola);
            }
        } catch (SQLException e) {
            System.out.println("Nu exista coordonator cu acest email si parola! (" + email + " " + parola + ")");
        }
        return coordonator;
    }


    public List<Coordonator> getAllCoordonators() {
        String query = "SELECT * FROM Coordonator";
        ResultSet resultSet = this.repository.getTable(query);
        List<Coordonator> coordonatorList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int coordonatorId = resultSet.getInt("coordonator_id");
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email");
                String parola = resultSet.getString("parola");
                Coordonator coordonator = new Coordonator(coordonatorId, nume, email, parola);
                coordonatorList.add(coordonator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordonatorList;
    }


    public List<Coordonator> getAllCoordonatoriiOrderedById() {
        String query = "SELECT * FROM Coordonator ORDER BY coordonator_id;";
        ResultSet resultSet = this.repository.getTable(query);
        List<Coordonator> coordonatorList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int coordonatorId = resultSet.getInt("coordonator_id");
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email");
                String parola = resultSet.getString("parola");
                Coordonator coordonator = new Coordonator(coordonatorId, nume, email, parola);
                coordonatorList.add(coordonator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordonatorList;
    }

    public Coordonator getCoordonatorByEmailAndPassword(String email, String parola) {
        String query = "SELECT * FROM Coordonator WHERE email = '" + email + "' AND parola = '" + parola + "'";

        ResultSet resultSet = this.repository.getTable(query);
        Coordonator coordonator = null;
        try {
            if (resultSet != null && resultSet.next()) {
                Integer coordonatorID = resultSet.getInt("coordonator_id");
                String nume = resultSet.getString("nume");
                coordonator = new Coordonator(coordonatorID, nume, email, parola);
            }
        } catch (SQLException e) {
            System.out.println("Coordonatorul cu acest email si parola nu exista! (" + email + " " + parola + ")");
        }
        return coordonator;
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM Coordonator WHERE email = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("parola");
                String role = "Coordonator";
                return new User(email, password, role);
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return null;
    }
}
