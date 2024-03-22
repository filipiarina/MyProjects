package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Note_finale;
import com.example.prjdb.domain.Tuple;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class NoteFinaleRepo implements Repo<Tuple<Integer, Integer>, Note_finale> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public NoteFinaleRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Note_finale> findOne(Tuple<Integer, Integer> aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM note_finale WHERE student_id = ? AND materie_id=?")){
            statement.setInt(1, aLong.getLeft());
            statement.setInt(2, aLong.getRight());
            ResultSet result = statement.executeQuery();
            result.next();
            float nota_finala = result.getFloat("nota_finala");
            Integer student_id = result.getInt("student_id");
            Integer activitate_id = result.getInt("materie_id");

            Note_finale notef = new Note_finale(nota_finala, aLong);
            return Optional.of(notef);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Note_finale> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Note_finale> NoteF = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from note_finale");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                float nota_finala = resultSet.getFloat("nota_finala");
                Integer student_id = resultSet.getInt("student_id");
                Integer activitate_id = resultSet.getInt("materie_id");

                Note_finale notef = new Note_finale(nota_finala, new Tuple<Integer, Integer>(student_id, activitate_id));
                notef.setId(new Tuple<Integer, Integer>(student_id, activitate_id));
                NoteF.add(notef);
            }
            return NoteF;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NoteF;
    }

    @Override
    public Optional<Note_finale> save(Note_finale entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Note_finale> delete(Tuple<Integer, Integer> aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM note_finale WHERE id1 = ? AND id2=?")){
            statement.setInt(1, aLong.getLeft());
            statement.setInt(1, aLong.getRight());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Note_finale> update(Note_finale entity) throws ClassNotFoundException {
        return Optional.empty();
    }
}