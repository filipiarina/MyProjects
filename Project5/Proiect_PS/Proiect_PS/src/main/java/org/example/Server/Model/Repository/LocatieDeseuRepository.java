package org.example.Server.Model.Repository;

import org.example.Server.Model.LocatieDeseu;
import org.example.Server.Model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocatieDeseuRepository extends Subject {
    private Repository repository;
    private Connection connection;

    public LocatieDeseuRepository() {
        this.repository = new Repository();
        this.connection = ConnectionFactory.getConnection();
    }

    public LocatieDeseuRepository(String dataURL) {
        this.repository = new Repository(dataURL);
    }


    public boolean addLocatieDeseu(LocatieDeseu locatieDeseu) {
        String commandSQL = "INSERT INTO LocatieDeseu (adresaLocatie, tipDeseu, stadiu) VALUES ('" +
                locatieDeseu.getAdresaLocatie() +
                "','" + locatieDeseu.getTipDeseu() +
                "','" + locatieDeseu.getStadiu() + "')";
        return this.repository.commandSQL(commandSQL);
    }


    public boolean updateLocatieDeseu(LocatieDeseu locatieDeseu) {
        String commandSQL = "UPDATE LocatieDeseu SET adresaLocatie = '" + locatieDeseu.getAdresaLocatie() +
                "', tipDeseu = '" + locatieDeseu.getTipDeseu() +
                "', stadiu = '" + locatieDeseu.getStadiu() +
                "' WHERE idLocatie = " + locatieDeseu.getIdLocatie();
        return this.repository.commandSQL(commandSQL);
    }

    public boolean updateStadiuLocatieDeseu(int idLocatieDeseu) {
        String commandSQL = "UPDATE LocatieDeseu SET stadiu = 'alocat/necolectat' WHERE idLocatie = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(commandSQL)) {
            preparedStatement.setInt(1, idLocatieDeseu);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStadiuLocatieDeseu1(int idLocatieDeseu) {
        String commandSQL = "UPDATE LocatieDeseu SET stadiu = 'colectat' WHERE idLocatie = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(commandSQL)) {
            preparedStatement.setInt(1, idLocatieDeseu);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deleteLocatieDeseu(int idLocatieDeseu) {
        String commandSQL = "DELETE FROM LocatieDeseu WHERE idLocatie = " + idLocatieDeseu;
        return this.repository.commandSQL(commandSQL);
    }


    public LocatieDeseu getLocatieDeseuById(int idLocatieDeseu) {
        String query = "SELECT * FROM LocatieDeseu WHERE idLocatie = " + idLocatieDeseu;
        ResultSet resultSet = this.repository.getTable(query);
        LocatieDeseu locatieDeseu = null;
        try {
            if (resultSet.next()) {
                String adresaLocatie = resultSet.getString("adresaLocatie");
                String tipDeseu = resultSet.getString("tipDeseu");
                String stadiu = resultSet.getString("stadiu");
                locatieDeseu = new LocatieDeseu(idLocatieDeseu, adresaLocatie, tipDeseu, stadiu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locatieDeseu;
    }


    public List<LocatieDeseu> getAllLocatiiDeseu() {
        String query = "SELECT * FROM LocatieDeseu ORDER BY adresaLocatie;";
        ResultSet resultSet = this.repository.getTable(query);
        List<LocatieDeseu> locatieDeseuList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idLocatie = resultSet.getInt("idLocatie");
                String adresaLocatie = resultSet.getString("adresaLocatie");
                String tipDeseu = resultSet.getString("tipDeseu");
                String stadiu = resultSet.getString("stadiu");
                LocatieDeseu locatieDeseu = new LocatieDeseu(idLocatie, adresaLocatie, tipDeseu, stadiu);
                locatieDeseuList.add(locatieDeseu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locatieDeseuList;
    }


    public List<LocatieDeseu> getAllLocatiiDeseuOrderedById() {
        String query = "SELECT * FROM LocatieDeseu ORDER BY idLocatie;";
        ResultSet resultSet = this.repository.getTable(query);
        List<LocatieDeseu> locatieDeseuList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idLocatie = resultSet.getInt("idLocatie");
                String adresaLocatie = resultSet.getString("adresaLocatie");
                String tipDeseu = resultSet.getString("tipDeseu");
                String stadiu = resultSet.getString("stadiu");
                LocatieDeseu locatieDeseu = new LocatieDeseu(idLocatie, adresaLocatie, tipDeseu, stadiu);
                locatieDeseuList.add(locatieDeseu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.repository.closingConnection();
        return locatieDeseuList;
    }


    public List<LocatieDeseu> getLocatieDeseuByStadiu(String stadiu) {
        String query = "SELECT * FROM LocatieDeseu WHERE stadiu = ?";
        List<LocatieDeseu> locatieDeseuList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, stadiu);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idLocatie = resultSet.getInt("idLocatie");
                    String adrLocatie = resultSet.getString("adresaLocatie");
                    String tipD = resultSet.getString("tipDeseu");
                    String stad = resultSet.getString("stadiu");

                    if (stad.equals("nealocat")) {
                        LocatieDeseu locatieDeseu = new LocatieDeseu(idLocatie, adrLocatie, tipD, stad);
                        locatieDeseuList.add(locatieDeseu);
                    }
                    if (stad.equals("alocat/necolectat")) {
                        LocatieDeseu locatieDeseu = new LocatieDeseu(idLocatie, adrLocatie, tipD, stad);
                        locatieDeseuList.add(locatieDeseu);
                    }
                    if (stad.equals("colectare")) {
                        LocatieDeseu locatieDeseu = new LocatieDeseu(idLocatie, adrLocatie, tipD, stad);
                        locatieDeseuList.add(locatieDeseu);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locatieDeseuList;
    }

    public List<LocatieDeseu> getLocatieDeseuNealocate(String stadiu) {
        String query = "SELECT * FROM LocatieDeseu WHERE stadiu = ?";
        List<LocatieDeseu> locatieDeseuList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, stadiu);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idLocatie = resultSet.getInt("idLocatie");
                    String adrLocatie = resultSet.getString("adresaLocatie");
                    String tipD = resultSet.getString("tipDeseu");
                    String stad = resultSet.getString("stadiu");

                    if (stad.equals("nealocat")) {
                        LocatieDeseu locatieDeseu = new LocatieDeseu(idLocatie, adrLocatie, tipD, stad);
                        locatieDeseuList.add(locatieDeseu);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locatieDeseuList;
    }


    public String getStadiuLocatieDeseu(int idLocatieDeseu) {
        String query = "SELECT stadiu FROM LocatieDeseu WHERE idLocatie = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idLocatieDeseu);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("stadiu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
