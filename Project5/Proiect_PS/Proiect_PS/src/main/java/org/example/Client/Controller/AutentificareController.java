package org.example.Client.Controller;

import org.example.Client.Services.ClientService;
import org.example.Client.View.AdministratorGUI;
import org.example.Client.View.AngajatGUI;
import org.example.Client.View.AutentificareGUI;
import org.example.Client.View.CoordonatorGUI;
import org.example.Server.Model.Administrator;
import org.example.Server.Model.Angajat;
import org.example.Server.Model.Coordonator;
import org.example.Server.Model.Repository.AdministratorRepository;
import org.example.Server.Model.Repository.AngajatRepository;
import org.example.Server.Model.Repository.CoordonatorRepository;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutentificareController {
    private AutentificareGUI autentificareGUI;
    private AdministratorRepository administratorRepository;
    private AngajatRepository angajatRepository;
    private CoordonatorRepository coordonatorRepository;
    private ClientService clientService;

    public AutentificareController(AutentificareGUI autentificareGUI, ClientService clientService) {
        this.autentificareGUI = autentificareGUI;
        this.administratorRepository = new AdministratorRepository();
        this.angajatRepository = new AngajatRepository();
        this.coordonatorRepository = new CoordonatorRepository();
        this.clientService = clientService;

        // Adăugăm un ActionListener pentru butonul de login
        autentificareGUI.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate1();
            }
        });
        this.autentificareGUI.getLANGUAGEButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //showLanguageSelectionDialog();
            }
        });
    }

    public void authenticate() {
        String email = autentificareGUI.getTextField1().getText();
        String password = new String(autentificareGUI.getPasswordField1().getPassword());

        Administrator administrator = administratorRepository.getAdministratorByEmailAndPassword(email, password);
        if (administrator != null) {
            mergeInAdmin(administrator);
        } else {
            Angajat angajat = angajatRepository.getAngajatByEmailAndPassword(email, password);
            if (angajat != null) {
                mergeInAngajat();
            } else {
                Coordonator coordonator = coordonatorRepository.getCoordonatorByEmailAndPassword(email, password);
                if (coordonator != null) {
                    mergeInCoordonator();
                } else {
                    autentificareGUI.clearFields();
                    JOptionPane.showMessageDialog(autentificareGUI, "Date incorecte!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void authenticate1() {
        String email = autentificareGUI.getTextField1().getText();
        String password = new String(autentificareGUI.getPasswordField1().getPassword());

        try {
            String role = clientService.sendLoginRequest(email, password);
            if (role != null) {
                openRoleSpecificGUI(role);
            } else {
                autentificareGUI.clearFields();
                JOptionPane.showMessageDialog(autentificareGUI, "Date incorecte!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            autentificareGUI.clearFields();
            JOptionPane.showMessageDialog(autentificareGUI, "Eroare de conexiune!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRoleSpecificGUI(String role) {
        switch (role) {
            case "Angajat":
                new AngajatGUI(); // Deschide interfața Angajat
                break;
            case "Coordonator":
                new CoordonatorGUI(); // Deschide interfața Coordonator
                break;
            case "Administrator":
                new AdministratorGUI(); // Deschide interfața Administrator
                break;
            default:
                JOptionPane.showMessageDialog(autentificareGUI, "Rol necunoscut", "Eroare", JOptionPane.ERROR_MESSAGE);
                break;
        }
        autentificareGUI.dispose(); // Închide fereastra de autentificare
    }

    public void mergeInAdmin(Administrator administrator) {
        Component parentComponent = SwingUtilities.getRoot(autentificareGUI);
        JDialog parentDialog = null;
        if (parentComponent instanceof JDialog) {
            parentDialog = (JDialog) parentComponent;
        } else {
            parentDialog = new JDialog();
        }

        AdministratorGUI administratorGUI = new AdministratorGUI();
        administratorGUI.setAdministrator(administrator);
        administratorGUI.setVisible(true);
        autentificareGUI.setVisible(false);
    }


    public void mergeInAngajat() {
        Component parentComponent = SwingUtilities.getRoot(autentificareGUI);
        JDialog parentDialog = null;
        if (parentComponent instanceof JDialog) {
            parentDialog = (JDialog) parentComponent;
        } else {
            parentDialog = new JDialog();
        }

        AngajatGUI angajatGUI = new AngajatGUI();
        angajatGUI.setVisible(true);
        autentificareGUI.setVisible(false);
    }

    public void mergeInCoordonator() {
        Component parentComponent = SwingUtilities.getRoot(autentificareGUI);
        JDialog parentDialog = null;
        if (parentComponent instanceof JDialog) {
            parentDialog = (JDialog) parentComponent;
        } else {
            parentDialog = new JDialog();
        }

        CoordonatorGUI coordonatorGUI = new CoordonatorGUI();
        coordonatorGUI.setVisible(true);
        autentificareGUI.setVisible(false);
    }

   /* private void showLanguageSelectionDialog() {
        String[] languages = {"English", "French", "Italian"};
        String selectedLanguage = (String) JOptionPane.showInputDialog(this.autentificareGUI, "Select Language", "Language Selection", JOptionPane.QUESTION_MESSAGE, null, languages, languages[0]);

        if (selectedLanguage != null) {
            switch (selectedLanguage) {
                case "English":
                    Language.getInstance().switchLanguage("en");
                    break;
                case "French":
                    Language.getInstance().switchLanguage("fr");
                    break;
                case "Italian":
                    Language.getInstance().switchLanguage("it");
                    break;
                default:
                    Language.getInstance().switchLanguage("en");
            }
            autentificareGUI.updateLanguage();
        }
    }*/
}
