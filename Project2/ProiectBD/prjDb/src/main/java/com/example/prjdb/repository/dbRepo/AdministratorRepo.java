package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Activitate_grup;
import com.example.prjdb.domain.Administrator;
import com.example.prjdb.domain.Student;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AdministratorRepo implements Repo<Integer, Administrator> {

    private String driver;
    private String url;
    private String username;
    private String password;

    public AdministratorRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Administrator> findOne(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM administrator WHERE id_administrator = ?")){
            statement.setInt(1, aLong);
            ResultSet result = statement.executeQuery();
            result.next();

            String tip = result.getString("tip");

            Administrator adm = new Administrator(aLong, tip);
            return Optional.of(adm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Administrator> findAll() {
        Set<Administrator> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from administrator");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id_administrator = resultSet.getInt("id_administrator");
                String tip = resultSet.getString("tip");

                Administrator adm = new Administrator(id_administrator, tip);
                adm.setId_administrator(id_administrator);
                users.add(adm);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<Administrator> save(Administrator entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Administrator> delete(Integer aLong) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM administrator WHERE id_administrator = ?")){
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Administrator> update(Administrator entity) {
        return Optional.empty();
    }

}
