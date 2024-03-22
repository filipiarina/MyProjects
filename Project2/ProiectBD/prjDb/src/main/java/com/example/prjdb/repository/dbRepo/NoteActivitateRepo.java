package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Note_activitate;
import com.example.prjdb.domain.Tuple;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class NoteActivitateRepo implements Repo<Tuple<Integer, Integer>, Note_activitate> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public NoteActivitateRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Note_activitate> findOne(Tuple<Integer, Integer> aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM note_activitate WHERE id1 = ? AND id2=?")){
            statement.setInt(1, aLong.getLeft());
            statement.setInt(2, aLong.getRight());
            ResultSet result = statement.executeQuery();
            result.next();
            float nota = result.getFloat("nota");
            Integer student_id = result.getInt("student_id");
            Integer activitate_id = result.getInt("activitate_id");

            Note_activitate notea = new Note_activitate(nota, aLong);
            return Optional.of(notea);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Note_activitate> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Note_activitate> NoteA = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from note_activitate");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                float nota = resultSet.getFloat("nota");
                Integer student_id = resultSet.getInt("student_id");
                Integer activitate_id = resultSet.getInt("activitate_id");

                Note_activitate notea = new Note_activitate(nota, new Tuple<Integer, Integer>(student_id,activitate_id));
                notea.setId(new Tuple<Integer, Integer>(student_id,activitate_id));
                NoteA.add(notea);
            }
            return NoteA;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NoteA;
    }

    @Override
    public Optional<Note_activitate> save(Note_activitate entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Note_activitate> delete(Tuple<Integer, Integer> aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM note_activitate WHERE id1 = ? AND id2=?")){
            statement.setInt(1, aLong.getLeft());
            statement.setInt(2, aLong.getRight());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Note_activitate> update(Note_activitate entity) throws ClassNotFoundException {
        return Optional.empty();
    }}
