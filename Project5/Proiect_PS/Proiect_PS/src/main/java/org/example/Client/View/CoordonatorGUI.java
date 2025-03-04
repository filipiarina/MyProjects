package org.example.Client.View;

import org.example.Client.Controller.CoordonatorController;
import org.example.Server.Model.Observable;
import org.example.Server.Model.Repository.LocatieDeseuRepository;
import org.example.Server.Model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CoordonatorGUI extends JDialog implements Observable {
    private JPanel panel1;
    private JTable table1;
    private JButton VIEWButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ADDButton;
    private JButton DELETEButton;
    private JButton EDITButton;
    private JButton nealocatButton;
    private JButton alocatNecolectatButton;
    private JButton colectatButton;
    private JComboBox<String> comboBox1;
    private JButton salveazaFisierButton;
    private JButton ALOCAREButton;
    private JButton statisticiButton;


    private CoordonatorController coordonatorController;


    public CoordonatorGUI() {
        //super(parent);
        setTitle("COORDONATOR PAGE");
        setContentPane(panel1);
        setMinimumSize(new Dimension(1500,700));
        //setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //DefaultTableModel tableModel = new DefaultTableModel();
        //table1.setModel(tableModel);
        coordonatorController = new CoordonatorController(this);

        statisticiButton.addActionListener(e -> performGraphics());

        setVisible(true);
    }

    public JTable getTable1() {
        return table1;
    }

    public JButton getVIEWButton() {
        return VIEWButton;
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

    public JButton getADDButton() {
        return ADDButton;
    }

    public JButton getDELETEButton() {
        return DELETEButton;
    }

    public JButton getEDITButton() {
        return EDITButton;
    }

    public JButton getNealocatButton() {
        return nealocatButton;
    }

    public JButton getAlocatNecolectatButton() {
        return alocatNecolectatButton;
    }

    public JButton getColectatButton() {
        return colectatButton;
    }

    public JButton getALOCAREButton() {
        return ALOCAREButton;
    }

    public JComboBox<String> getComboBox1() {
        return comboBox1;
    }

    public JButton getSalveazaFisierButton() {
        return salveazaFisierButton;
    }

    public JButton getStatisticiButton() {
        return statisticiButton;
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void afisareDateTabel(DefaultTableModel model) {
        table1.setModel(model);
    }

    public void update(Subject subject) {
        coordonatorController.populateTable();
    }

    private void performGraphics()
    {
        LocatieDeseuRepository locatieDeseuRepository = new LocatieDeseuRepository();
        StatisticiInelarGUI artworkStatisticsView = new StatisticiInelarGUI(locatieDeseuRepository);
        StatisticiLinearGUI artworkStatisticsLinearView = new StatisticiLinearGUI(locatieDeseuRepository);
        StatisticiRadialGUI artworkStatisticsInelarView = new StatisticiRadialGUI(locatieDeseuRepository);
    }
}
