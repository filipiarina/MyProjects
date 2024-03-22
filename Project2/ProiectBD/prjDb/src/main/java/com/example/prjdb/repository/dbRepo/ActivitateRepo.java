package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Activitate;
import com.example.prjdb.domain.Student;
import com.example.prjdb.domain.Utilizator;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ActivitateRepo implements Repo<Integer, Activitate> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public ActivitateRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Activitate> findOne(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM activitate WHERE id_activitate = ?")){
            statement.setInt(1, aLong);
            ResultSet result = statement.executeQuery();
            result.next();
            Integer id = result.getInt("id_activitate");
            Integer materie_id = result.getInt("materie_id");
            String tip_activitate = result.getString("tip_activitate");
            Boolean colocviu = result.getBoolean("cologviu");
            Boolean examen = result.getBoolean("examen");
            String procent = result.getString("procent");
            Integer nr_maxim_participanti = result.getInt("nr_maxim_participanti");


            Activitate utilizator = new Activitate(aLong,materie_id, tip_activitate, colocviu, examen,procent, nr_maxim_participanti);
            return Optional.of(utilizator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Activitate> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Activitate> Act = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from activitate");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_activitate");
                Integer materie_id = resultSet.getInt("materie_id");
                String tip_activitate = resultSet.getString("tip_activitate");
                Boolean colocviu = resultSet.getBoolean("cologviu");
                Boolean examen = resultSet.getBoolean("examen");
                String procent = resultSet.getString("procent");
                Integer nr_maxim_participanti = resultSet.getInt("nr_maxim_participanti");
                Activitate act = new Activitate(id, materie_id, tip_activitate, colocviu, examen,procent, nr_maxim_participanti);
                act.setId_activitate(id);
                Act.add(act);
            }
            return Act;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Act;
    }

    @Override
    public Optional<Activitate> save(Activitate entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Activitate> delete(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM activitate WHERE id = ?")){
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Activitate> update(Activitate entity) throws ClassNotFoundException {
        return Optional.empty();
    }
}