package org.example.Client.Controller;

import org.example.Server.Model.Administrator;
import org.example.Server.Model.Angajat;
import org.example.Server.Model.Coordonator;
import org.example.Server.Model.Repository.AdministratorRepository;
import org.example.Server.Model.Repository.AngajatRepository;
import org.example.Server.Model.Repository.CoordonatorRepository;
import org.example.Client.View.AdministratorGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class AdministratorController {
    private AdministratorGUI administratorGUI;
    private AdministratorRepository administratorRepository;
    private AngajatRepository angajatRepository;
    private CoordonatorRepository coordonatorRepository;

    public AdministratorController(AdministratorGUI administratorGUI) {
        this.administratorGUI = administratorGUI;
        this.administratorRepository = new AdministratorRepository();
        this.angajatRepository = new AngajatRepository();
        this.coordonatorRepository = new CoordonatorRepository();

        this.populateTable();
        //this.vizualizareUtilizatori();

        this.administratorGUI.setVisible(true);
        this.eventsManagement();
    }

    public AdministratorGUI getView(){
        return this.administratorGUI;
    }

    public void vizualizareUtilizatori() {
        List<Administrator> administratori = administratorRepository.getAllAdministrators();
        List<Angajat> angajati = angajatRepository.getAllAngajati();
        List<Coordonator> coordonatori = coordonatorRepository.getAllCoordonators();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Tip Utilizator");
        model.addColumn("ID");
        model.addColumn("Nume");
        model.addColumn("Email");
        model.addColumn("Parola");
        model.addColumn("Telefon/Ani Experienta");

        for (Administrator administrator : administratori) {
            Vector<Object> row = new Vector<>();
            row.add("Administrator");
            row.add(administrator.getId());
            row.add(administrator.getNume());
            row.add(administrator.getEmail());
            row.add(administrator.getParola());
            row.add(administrator.getTelefon());
            model.addRow(row);
        }

        for (Angajat angajat : angajati) {
            Vector<Object> row = new Vector<>();
            row.add("Angajat");
            row.add(angajat.getId());
            row.add(angajat.getNume());
            row.add(angajat.getEmail());
            row.add(angajat.getParola());
            row.add(angajat.getDepartament() + "/" + angajat.getAniExperienta());
            model.addRow(row);
        }

        for (Coordonator coordonator : coordonatori) {
            Vector<Object> row = new Vector<>();
            row.add("Coordonator");
            row.add(coordonator.getId());
            row.add(coordonator.getNume());
            row.add(coordonator.getEmail());
            row.add(coordonator.getParola());
            row.add("");
            model.addRow(row);
        }

        administratorGUI.afisareUtilizatori(model);
    }



    public void populateTable() {
        List<Angajat> usersList = angajatRepository.getAllAngajati();
        DefaultTableModel model = (DefaultTableModel) administratorGUI.getTable1().getModel();
        model.setRowCount(0);
        for (Angajat user : usersList) {
            Object[] row = {user.getId(), user.getNume(), user.getEmail(), user.getParola(), user.getDepartament(),user.getAniExperienta()};
            model.addRow(row);
        }
    }

    private void eventsManagement() {
        this.administratorGUI.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaAngajat();
            }
        });

        this.administratorGUI.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergeAngajat();
            }
        });

        this.administratorGUI.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizeazaAngajat();
            }
        });

        this.administratorGUI.getAddButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaCoordonator();
            }
        });

        this.administratorGUI.getDeleteButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergeCoordonator();
            }
        });

        this.administratorGUI.getEditButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizeazaCoordonator();
            }
        });

        this.administratorGUI.getVIEWButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizualizareUtilizatori();
            }
        });

        this.administratorGUI.getADMINButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrareUtilizatoriDupaTip("Administrator");
            }
        });

        // Ascultător pentru butonul de afișare a angajaților
        this.administratorGUI.getANGAJATButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrareUtilizatoriDupaTip("Angajat");
            }
        });

        // Ascultător pentru butonul de afișare a coordonatorilor
        this.administratorGUI.getCOORDONATORButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrareUtilizatoriDupaTip("Coordonator");
            }
        });
    }

    private void adaugaAngajat() {
        //int id = administratorGUI.getTextField1();
        String nume = administratorGUI.getTextField2().getText();
        String email = administratorGUI.getTextField3().getText();
        String parola = administratorGUI.getTextField4().getText();
        String departament = administratorGUI.getTextField5().getText();
        int aniExperienta = Integer.parseInt(administratorGUI.getTextField10().getText());

        Angajat angajat = new Angajat(0,nume, email, parola, departament, aniExperienta);

        if (angajatRepository.addAngajat(angajat)) {
            JOptionPane.showMessageDialog(administratorGUI, "Angajat adăugat cu succes!");
            vizualizareUtilizatori();
        } else {
            JOptionPane.showMessageDialog(administratorGUI, "Eroare la adăugarea angajatului!");
        }
    }

    public void stergeAngajat() {
        int angajatId = administratorGUI.getTextField1();

        if (angajatId == -1) {
            administratorGUI.displayMessage("Selectați un angajat pentru a-l șterge!");
            return;
        }

        boolean success = angajatRepository.deleteAngajat(angajatId);

        if (success) {
            administratorGUI.displayMessage("Angajatul a fost șters cu succes!");
            vizualizareUtilizatori();
        } else {
            administratorGUI.displayMessage("Eroare la ștergerea angajatului!");
        }
    }

    public void actualizeazaAngajat() {
        int angajatId = administratorGUI.getTextField1();
        String numeAngajat = administratorGUI.getTextField2().getText();
        String emailAngajat = administratorGUI.getTextField3().getText();
        String parolaAngajat = administratorGUI.getTextField4().getText();
        String departamentAngajat = administratorGUI.getTextField5().getText();
        int aniExperientaAngajat = Integer.parseInt(administratorGUI.getTextField10().getText());

        Angajat angajat = new Angajat(angajatId, numeAngajat, emailAngajat, parolaAngajat, departamentAngajat, aniExperientaAngajat);

        boolean success = angajatRepository.updateAngajat(angajat);

        if (success) {
            administratorGUI.displayMessage("Angajatul a fost actualizat cu succes!");
            vizualizareUtilizatori(); // Reafișăm lista actualizată de utilizatori
        } else {
            administratorGUI.displayMessage("Eroare la actualizarea angajatului!");
        }
    }

    public void adaugaCoordonator() {
        String nume = administratorGUI.getTextField7().getText();
        String email = administratorGUI.getTextField8().getText();
        String parola = administratorGUI.getTextField9().getText();

        Coordonator coordonator = new Coordonator(0,nume, email, parola);

        if (coordonatorRepository.addCoordonator(coordonator)) {
            JOptionPane.showMessageDialog(administratorGUI, "Coordonator adăugat cu succes!");
            vizualizareUtilizatori();
        } else {
            JOptionPane.showMessageDialog(administratorGUI, "Eroare la adăugarea coordonatorului!");
        }
    }

    public void stergeCoordonator() {
        int coordonatorId = administratorGUI.getTextField6();

        if (coordonatorId == -1) {
            administratorGUI.displayMessage("Selectați un coordonator pentru a-l șterge!");
            return;
        }

        boolean success = coordonatorRepository.deleteCoordonator(coordonatorId);

        if (success) {
            administratorGUI.displayMessage("Coordonatorul a fost șters cu succes!");
            vizualizareUtilizatori();
        } else {
            administratorGUI.displayMessage("Eroare la ștergerea coordonatorului!");
        }
    }

    public void actualizeazaCoordonator() {
        int coordonatorId = administratorGUI.getTextField6();
        String numeCoordonator = administratorGUI.getTextField7().getText();
        String emailCoordonator = administratorGUI.getTextField8().getText();
        String parolaCoordonator = administratorGUI.getTextField9().getText();

        Coordonator coordonator = new Coordonator(coordonatorId, numeCoordonator, emailCoordonator, parolaCoordonator);

        boolean success = coordonatorRepository.updateCoordonator(coordonator);

        if (success) {
            administratorGUI.displayMessage("Coordonatorul a fost actualizat cu succes!");
            vizualizareUtilizatori(); // Reafișăm lista actualizată de utilizatori
        } else {
            administratorGUI.displayMessage("Eroare la actualizarea coordonatorului!");
        }
    }


    public void filtrareUtilizatoriDupaTip(String tipUtilizator) {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Tip Utilizator");
        model.addColumn("ID");
        model.addColumn("Nume");
        model.addColumn("Email");
        model.addColumn("Parola");
        model.addColumn("Telefon/Ani Experienta");

        // Verificăm tipul utilizatorului și afișăm doar utilizatorii corespunzători
        if (tipUtilizator.equals("Administrator")) {
            List<Administrator> administratori = administratorRepository.getAllAdministrators();
            for (Administrator administrator : administratori) {
                Vector<Object> row = new Vector<>();
                row.add("Administrator");
                row.add(administrator.getId());
                row.add(administrator.getNume());
                row.add(administrator.getEmail());
                row.add(administrator.getParola());
                row.add(administrator.getTelefon());
                model.addRow(row);
            }
        } else if (tipUtilizator.equals("Angajat")) {
            List<Angajat> angajati = angajatRepository.getAllAngajati();
            for (Angajat angajat : angajati) {
                Vector<Object> row = new Vector<>();
                row.add("Angajat");
                row.add(angajat.getId());
                row.add(angajat.getNume());
                row.add(angajat.getEmail());
                row.add(angajat.getParola());
                row.add(angajat.getDepartament() + "/" + angajat.getAniExperienta());
                model.addRow(row);
            }
        } else if (tipUtilizator.equals("Coordonator")) {
            List<Coordonator> coordonatori = coordonatorRepository.getAllCoordonators();
            for (Coordonator coordonator : coordonatori) {
                Vector<Object> row = new Vector<>();
                row.add("Coordonator");
                row.add(coordonator.getId());
                row.add(coordonator.getNume());
                row.add(coordonator.getEmail());
                row.add(coordonator.getParola());
                row.add("");
                model.addRow(row);
            }
        }

        administratorGUI.afisareUtilizatori(model);
    }


}
