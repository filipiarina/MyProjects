package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Mesaj;
import com.example.prjdb.domain.Student;
import com.example.prjdb.domain.Utilizator;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MesajRepo implements Repo<Integer, Mesaj> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public MesajRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Mesaj> findOne(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM mesaj WHERE id = ?")){
            statement.setInt(1, aLong);
            ResultSet result = statement.executeQuery();
            result.next();
            String continut = result.getString("continut");
            Integer student_id = result.getInt("student_id");
            Integer grup_id = result.getInt("grup_id");

            Mesaj msj = new Mesaj(aLong, continut,student_id, grup_id);
            return Optional.of(msj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Mesaj> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Mesaj> Mesaje = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from mesaj");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_mesaj");
                String continut = resultSet.getString("continut");
                Integer student_id = resultSet.getInt("student_id");
                Integer grup_id = resultSet.getInt("grup_id");

                Mesaj msj = new Mesaj(id, continut, student_id, grup_id);
                msj.setId_mesaj(id);
                Mesaje.add(msj);
            }
            return Mesaje;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Mesaje;
    }

    @Override
    public Optional<Mesaj> save(Mesaj entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Mesaj> delete(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM mesaj WHERE id = ?")){
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Mesaj> update(Mesaj entity) throws ClassNotFoundException {
        return Optional.empty();
    }
}