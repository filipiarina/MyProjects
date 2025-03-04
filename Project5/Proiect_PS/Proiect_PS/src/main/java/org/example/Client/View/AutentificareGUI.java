package org.example.Client.View;

import org.example.Server.Model.Observable;
import org.example.Server.Model.Subject;

import javax.swing.*;
import java.awt.*;

public class AutentificareGUI extends JDialog implements Observable {
    private JPanel panel1;
    private JTextField textField1;
    private JButton LOGINButton;
    private JPasswordField passwordField1;
    private JButton LANGUAGEButton;
    private JLabel lab1;
    private JLabel lab2;
    private JLabel lab3;
    private JComboBox comboBox1;


    public AutentificareGUI() throws HeadlessException {
       // super(parent);
        setTitle("USER PAGE");
        setContentPane(panel1);
        setMinimumSize(new Dimension(450,474));
       // setModal(true);
        //setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

       /* lab1.setText(Language.getInstance().getString("WELCOME!"));
        lab2.setText(Language.getInstance().getString("E-mail:"));
        lab2.setToolTipText(Language.getInstance().getString("Password:"));
        LOGINButton.setToolTipText(Language.getInstance().getString("LOGIN"));
        comboBox1.setToolTipText(Language.getInstance().getString("language"));


        updateLanguage();*/

        setVisible(true);

    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JButton getLoginButton() {
        return LOGINButton;
    }

    public JButton getLANGUAGEButton() {
        return LANGUAGEButton;
    }

    /*public void updateLanguage() {
        lab1.setText(Language.getInstance().getString("WELCOME!"));
        lab2.setText(Language.getInstance().getString("E-mail:"));
        lab2.setToolTipText(Language.getInstance().getString("Password:"));
        LOGINButton.setToolTipText(Language.getInstance().getString("LOGIN"));
        comboBox1.setToolTipText(Language.getInstance().getString("language"));
    }*/


    public void clearFields() {
        textField1.setText("");
        passwordField1.setText("");
    }

    @Override
    public void update(Subject subject) {
    }
}
