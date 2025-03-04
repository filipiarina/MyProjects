package org.example.Client.View;

import org.example.Client.Controller.AngajatController;
import org.example.Server.Model.Observable;
import org.example.Server.Model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AngajatGUI extends JDialog implements Observable {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton cautaSiSeteazaButton;
    private JTextField textField2;
    private JButton VIEWButton;

    private AngajatController angajatController;


    public AngajatGUI() {
        setTitle("EMPLOYE PAGE");
        setContentPane(panel1);
        setMinimumSize(new Dimension(1500,700));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultTableModel tableModel = new DefaultTableModel();
        table1.setModel(tableModel);
        angajatController = new AngajatController(this);

        setVisible(true);
    }

    public JTable getTable1() {
        return table1;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JButton getCautaSiSeteazaButton() {
        return cautaSiSeteazaButton;
    }

    public JButton getVIEWButton() {
        return VIEWButton;
    }

    public void afisareDateTabel(DefaultTableModel model) {
        table1.setModel(model);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


    @Override
    public void update(Subject subject) {
        angajatController.populateTable();
    }
}
