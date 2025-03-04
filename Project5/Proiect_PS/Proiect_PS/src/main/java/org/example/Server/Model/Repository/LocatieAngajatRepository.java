package org.example.Server.Model.Repository;

import org.example.Server.Model.LocatieAngajat;
import org.example.Server.Model.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocatieAngajatRepository extends Subject {
    private final Repository repository;

    public LocatieAngajatRepository() {
        this.repository = new Repository();
    }

    public LocatieAngajatRepository(String dataURL) {
        this.repository = new Repository(dataURL);
    }


    public boolean addLocatieAngajat(LocatieAngajat locatieAngajat) {
        String commandSQL = "INSERT INTO LocatieAngajat (idLocatie, angajat_id) VALUES ('" +
                locatieAngajat.getIdLocatieDeseu() +
                "','" + locatieAngajat.getIdAngajat() + "')";
        return this.repository.commandSQL(commandSQL);
    }


    public boolean updateLocatieAngajat(LocatieAngajat locatieAngajat) {
        String commandSQL = "UPDATE LocatieAngajat SET idLocatie = '" + locatieAngajat.getIdLocatieDeseu() +
                "', idAngajat = '" + locatieAngajat.getIdAngajat() +
                "' WHERE idLocatieAngajat = " + locatieAngajat.getIdLocatieAngajat();
        return this.repository.commandSQL(commandSQL);
    }


    public boolean deleteLocatieAngajat(int idLocatieAngajat) {
        String commandSQL = "DELETE FROM LocatieAngajat WHERE idLocatieAngajat = " + idLocatieAngajat;
        return this.repository.commandSQL(commandSQL);
    }


    public LocatieAngajat getLocatieAngajatById(int idLocatieAngajat) {
        String query = "SELECT * FROM LocatieAngajat WHERE idLocatieAngajat = " + idLocatieAngajat;
        ResultSet resultSet = this.repository.getTable(query);
        LocatieAngajat locatieAngajat = null;
        try {
            if (resultSet.next()) {
                Integer idLocatie = resultSet.getInt("idLocatie");
                Integer idAngajat = resultSet.getInt("angajat_id");
                locatieAngajat = new LocatieAngajat(idLocatieAngajat, idLocatie, idAngajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locatieAngajat;
    }


    public List<LocatieAngajat> getAllLocatiiAngajat() {
        String query = "SELECT * FROM LocatieAngajat ORDER BY idLocatieAngajat;";
        ResultSet resultSet = this.repository.getTable(query);
        List<LocatieAngajat> locatieAngajatList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idLocatieAngajat = resultSet.getInt("idLocatieAngajat");
                Integer idLocatie = resultSet.getInt("idLocatie");
                Integer idAngajat = resultSet.getInt("angajat_id");
                LocatieAngajat locatieAngajat = new LocatieAngajat(idLocatieAngajat, idLocatie, idAngajat);
                locatieAngajatList.add(locatieAngajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locatieAngajatList;
    }


    public List<LocatieAngajat> getAllLocatiiAngajatOrderedById() {
        String query = "SELECT * FROM LocatieAngajat ORDER BY idLocatieAngajat;";
        ResultSet resultSet = this.repository.getTable(query);
        List<LocatieAngajat> locatieAngajatList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idLocatieAngajat = resultSet.getInt("idLocatieAngajat");
                Integer idLocatie = resultSet.getInt("idLocatie");
                Integer idAngajat = resultSet.getInt("angajat_id");
                LocatieAngajat locatieAngajat = new LocatieAngajat(idLocatieAngajat, idLocatie, idAngajat);
                locatieAngajatList.add(locatieAngajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.repository.closingConnection();
        return locatieAngajatList;
    }


    public List<LocatieAngajat> getLocatieAngajatByLocatie(Integer idLocatie, Integer idAngajat) {
        String query = "SELECT * FROM LocatieAngajat WHERE ";
        boolean ok = false;
        if(!idLocatie.equals("")){
            query = query + "idLocatie = '" + idLocatie + "'";
            ok = true;
        }
        if(idAngajat!=null){
            if(ok){
                query = query + " AND";
            }
            query = query + " angajat_id <= " + idAngajat;
        }
        query = query + " ORDER BY idLocatieAngajat;";
        ResultSet resultSet = this.repository.getTable(query);
        List<LocatieAngajat> locatieAngajatList = null;
        try {
            if(resultSet!=null){
                locatieAngajatList = new ArrayList<>();
                while (resultSet.next()) {
                    int idLocatieAngajat = resultSet.getInt("idLocatieAngajat");
                    Integer idLoc = resultSet.getInt("idLocatie");
                    Integer idAngj = resultSet.getInt("angajat_id");
                    LocatieAngajat locatieAngajat = new LocatieAngajat(idLocatieAngajat, idLoc, idAngj);
                    locatieAngajatList.add(locatieAngajat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locatieAngajatList;
    }

    public List<LocatieAngajat> getLocatieAngajatNealocat(Integer idLocatie, Integer idAngajat) {
        String query = "SELECT * FROM LocatieAngajat WHERE ";
        boolean ok = false;
        if (!idLocatie.equals("")) {
            query = query + "idLocatie = '" + idLocatie + "'";
            ok = true;
        }
        if (idAngajat != null) {
            if (ok) {
                query = query + " AND";
            }
            query = query + " angajat_id = " + idAngajat;
        }
        query = query + " AND stadiu = 'nealocat' ORDER BY idLocatieAngajat;";
        ResultSet resultSet = this.repository.getTable(query);
        List<LocatieAngajat> locatieAngajatList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idLocatieAngajat = resultSet.getInt("idLocatieAngajat");
                Integer idLoc = resultSet.getInt("idLocatie");
                Integer idAngj = resultSet.getInt("angajat_id");
                LocatieAngajat locatieAngajat = new LocatieAngajat(idLocatieAngajat, idLoc, idAngj);
                locatieAngajatList.add(locatieAngajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locatieAngajatList;
    }

    public boolean updateStadiuLocatieAngajat(int idLocatieAngajat, String stadiu) {
        String commandSQL = "UPDATE LocatieAngajat SET stadiu = '" + stadiu +
                "' WHERE idLocatieAngajat = " + idLocatieAngajat;
        return this.repository.commandSQL(commandSQL);
    }

    public String getStadiuLocatieDeseu(int idLocatieDeseu) {
        String query = "SELECT stadiu FROM LocatieDeseu WHERE idLocatie = " + idLocatieDeseu;
        ResultSet resultSet = this.repository.getTable(query);
        String stadiu = null;
        try {
            if (resultSet.next()) {
                stadiu = resultSet.getString("stadiu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stadiu;
    }

    public LocatieAngajat getLocatieAngajatByAngajat(int idAngajat, int idLocatieDeseu) {
        String query = "SELECT * FROM LocatieAngajat WHERE angajat_id = " + idAngajat +
                " AND idLocatie = " + idLocatieDeseu;
        ResultSet resultSet = this.repository.getTable(query);
        LocatieAngajat locatieAngajat = null;
        try {
            if (resultSet.next()) {
                int idLocatieAngajat = resultSet.getInt("idLocatieAngajat");
                locatieAngajat = new LocatieAngajat(idLocatieAngajat, idLocatieDeseu, idAngajat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locatieAngajat;
    }

}
