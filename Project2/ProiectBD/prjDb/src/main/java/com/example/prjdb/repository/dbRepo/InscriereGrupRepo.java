package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Inscriere_grup;
import com.example.prjdb.domain.Tuple;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InscriereGrupRepo implements Repo<Tuple<Integer, Integer>, Inscriere_grup> {
    private String driver;
    private String url;
    private String username;
    private String password;

    public InscriereGrupRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Inscriere_grup> findOne(Tuple<Integer, Integer> aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM inscriere_grup WHERE student_id = ? AND grup_id=?")){
            statement.setInt(1, aLong.getLeft());
            statement.setInt(2, aLong.getRight());
            ResultSet result = statement.executeQuery();
            result.next();
            Integer student_id = result.getInt("student_id");
            Integer grup_id = result.getInt("grup_id");

            Inscriere_grup inscrieregrup = new Inscriere_grup(aLong);
            return Optional.of(inscrieregrup);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Inscriere_grup> findAll() throws ClassNotFoundException {
        Class.forName(driver);
        Set<Inscriere_grup> InscriereGrup = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from inscriere_grup");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer student_id = resultSet.getInt("student_id");
                Integer grup_id = resultSet.getInt("grup_id");

                Inscriere_grup inscrieregrup = new Inscriere_grup(new Tuple<Integer, Integer>(student_id, grup_id));
                inscrieregrup.setId(new Tuple<Integer, Integer>(student_id, grup_id));
                InscriereGrup.add(inscrieregrup);
            }
            return InscriereGrup;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return InscriereGrup;
    }

    @Override
    public Optional<Inscriere_grup> save(Inscriere_grup entity) throws ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public Optional<Inscriere_grup> delete(Tuple<Integer, Integer> aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM inscriere_grup WHERE id1 = ? AND id2=?")){
            statement.setInt(1, aLong.getLeft());
            statement.setInt(2, aLong.getRight());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Inscriere_grup> update(Inscriere_grup entity) throws ClassNotFoundException {
        return Optional.empty();
    }
}