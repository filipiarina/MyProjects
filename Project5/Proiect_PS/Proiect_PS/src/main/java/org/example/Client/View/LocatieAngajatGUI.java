package org.example.Client.View;

import org.example.Client.Controller.LocatieAngajatController;
import org.example.Server.Model.Observable;
import org.example.Server.Model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LocatieAngajatGUI extends JDialog implements Observable {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton ADDButton;

    private LocatieAngajatController locatieAngajatController;

    public LocatieAngajatGUI() throws HeadlessException {
        setTitle("Locatie Angajat PAGE");
        setContentPane(panel1);
        setMinimumSize(new Dimension(450,474));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultTableModel tableModel = new DefaultTableModel();
        table1.setModel(tableModel);
        locatieAngajatController = new LocatieAngajatController(this);

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

    public JButton getADDButton() {
        return ADDButton;
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void update(Subject subject) {
        locatieAngajatController.populateTable();
    }
    public void afisareDateTabel(DefaultTableModel model) {
        table1.setModel(model);
    }

}
