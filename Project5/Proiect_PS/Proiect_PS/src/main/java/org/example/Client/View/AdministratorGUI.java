package org.example.Client.View;

import org.example.Client.Controller.AdministratorController;
import org.example.Server.Model.Administrator;
import org.example.Server.Model.Observable;
import org.example.Server.Model.Repository.AdministratorRepository;
import org.example.Server.Model.Repository.LocatieDeseuRepository;
import org.example.Server.Model.Repository.UserRepository;
import org.example.Server.Model.Subject;
import org.example.Server.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AdministratorGUI  extends JDialog implements Observable {
    private JPanel panel1;
    private JTable table1;
    private JButton ADDButton;
    private JButton DELETEButton;
    private JButton EDITButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JButton ADDButton1;
    private JButton DELETEButton1;
    private JButton EDITButton1;
    private JButton VIEWButton;
    private JButton ANGAJATButton;
    private JButton COORDONATORButton;
    private JButton ADMINButton;
    private JButton statisticiButton;

    private AdministratorController administratorController;

    public AdministratorGUI() {
        setTitle("ADMIN PAGE");
        setContentPane(panel1);
        setMinimumSize(new Dimension(1500,700));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultTableModel tableModel = new DefaultTableModel();
        table1.setModel(tableModel);
        administratorController = new AdministratorController(this);

        statisticiButton.addActionListener(e -> performGraphics());

        setVisible(true);
    }

    public JButton getAddButton() {return ADDButton;}

    public JButton getEditButton() {
        return EDITButton;
    }

    public JButton getDeleteButton() {
        return DELETEButton;
    }

    public JButton getAddButton1() {
        return ADDButton1;
    }

    public JButton getEditButton1() {
        return EDITButton1;
    }

    public JButton getDeleteButton1() {
        return DELETEButton1;
    }

    public JTable getTable1() {
        return table1;
    }

    public int getTextField1() {
        try {
            return Integer.parseInt(textField1.getText());
        } catch (NumberFormatException e) {
            displayMessage("Introduceți un număr valid pentru ID-ul angajatului!");
            return -1;
        }
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public int getTextField6() {
        try {
            return Integer.parseInt(textField6.getText());
        } catch (NumberFormatException e) {
            displayMessage("Introduceți un număr valid pentru ID-ul angajatului!");
            return -1;
        }    }

    public JTextField getTextField7() {
        return textField7;
    }

    public JTextField getTextField8() {
        return textField8;
    }

    public JTextField getTextField9() {
        return textField9;
    }

    public JTextField getTextField10() {
        return textField10;
    }

    public JButton getVIEWButton() {
        return VIEWButton;
    }

    public JButton getANGAJATButton() {
        return ANGAJATButton;
    }

    public JButton getCOORDONATORButton() {
        return COORDONATORButton;
    }

    public JButton getADMINButton() {
        return ADMINButton;
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void afisareUtilizatori(DefaultTableModel model) {
        table1.setModel(model);
    }

    @Override
    public void update(Subject subject) {
        administratorController.populateTable();
    }

    public void setAdministrator(Administrator administrator) {
        // Setează informațiile administratorului în interfața grafică, de exemplu în câmpurile de text, tabele etc.
        // Exemplu:
        textField1.setText(administrator.getNume());
        textField2.setText(administrator.getEmail());
        // ...
    }

    private void performGraphics()
    {
        AdministratorRepository userRepository = new AdministratorRepository();
        StatisticiUtilizatoriGUI artworkStatisticsView = new StatisticiUtilizatoriGUI(userRepository);
    }
}
