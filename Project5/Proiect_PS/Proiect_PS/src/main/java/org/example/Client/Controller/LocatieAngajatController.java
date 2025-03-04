package org.example.Client.Controller;

import org.example.Server.Model.LocatieAngajat;
import org.example.Server.Model.Repository.LocatieAngajatRepository;
import org.example.Server.Model.Repository.LocatieDeseuRepository;
import org.example.Client.View.LocatieAngajatGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class LocatieAngajatController {
    private LocatieAngajatGUI locatieAngajatGUI;
    private LocatieAngajatRepository locatieAngajatRepository;
    private LocatieDeseuRepository locatieDeseuRepository;


    public LocatieAngajatController(LocatieAngajatGUI locatieAngajatGUI) {
        this.locatieAngajatGUI = locatieAngajatGUI;
        this.locatieAngajatRepository = new LocatieAngajatRepository();
        this.locatieDeseuRepository = new LocatieDeseuRepository();

        this.populateTable();
        this.vizualizareLocatieAngajat();

        this.locatieAngajatGUI.setVisible(true);

        this.eventsManagement();

        // afisareDateTabel();
        //execute();
        // afiseazaTabelAngajati();
    }

    public void vizualizareLocatieAngajat() {
        List<LocatieAngajat> listaLocatiiAngajat = locatieAngajatRepository.getAllLocatiiAngajat();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Locatie Angajat");
        model.addColumn("ID Locatie Deseu");
        model.addColumn("ID Angajat");

        for (LocatieAngajat locatieAngajat : listaLocatiiAngajat) {
            Vector<Object> rand = new Vector<>();
            rand.add(locatieAngajat.getIdLocatieAngajat());
            rand.add(locatieAngajat.getIdLocatieDeseu());
            rand.add(locatieAngajat.getIdAngajat());
            model.addRow(rand);
        }

        this.locatieAngajatGUI.afisareDateTabel(model);
    }


    public void alocaLocatieDeseuAngajat(int idLocatieDeseu, int idAngajat) {
        String stadiuLocatieDeseu = locatieDeseuRepository.getStadiuLocatieDeseu(idLocatieDeseu);
        if (stadiuLocatieDeseu != null && stadiuLocatieDeseu.equalsIgnoreCase("nealocat")) {
            LocatieAngajat locatieAngajat = new LocatieAngajat(0,idLocatieDeseu, idAngajat);
            if (locatieAngajatRepository.addLocatieAngajat(locatieAngajat)) {
                locatieDeseuRepository.updateStadiuLocatieDeseu(idLocatieDeseu);
                vizualizareLocatieAngajat();
                System.out.println("Locația deșeu a fost alocată cu succes angajatului!");
            } else {
                System.out.println("Eroare la alocarea locației deșeu angajatului!");
            }
        } else {
            System.out.println("Locația deșeu nu există sau este deja alocată!");
        }
    }


    private void eventsManagement() {
        this.locatieAngajatGUI.getADDButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaButonApasat();
            }
        });
    }

    private void adaugaButonApasat() {
        int idLocatieDeseu = Integer.parseInt(locatieAngajatGUI.getTextField1().getText());
        int idAngajat = Integer.parseInt(locatieAngajatGUI.getTextField2().getText());

        String stadiu = locatieAngajatRepository.getStadiuLocatieDeseu(idLocatieDeseu);
        if ("nealocat".equalsIgnoreCase(stadiu)) {
            alocaLocatieDeseuAngajat(idLocatieDeseu, idAngajat);
        } else {
            JOptionPane.showMessageDialog(null, "Locatia de deseuri nu este disponibila pentru alocare.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void populateTable() {
        List<LocatieAngajat> usersList = locatieAngajatRepository.getAllLocatiiAngajatOrderedById();
        DefaultTableModel model = (DefaultTableModel) locatieAngajatGUI.getTable1().getModel();
        model.setRowCount(0);
        for (LocatieAngajat user : usersList) {
            Object[] row = {user.getIdLocatieAngajat(), user.getIdLocatieDeseu(), user.getIdAngajat()};
            model.addRow(row);
        }
    }
}
