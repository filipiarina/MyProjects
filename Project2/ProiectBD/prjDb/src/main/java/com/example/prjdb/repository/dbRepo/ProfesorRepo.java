package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Profesor;
import com.example.prjdb.domain.Student;
import com.example.prjdb.domain.Utilizator;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProfesorRepo implements Repo<Integer, Profesor> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public ProfesorRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Profesor> findOne(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM profesor WHERE id_profesor = ?")){
            statement.setInt(1, aLong);
            ResultSet result = statement.executeQuery();
            result.next();

            int nr_min_ore = result.getInt("nr_min_ore");
            int nr_maxim_ore = result.getInt("nr_maxim_ore");

            String cursuri = result.getString("cursuri");
            String departament = result.getString("departament");

            Profesor utilizator = new Profesor(aLong, nr_min_ore, nr_maxim_ore, cursuri, departament);
            return Optional.of(utilizator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Profesor> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Profesor> Utilizators = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from profesor");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_profesor");
                int nr_min_ore = resultSet.getInt("nr_min_ore");
                int nr_maxim_ore = resultSet.getInt("nr_maxim_ore");

                String cursuri = resultSet.getString("cursuri");
                String departament = resultSet.getString("departament");

                Profesor utilizator = new Profesor(id, nr_min_ore, nr_maxim_ore, cursuri, departament);
                utilizator.setId_profesor(id);
                Utilizators.add(utilizator);
            }
            return Utilizators;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Utilizators;
    }

    @Override
    public Optional<Profesor> save(Profesor entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Profesor> delete(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM profesor WHERE id_profesor = ?")){
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Profesor> update(Profesor Profesor) throws ClassNotFoundException {
        return Optional.empty();
    }
}
