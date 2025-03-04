package org.example.Server.Model.Repository;

import org.example.Server.Model.Administrator;
import org.example.Server.Model.Angajat;
import org.example.Server.Model.Subject;
import org.example.Server.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AdministratorRepository extends Subject implements  UserRepository{
    private Connection connection;
    private Repository repository;

    public AdministratorRepository() {
        connection = ConnectionFactory.getConnection();
    }

    public AdministratorRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean saveAngajat(Angajat angajat) {
        int rowsInserted = -1;
        String query = "INSERT INTO Angajat (nume, email, parola, departament, aniExperienta) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, angajat.getNume());
            statement.setString(2, angajat.getEmail());
            statement.setString(3, angajat.getParola());
            statement.setString(4, angajat.getDepartament());
            statement.setInt(5, angajat.getAniExperienta());

            rowsInserted = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (rowsInserted != -1) {
            notifyObservers();
        }

        return rowsInserted > 0;
    }

    public boolean updateAngajat(Angajat angajat) {
        int rowsUpdated = -1;
        String query = "UPDATE Angajat SET nume = ?, email = ?, parola = ?, telefon = ?, aniExperienta = ? WHERE angajat_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, angajat.getNume());
            statement.setString(2, angajat.getEmail());
            statement.setString(3, angajat.getParola());
            statement.setString(4, angajat.getDepartament());
            statement.setInt(5, angajat.getAniExperienta());
            statement.setInt(6, angajat.getId());

            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (rowsUpdated != -1) {
            notifyObservers();
        }

        return rowsUpdated > 0;
    }

    public boolean deleteAngajat(int angajatId) {
        int rowsDeleted = -1;
        String query = "DELETE FROM Angajat WHERE angajat_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, angajatId);

            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (rowsDeleted != -1) {
            notifyObservers();
        }

        return rowsDeleted > 0;
    }

   /* public Angajat getAngajatById(int angajatId) {
        String query = "SELECT * FROM Angajat WHERE angajat_id = ?";
        Angajat angajat = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, angajatId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    angajat = new Angajat();
                    angajat.setId(resultSet.getInt("angajat_id"));
                    angajat.setNume(resultSet.getString("nume"));
                    angajat.setEmail(resultSet.getString("email"));
                    angajat.setParola(resultSet.getString("parola"));
                    angajat.setDepartament(resultSet.getString("departament"));
                    angajat.setAniExperienta(resultSet.getInt("aniExperienta"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return angajat;
    }

    public List<Angajat> getAllAngajati() {
        List<Angajat> angajati = new ArrayList<>();
        String query = "SELECT * FROM Angajat";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Angajat angajat = new Angajat();
                angajat.setId(resultSet.getInt("angajat_id"));
                angajat.setNume(resultSet.getString("nume"));
                angajat.setEmail(resultSet.getString("email"));
                angajat.setParola(resultSet.getString("parola"));
                angajat.setDepartament(resultSet.getString("departament"));
                angajat.setAniExperienta(resultSet.getInt("aniExperienta"));
                angajati.add(angajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return angajati;
    }*/

    public Administrator getAdministratorByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM Administrator WHERE email = ? AND parola = ?";
        Administrator administrator = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int administratorId = resultSet.getInt("admin_id");
                    String nume = resultSet.getString("nume");
                    String telefon = resultSet.getString("telefon");
                    administrator = new Administrator(administratorId, nume, email, password, telefon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administrator;
    }



    public List<Administrator> getAllAdministrators() {
        List<Administrator> administrators = new ArrayList<>();
        String query = "SELECT * FROM Administrator";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int administratorId = resultSet.getInt("admin_id");
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email");
                String password = resultSet.getString("parola");
                String telefon = resultSet.getString("telefon");

                Administrator administrator = new Administrator(administratorId, nume, email, password, telefon);
                administrators.add(administrator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administrators;
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM Administrator WHERE email = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("parola");
                String role = "Administrator";
                return new User(email, password, role);
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("role")
            ));
        }
        return users;
    }

}

