package org.example.Server.Model.Repository;

import org.example.Server.Model.Angajat;
import org.example.Server.Model.LocatieDeseu;
import org.example.Server.Model.Subject;
import org.example.Server.Model.User;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AngajatRepository extends Subject implements UserRepository{
    private Repository repository;

    public AngajatRepository() {
        this.repository = new Repository();
    }

    public AngajatRepository(String dataURL) {
        this.repository = new Repository(dataURL);
    }

    private Connection connection;

    public AngajatRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean addAngajat(Angajat angajat) {
        String commandSQL = "INSERT INTO Angajat (nume, email, parola, departament, aniExperienta) VALUES ('" +
                angajat.getNume() +
                "','" + angajat.getEmail() +
                "','" + angajat.getParola() +
                "','" + angajat.getDepartament() +
                "','" + angajat.getAniExperienta() + "')";
        return this.repository.commandSQL(commandSQL);
    }


    public boolean updateAngajat(Angajat angajat) {
        String commandSQL = "UPDATE Angajat SET nume = '" + angajat.getNume() +
                "', email = '" + angajat.getEmail() +
                "', parola = '" + angajat.getParola() +
                "', departament = '" + angajat.getDepartament() +
                "', aniExperienta = '" + angajat.getAniExperienta() +
                "' WHERE angajat_id = " + angajat.getId();
        return this.repository.commandSQL(commandSQL);
    }


    public boolean deleteAngajat(int angajatId) {
        String commandSQL = "DELETE FROM Angajat WHERE angajat_id = " + angajatId;
        return this.repository.commandSQL(commandSQL);
    }


    public Angajat getAngajatById(int angajatId) {
        String query = "SELECT * FROM Angajat WHERE angajat_id = " + angajatId;
        ResultSet resultSet = this.repository.getTable(query);
        Angajat angajat = null;
        try {
            if (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email");
                String departament = resultSet.getString("departament");
                String parola = resultSet.getString("parola");
                Integer aniExperienta = resultSet.getInt("aniExperienta");
                angajat = new Angajat(angajatId, nume, email, parola, departament, aniExperienta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return angajat;
    }


    public Angajat getAngajatByEmailAndPassword(String email, String parola) {
        String query = "SELECT * FROM Angajat WHERE email = '" + email + "' AND parola = '" + parola + "'";
        ResultSet resultSet = this.repository.getTable(query);
        Angajat angajat = null;
        try {
            if (resultSet.next()) {
                Integer angajatId = resultSet.getInt("angajat_id");
                String nume = resultSet.getString("nume");
                String departament = resultSet.getString("departament");
                Integer aniExperienta = resultSet.getInt("aniExperienta");
                angajat = new Angajat(angajatId, nume, email, parola, departament, aniExperienta);
            }
        } catch (SQLException e) {
            System.out.println("Nu exista angajatul cu acest email si parola! (" + email + " " + parola + ")");
        }
        return angajat;
    }


    public List<Angajat> getAllAngajati() {
        String query = "SELECT * FROM Angajat;";
        ResultSet resultSet = this.repository.getTable(query);
        List<Angajat> angajatList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int angajatId = resultSet.getInt("angajat_id");
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email");
                String departament = resultSet.getString("departament");
                String parola = resultSet.getString("parola");
                Integer aniExperienta = resultSet.getInt("aniExperienta");
                Angajat angajat = new Angajat(angajatId, nume, email, parola, departament, aniExperienta);
                angajatList.add(angajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return angajatList;
    }


    public List<Angajat> getAllAngajatiOrderedById() {
        String query = "SELECT * FROM Angajat ORDER BY angajat_id;";
        ResultSet resultSet = this.repository.getTable(query);
        List<Angajat> angajatList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int angajatId = resultSet.getInt("angajat_id");
                String nume = resultSet.getString("nume");
                String email = resultSet.getString("email");
                String departament = resultSet.getString("departament");
                String parola = resultSet.getString("parola");
                Integer aniExperienta = resultSet.getInt("aniExperienta");
                Angajat angajat = new Angajat(angajatId, nume, email, parola, departament, aniExperienta);
                angajatList.add(angajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.repository.closingConnection();
        return angajatList;
    }

    public DefaultTableModel creazaModelTabelAngajati() {
        DefaultTableModel model = new DefaultTableModel();

        // Adăugăm coloanele în modelul tabelului
        model.addColumn("ID Angajat");
        model.addColumn("Nume");
        model.addColumn("Email");
        model.addColumn("Departament");
        model.addColumn("Ani Experienta");

        // Adăugăm datele angajaților în modelul tabelului
        List<Angajat> listaAngajati = getAllAngajati();
        for (Angajat angajat : listaAngajati) {
            Vector<Object> rand = new Vector<>();
            rand.add(angajat.getId()); // ID Angajat
            rand.add(angajat.getNume()); // Nume
            rand.add(angajat.getEmail()); // Email
            rand.add(angajat.getDepartament()); // Departament
            rand.add(angajat.getAniExperienta()); // Ani Experienta
            model.addRow(rand);
        }

        return model;
    }

    public List<LocatieDeseu> getAllLocatiiDeseuri() {
        String query = "SELECT * FROM LocatieDeseu";
        ResultSet resultSet = this.repository.getTable(query);
        List<LocatieDeseu> locatiiDeseuri = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idLocatie");
                String adresa = resultSet.getString("adresaLocatie");
                String tipDeseu = resultSet.getString("tipDeseu");
                String stadiu = resultSet.getString("stadiu");

                // Creăm un obiect LocatieDeseuri și îl adăugăm în lista de rezultate
                LocatieDeseu locatie = new LocatieDeseu(id, adresa, tipDeseu, stadiu);
                locatiiDeseuri.add(locatie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locatiiDeseuri;
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM Angajat WHERE email = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("parola");
                String role = "Angajat";
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
