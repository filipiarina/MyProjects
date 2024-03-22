package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Student;
import com.example.prjdb.domain.Utilizator;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class StudentRepo implements Repo<Integer, Student> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public StudentRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Student> findOne(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM student WHERE id_student = ?")){
            statement.setInt(1, aLong);
            ResultSet result = statement.executeQuery();
            result.next();
            String an_studiu = result.getString("an_studiu");
            Integer grup_id = result.getInt("grup_id");

            Student utilizator = new Student(aLong, an_studiu, grup_id);
            return Optional.of(utilizator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Student> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Student> Utilizators = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from student");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_student");
                String an_studiu = resultSet.getString("an_studiu");
                Integer grup_id = resultSet.getInt("grup_id");

                Student utilizator = new Student(id, an_studiu, grup_id);
                utilizator.setId_student(id);
                Utilizators.add(utilizator);
            }
            return Utilizators;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Utilizators;
    }

    @Override
    public Optional<Student> save(Student entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Student> delete(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM student WHERE id_student = ?")){
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Student> update(Student entity) throws ClassNotFoundException {
        return Optional.empty();
    }
}