package org.example.Client.Controller;

import org.example.Client.Formate.FileCSV;
import org.example.Client.Formate.FileDOC;
import org.example.Client.Formate.FileJSON;
import org.example.Client.Formate.FileXML;
import org.example.Server.Model.LocatieDeseu;
import org.example.Server.Model.Repository.LocatieDeseuRepository;
import org.example.Client.View.CoordonatorGUI;
import org.example.Client.View.LocatieAngajatGUI;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;


public class CoordonatorController {
    private CoordonatorGUI coordonatorGUI;
    private LocatieDeseuRepository locatieDeseuRepository;


    public CoordonatorController(CoordonatorGUI coordonatorGUI) {
        this.coordonatorGUI = coordonatorGUI;
        this.locatieDeseuRepository = new LocatieDeseuRepository();

        this.populateTable();
        this.coordonatorGUI.setVisible(true);
        this.eventsManagement();
    }

    private void eventsManagement() {
        this.coordonatorGUI.getVIEWButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizualizareLocatie();
            }
        });
        this.coordonatorGUI.getADDButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaLocatie();
            }
        });

        this.coordonatorGUI.getDELETEButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLocatie();
            }
        });

        this.coordonatorGUI.getEDITButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editLocatie();
            }
        });

        this.coordonatorGUI.getNealocatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizualizareNealocat();
            }
        });

        this.coordonatorGUI.getAlocatNecolectatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizualizareAlocatNecolectat();
            }
        });

        this.coordonatorGUI.getColectatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizualizareColectat();
            }
        });

        this.coordonatorGUI.getALOCAREButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocatieAngajatGUI locatieAngajatGUI = new LocatieAngajatGUI();
                LocatieAngajatController locatieAngajatController = new LocatieAngajatController(locatieAngajatGUI);
                //locatieAngajatGUI.setVisible(true);
            }
        });

        this.coordonatorGUI.getSalveazaFisierButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alegeFormat();
            }
        });

        /*this.coordonatorGUI.getStatisticiButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatisticiGUI statisticiGUI = new StatisticiGUI();
                StatisticiController statisticiController = new StatisticiController();
                statisticiGUI.setVisible(true);
            }
        });*/
       /* this.coordonatorGUI.getStatisticiButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button pressed");
                StatisticiController statisticiController = new StatisticiController();
                StatisticiGUI statisticiGUI = new StatisticiGUI(statisticiController.getMainPanel());
                statisticiGUI.setVisible(true);
            }
        });*/

    }

    public void vizualizareLocatie() {
        List<LocatieDeseu> listaLocatii = locatieDeseuRepository.getAllLocatiiDeseu();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Locatie");
        model.addColumn("Adresa Locatie");
        model.addColumn("Tip Deseu");
        model.addColumn("Stadiu");

        for (LocatieDeseu locatieDeseu : listaLocatii) {
            Vector<Object> rand = new Vector<>();
            rand.add(locatieDeseu.getIdLocatie());
            rand.add(locatieDeseu.getAdresaLocatie());
            rand.add(locatieDeseu.getTipDeseu());
            rand.add(locatieDeseu.getStadiu());
            model.addRow(rand);
        }

        this.coordonatorGUI.afisareDateTabel(model);
    }

    public void adaugaLocatie() {
        String adresaLocatie = coordonatorGUI.getTextField2().getText();
        String tipDeseu = coordonatorGUI.getTextField3().getText();
        String stadiu = coordonatorGUI.getTextField4().getText();

        LocatieDeseu locatieDeseu = new LocatieDeseu(0, adresaLocatie, tipDeseu, stadiu);

        boolean success = locatieDeseuRepository.addLocatieDeseu(locatieDeseu);

        if (success) {
            coordonatorGUI.displayMessage("Locatie adaugata cu succes!");
            vizualizareLocatie(); // Reafisam tabelul cu date actualizate
        } else {
            coordonatorGUI.displayMessage("Eroare la adaugarea locatiei!");
        }
    }

    public void editLocatie(){
        Integer idLocatie = coordonatorGUI.getTextField1();
        String adresaLocatie = coordonatorGUI.getTextField2().getText();
        String tipDeseu = coordonatorGUI.getTextField3().getText();
        String stadiu = coordonatorGUI.getTextField4().getText();

        LocatieDeseu locatieDeseu = new LocatieDeseu(idLocatie, adresaLocatie, tipDeseu, stadiu);

        boolean success = locatieDeseuRepository.updateLocatieDeseu(locatieDeseu);

        if (success) {
            coordonatorGUI.displayMessage("Locatie actualizata cu succes!");
            vizualizareLocatie(); // Reafisam tabelul cu date actualizate
        } else {
            coordonatorGUI.displayMessage("Eroare la actualizarea locatiei!");
        }
    }

    public void deleteLocatie(){
        Integer idLocatie = coordonatorGUI.getTextField1();

        boolean success = locatieDeseuRepository.deleteLocatieDeseu(idLocatie);

        if (success) {
            coordonatorGUI.displayMessage("Locatie stearsa cu succes!");
            vizualizareLocatie(); // Reafisam tabelul cu date actualizate
        } else {
            coordonatorGUI.displayMessage("Eroare la stergerea locatiei!");
        }
    }

   public void vizualizareNealocat() {
       List<LocatieDeseu> locatiiNealocate = locatieDeseuRepository.getLocatieDeseuByStadiu("nealocat");
       DefaultTableModel model = new DefaultTableModel();

       model.addColumn("ID Locatie");
       model.addColumn("Adresa Locatie");
       model.addColumn("Tip Deseu");
       model.addColumn("Stadiu");

       for (LocatieDeseu locatieDeseu : locatiiNealocate) {
           Vector<Object> row = new Vector<>();
           row.add(locatieDeseu.getIdLocatie());
           row.add(locatieDeseu.getAdresaLocatie());
           row.add(locatieDeseu.getTipDeseu());
           row.add(locatieDeseu.getStadiu());
           model.addRow(row);
       }

       coordonatorGUI.afisareDateTabel(model);
   }

    public void vizualizareAlocatNecolectat() {
        List<LocatieDeseu> locatiiAlocateNecolectate = locatieDeseuRepository.getLocatieDeseuByStadiu("alocat/necolectat");
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Locatie");
        model.addColumn("Adresa Locatie");
        model.addColumn("Tip Deseu");
        model.addColumn("Stadiu");

        for (LocatieDeseu locatieDeseu : locatiiAlocateNecolectate) {
            Vector<Object> row = new Vector<>();
            row.add(locatieDeseu.getIdLocatie());
            row.add(locatieDeseu.getAdresaLocatie());
            row.add(locatieDeseu.getTipDeseu());
            row.add(locatieDeseu.getStadiu());
            model.addRow(row);
        }

        coordonatorGUI.afisareDateTabel(model);
    }

    public void vizualizareColectat() {
        List<LocatieDeseu> locatiiColectate = locatieDeseuRepository.getLocatieDeseuByStadiu("colectare");
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Locatie");
        model.addColumn("Adresa Locatie");
        model.addColumn("Tip Deseu");
        model.addColumn("Stadiu");

        for (LocatieDeseu locatieDeseu : locatiiColectate) {
            Vector<Object> row = new Vector<>();
            row.add(locatieDeseu.getIdLocatie());
            row.add(locatieDeseu.getAdresaLocatie());
            row.add(locatieDeseu.getTipDeseu());
            row.add(locatieDeseu.getStadiu());
            model.addRow(row);
        }

        coordonatorGUI.afisareDateTabel(model);
    }

    private void afisareLocatiiInTabel(List<LocatieDeseu> listaLocatii) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Locatie");
        model.addColumn("Adresa Locatie");
        model.addColumn("Tip Deseu");
        model.addColumn("Stadiu");

        for (LocatieDeseu locatieDeseu : listaLocatii) {
            Vector<Object> rand = new Vector<>();
            rand.add(locatieDeseu.getIdLocatie());
            rand.add(locatieDeseu.getAdresaLocatie());
            rand.add(locatieDeseu.getTipDeseu());
            rand.add(locatieDeseu.getStadiu());
            model.addRow(rand);
        }

        this.coordonatorGUI.afisareDateTabel(model);
    }

    public void populateTable() {
        List<LocatieDeseu> usersList = locatieDeseuRepository.getAllLocatiiDeseu();
        DefaultTableModel model = (DefaultTableModel) coordonatorGUI.getTable1().getModel();
        model.setRowCount(0);
        for (LocatieDeseu user : usersList) {
            Object[] row = {user.getIdLocatie(), user.getAdresaLocatie(), user.getTipDeseu(), user.getStadiu()};
            model.addRow(row);
        }
    }

    public void alegeFormat(){
        if(coordonatorGUI.getComboBox1().getSelectedItem().equals(".doc")){
            FileDOC fileDOC = new FileDOC();
            fileDOC.execute();
        }
        else if(coordonatorGUI.getComboBox1().getSelectedItem().equals(".csv")){
            FileCSV fileCSV = new FileCSV();
            fileCSV.execute();
        }
        else if(coordonatorGUI.getComboBox1().getSelectedItem().equals(".xml")){
            FileXML fileXML = new FileXML();
            fileXML.execute();
        }else if(coordonatorGUI.getComboBox1().getSelectedItem().equals(".json")){
            FileJSON fileJSON = new FileJSON();
            fileJSON.execute();
        }else{
            System.out.println("Alegeti un format pentru a salva informatiile despre pachete!");
        }
    }
}
