package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Grup_studiu;
import com.example.prjdb.domain.Student;
import com.example.prjdb.domain.Utilizator;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GrupStudiuRepo implements Repo<Integer, Grup_studiu> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public GrupStudiuRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Grup_studiu> findOne(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM grup_studiu WHERE id_grup = ?")){
            statement.setInt(1, aLong);
            ResultSet result = statement.executeQuery();
            result.next();

            Integer materie_id = result.getInt("materie_id");

            Grup_studiu grup = new Grup_studiu(aLong, materie_id);
            return Optional.of(grup);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Grup_studiu> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Grup_studiu> Grup = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from grup_studiu");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_grup");
                Integer materie_id = resultSet.getInt("materie_id");

                Grup_studiu grup = new Grup_studiu(id, materie_id);
                grup.setId_grup(id);
                Grup.add(grup);
            }
            return Grup;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Grup;
    }

    @Override
    public Optional<Grup_studiu> save(Grup_studiu entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Grup_studiu> delete(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM grup_studiu WHERE id = ?")){
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Grup_studiu> update(Grup_studiu entity) throws ClassNotFoundException {
        return Optional.empty();
    }
}