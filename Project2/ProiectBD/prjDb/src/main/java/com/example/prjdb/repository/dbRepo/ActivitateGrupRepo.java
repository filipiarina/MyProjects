package com.example.prjdb.repository.dbRepo;

import com.example.prjdb.domain.Activitate_grup;
import com.example.prjdb.domain.Grup_studiu;
import com.example.prjdb.domain.Student;
import com.example.prjdb.domain.Utilizator;
import com.example.prjdb.repository.Repo;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ActivitateGrupRepo implements Repo<Integer, Activitate_grup> {

    private String driver;
    private String url;
    private String username;
    private String password;

    public ActivitateGrupRepo(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<Activitate_grup> findOne(Integer aLong) throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM activitate_grup WHERE id_activitate_grup = ?")){
            statement.setInt(1, aLong);
            ResultSet result = statement.executeQuery();
            result.next();

            String descriere_activitate = result.getString("descriere_activitate");
            String data_activitate = result.getString("data_activitate");
            String ora_inceput_activitate = result.getString("ora_inceput_activitate");
            String durata_activitate = result.getString("durata_activitate");
            Integer nr_min_participanti = result.getInt("nr_min_participanti");
            String durata_exp_accept = result.getString("durata_exp_accept");
            Integer student_id = result.getInt("student_id");
            Integer grup_id = result.getInt("grup_id");
            Activitate_grup user = new Activitate_grup(aLong, descriere_activitate, data_activitate,
                    ora_inceput_activitate,durata_activitate, nr_min_participanti,durata_exp_accept,student_id,
                    grup_id);
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Activitate_grup> findAll() {
        Set<Activitate_grup> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from activitate_grup");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id_activitate_grup = resultSet.getInt("id_activitate_grup");
                String descriere_activitate = resultSet.getString("descriere_activitate");
                String data_activitate = resultSet.getString("data_activitate");
                String ora_inceput_activitate = resultSet.getString("ora_inceput_activitate");
                String durata_activitate = resultSet.getString("durata_activitate");
                Integer nr_min_participanti = resultSet.getInt("nr_min_participanti");
                String durata_exp_accept = resultSet.getString("durata_exp_accept");
                Integer student_id = resultSet.getInt("student_id");
                Integer grup_id = resultSet.getInt("grup_id");
                Activitate_grup user = new Activitate_grup(id_activitate_grup, descriere_activitate, data_activitate,
                        ora_inceput_activitate,durata_activitate, nr_min_participanti,durata_exp_accept,student_id,
                        grup_id);
                user.setId_activitate_grup(id_activitate_grup);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<Activitate_grup> save(Activitate_grup entity) {
        String sql = "insert into users (descriere_activitate,data_activitate,ora_inceput_activitate,durata_activitate,nr_min_participanti,durata_exp_accept,student_id,grup_id) values (?, ?, ?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getDescriere_activitate());
            ps.setString(2, entity.getData_activitate());
            ps.setString(3, entity.getOra_inceput_activitate());
            ps.setString(4, entity.getDurata_activitate());
            ps.setInt(5, entity.getNr_min_participanti());
            ps.setString(6, entity.getDurata_exp_accept());
            ps.setInt(7, entity.getStudent_id());
            ps.setInt(8, entity.getGrup_id());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Activitate_grup> delete(Integer aLong) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM activitate_grup WHERE id = ?")){
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Activitate_grup> update(Activitate_grup entity) {
        return Optional.empty();
    }

}
