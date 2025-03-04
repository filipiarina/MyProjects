package org.example.Client.Controller;

import org.example.Server.Model.LocatieAngajat;
import org.example.Server.Model.LocatieDeseu;
import org.example.Server.Model.Repository.AngajatRepository;
import org.example.Server.Model.Repository.LocatieAngajatRepository;
import org.example.Server.Model.Repository.LocatieDeseuRepository;
import org.example.Client.View.AngajatGUI;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class AngajatController {
    private AngajatGUI angajatGUI;
    private LocatieDeseuRepository locatieDeseuRepository;
    private AngajatRepository angajatRepository;
    private LocatieAngajatRepository locatieAngajatRepository;

    public AngajatController(AngajatGUI angajatGUI) {
        this.angajatGUI = angajatGUI;
        this.locatieDeseuRepository = new LocatieDeseuRepository();
        this.angajatRepository = new AngajatRepository();
        this.locatieAngajatRepository = new LocatieAngajatRepository();

        this.populateTable();
        this.vizualizareLocatie();

        this.angajatGUI.setVisible(true);

        this.eventsManagement();

       // afisareDateTabel();
        //execute();
       // afiseazaTabelAngajati();
    }


    public void vizualizareLocatie() {
        List<LocatieDeseu> listaLocatii = locatieDeseuRepository.getAllLocatiiDeseu();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Locatie");
        model.addColumn("Adresa Locatie");
        model.addColumn("Tip Deseu");
        model.addColumn("Stadiu");

        for (LocatieDeseu locatieDeseu : listaLocatii) {
            if ("alocat/necolectat".equalsIgnoreCase(locatieDeseu.getStadiu())) {
                Vector<Object> rand = new Vector<>();
                rand.add(locatieDeseu.getIdLocatie());
                rand.add(locatieDeseu.getAdresaLocatie());
                rand.add(locatieDeseu.getTipDeseu());
                rand.add(locatieDeseu.getStadiu());
                model.addRow(rand);
            }
        }

        this.angajatGUI.afisareDateTabel(model);
    }

    private void eventsManagement() {
        this.angajatGUI.getCautaSiSeteazaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idAngajat = Integer.parseInt(angajatGUI.getTextField1().getText());
                int idLocatieDeseu = Integer.parseInt(angajatGUI.getTextField2().getText());

                // Apelează funcția colectareDeseu din controller cu id-urile obținute
                colectareDeseu(idAngajat, idLocatieDeseu);
            }
        });
        this.angajatGUI.getVIEWButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizualizareLocatie();
            }
        });
    }


    public void colectareDeseu(int idAngajat, int idLocatieDeseu) {
        // Verificăm dacă angajatul și locația deșeului există
        if (angajatRepository.getAngajatById(idAngajat) == null) {
            System.out.println("Angajatul cu ID-ul " + idAngajat + " nu există!");
            return;
        }
        if (locatieDeseuRepository.getLocatieDeseuById(idLocatieDeseu) == null) {
            System.out.println("Locația deșeului cu ID-ul " + idLocatieDeseu + " nu există!");
            return;
        }

        // Căutăm locația deșeului alocată angajatului
        LocatieAngajat locatieAngajat = locatieAngajatRepository.getLocatieAngajatByAngajat(idAngajat, idLocatieDeseu);
        if (locatieAngajat == null) {
            System.out.println("Angajatul cu ID-ul " + idAngajat + " nu are asignată locația deșeului cu ID-ul " + idLocatieDeseu);
            return;
        }

        // Actualizăm stadiul locației deșeului în "colectat"
        if (locatieDeseuRepository.updateStadiuLocatieDeseu1(idLocatieDeseu)) {
            System.out.println("Stadiul locației deșeului cu ID-ul " + idLocatieDeseu + " a fost actualizat la 'colectat'.");

            // Obținem datele locației deșeului pentru a le afișa în tabel
            LocatieDeseu locatieDeseu = locatieDeseuRepository.getLocatieDeseuById(idLocatieDeseu);

            // Creăm un nou model de tabel și adăugăm datele găsite
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Locatie");
            model.addColumn("Adresa Locatie");
            model.addColumn("Tip Deseu");
            model.addColumn("Stadiu");
            Vector<Object> rand = new Vector<>();
            rand.add(locatieDeseu.getIdLocatie());
            rand.add(locatieDeseu.getAdresaLocatie());
            rand.add(locatieDeseu.getTipDeseu());
            rand.add(locatieDeseu.getStadiu());
            model.addRow(rand);

            // Actualizăm modelul tabelului în interfață
            angajatGUI.getTable1().setModel(model);
        } else {
            System.out.println("Eroare la actualizarea stadiului locației deșeului!");
        }
    }


    public void populateTable() {
        List<LocatieDeseu> usersList = locatieDeseuRepository.getAllLocatiiDeseu();
        DefaultTableModel model = (DefaultTableModel) angajatGUI.getTable1().getModel();
        model.setRowCount(0);
        for (LocatieDeseu user : usersList) {
            Object[] row = {user.getIdLocatie(), user.getAdresaLocatie(), user.getTipDeseu(), user.getStadiu()};
            model.addRow(row);
        }
    }



}
