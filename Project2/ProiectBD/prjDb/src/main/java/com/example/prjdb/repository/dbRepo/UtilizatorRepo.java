package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Student;
import com.example.prjdb.domain.Utilizator;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UtilizatorRepo implements Repo<Integer, Utilizator> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public UtilizatorRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Utilizator> findOne(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilizator WHERE id_user = ?")){
            statement.setInt(1, aLong);
            ResultSet result = statement.executeQuery();
            result.next();

            String CNP = result.getString("CNP");
            String nume = result.getString("nume");
            String prenume = result.getString("prenume");
            String adresa = result.getString("adresa");
            String email = result.getString("email");
            String parola = result.getString("parola");
            String nr_telefon = result.getString("nr_telefon");
            String cont_iban = result.getString("cont_iban");
            String nr_cont = result.getString("nr_cont");
            String nr_contact = result.getString("nr_contact");
            String tip = result.getString("tip");

            Utilizator utilizator = new Utilizator(aLong, CNP, nume, prenume, adresa, email, parola, nr_telefon,
                    cont_iban,nr_cont,nr_contact,tip);
            return Optional.of(utilizator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Utilizator> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Utilizator> Utilizators = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from utilizator");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_user");
                String CNP = resultSet.getString("CNP");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String adresa = resultSet.getString("adresa");
                String email = resultSet.getString("email");
                String parola = resultSet.getString("parola");
                String nr_telefon = resultSet.getString("nr_telefon");
                String cont_iban = resultSet.getString("cont_iban");
                String nr_cont = resultSet.getString("nr_cont");
                String nr_contact = resultSet.getString("nr_contact");
                String tip = resultSet.getString("tip");

                Utilizator utilizator = new Utilizator(id, CNP, nume, prenume, adresa, email, parola, nr_telefon,
                        cont_iban,nr_cont,nr_contact,tip);
                utilizator.setId_user(id);
                Utilizators.add(utilizator);
            }
            return Utilizators;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Utilizators;
    }

    @Override
    public Optional<Utilizator> save(Utilizator entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Utilizator> delete(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM utilizator WHERE id = ?")){
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Utilizator> update(Utilizator entity) throws ClassNotFoundException {
        return Optional.empty();
    }
}