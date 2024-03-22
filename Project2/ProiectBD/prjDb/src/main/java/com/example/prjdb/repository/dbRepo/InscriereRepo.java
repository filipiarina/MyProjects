package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Inscriere;
import com.example.prjdb.domain.Student;
import com.example.prjdb.domain.Tuple;
import com.example.prjdb.domain.Utilizator;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InscriereRepo implements Repo<Tuple<Integer, Integer>, Inscriere> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public InscriereRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Inscriere> findOne(Tuple<Integer, Integer> id) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM inscriere WHERE student_id = ? AND materie_id=?")){
            statement.setInt(1, id.getLeft());
            statement.setInt(1, id.getRight());
            ResultSet result = statement.executeQuery();
            result.next();
            Integer student_id = result.getInt("student_id");
            Integer materie_id = result.getInt("materie_id");

            Inscriere inscr = new Inscriere(id);
            return Optional.of(inscr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Inscriere> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Inscriere> Inscriere = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from inscriere");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer student_id = resultSet.getInt("student_id");
                Integer materie_id = resultSet.getInt("materie_id");

                Inscriere utilizator = new Inscriere(new Tuple<Integer, Integer>(student_id, materie_id));
                utilizator.setId(new Tuple<Integer, Integer>(student_id, materie_id));
                Inscriere.add(utilizator);
            }
            return Inscriere;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Inscriere;
    }

    @Override
    public Optional<Inscriere> save(Inscriere entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Inscriere> delete(Tuple<Integer, Integer> aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM inscriere WHERE id1 = ? AND id2=?")){
            statement.setInt(1, aLong.getLeft());
            statement.setInt(2, aLong.getRight());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Inscriere> update(Inscriere entity) throws ClassNotFoundException {
        return Optional.empty();
    }
}