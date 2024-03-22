package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Materie;
import com.example.prjdb.domain.Student;
import com.example.prjdb.domain.Utilizator;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MaterieRepo implements Repo<Integer, Materie> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public MaterieRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Materie> findOne(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM materie WHERE id_materie = ?")){
            statement.setInt(1, aLong);
            ResultSet result = statement.executeQuery();
            result.next();
            Integer profesor_id = result.getInt("profesor_id");
            String denumire_materie = result.getString("denumire_materie");
            Boolean curs = result.getBoolean("curs");
            Boolean seminar = result.getBoolean("seminar");
            Boolean laborator = result.getBoolean("laborator");

            Materie mat = new Materie(aLong, profesor_id, denumire_materie, curs, seminar, laborator);
            return Optional.of(mat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Materie> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Materie> Materii = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from materie");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_materie");
                Integer profesor_id = resultSet.getInt("profesor_id");
                String denumire_materie = resultSet.getString("denumire_materie");
                Boolean curs = resultSet.getBoolean("curs");
                Boolean seminar = resultSet.getBoolean("seminar");
                Boolean laborator = resultSet.getBoolean("laborator");

                Materie mat = new Materie(id, profesor_id, denumire_materie, curs, seminar, laborator);
                mat.setId_materie(id);
                Materii.add(mat);
            }
            return Materii;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Materii;
    }

    @Override
    public Optional<Materie> save(Materie entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Materie> delete(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM materie WHERE id = ?")){
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Materie> update(Materie entity) throws ClassNotFoundException {
        return Optional.empty();
    }
}