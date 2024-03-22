package com.example.tema1_tp;

import com.clase.Monom;
import com.clase.Operatii;
import com.clase.Polinom;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {
    public Polinom polinom = new Polinom();
    public Polinom polinom2 = new Polinom();
    public Polinom rezultatPol = new Polinom();

    @FXML
    private Button butonAdunare;
    @FXML
    private Button butonScadere;
    @FXML
    private Button butonDerivare;
    @FXML
    private Button butonInmultire;
    @FXML
    private Button butonSubmit1;
    @FXML
    private Button butonSubmit2;
    @FXML
    private Button butonClear;
    @FXML
    private TextField afisarePolinom1;
    @FXML
    private TextField afisarePolinom2;
    @FXML
    private TextField rezultat;
    @FXML
    private TextField p1coef5;
    @FXML
    private TextField p1coef4;
    @FXML
    private TextField p1coef3;
    @FXML
    private TextField p1coef2;
    @FXML
    private TextField p1coef1;
    @FXML
    private TextField p1coef0;
    @FXML
    private TextField p2coef5;
    @FXML
    private TextField p2coef4;
    @FXML
    private TextField p2coef3;
    @FXML
    private TextField p2coef2;
    @FXML
    private TextField p2coef1;
    @FXML
    private TextField p2coef0;



    @FXML
    void adunare(ActionEvent event) {
        this.rezultat.clear();
        StringBuilder rez = new StringBuilder();
        this.rezultatPol = Operatii.adunare(this.polinom, this.polinom2);
        Iterator var4 = this.rezultatPol.getPolinom().iterator();

        while(var4.hasNext()) {
            Monom m = (Monom)var4.next();
            rez.append(m.toString());
        }

        this.rezultat.setText(rez.toString());
    }

    @FXML
    void scadere(ActionEvent event) {
        this.rezultat.clear();
        StringBuilder rez = new StringBuilder();
        this.rezultatPol = Operatii.scadere(this.polinom, this.polinom2);
        Iterator var4 = this.rezultatPol.getPolinom().iterator();

        while(var4.hasNext()) {
            Monom m = (Monom)var4.next();
            rez.append(m.toString());
        }

        this.rezultat.setText(rez.toString());
    }

    @FXML
    void derivare(ActionEvent event) {
        this.rezultat.clear();
        StringBuilder rez = new StringBuilder();
        this.rezultatPol = Operatii.derivare(this.polinom);
        Iterator var4 = this.rezultatPol.getPolinom().iterator();

        while(var4.hasNext()) {
            Monom m = (Monom)var4.next();
            rez.append(m.toString());
        }

        this.rezultat.setText(rez.toString());
    }

    @FXML
    void inmultire(ActionEvent event) {
        this.rezultat.clear();
        StringBuilder rez = new StringBuilder();
        this.rezultatPol = Operatii.produs(this.polinom, this.polinom2);
        Iterator var4 = this.rezultatPol.getPolinom().iterator();

        while(var4.hasNext()) {
            Monom m = (Monom)var4.next();
            rez.append(m.toString());
        }

        this.rezultat.setText(rez.toString());
    }

    @FXML
    void clr(ActionEvent event) {
        this.afisarePolinom1.clear();
        this.afisarePolinom2.clear();
        this.rezultat.clear();
        this.p1coef5.clear();
        this.p1coef4.clear();
        this.p1coef3.clear();
        this.p1coef2.clear();
        this.p1coef1.clear();
        this.p1coef0.clear();
        this.p2coef5.clear();
        this.p2coef4.clear();
        this.p2coef3.clear();
        this.p2coef2.clear();
        this.p2coef1.clear();
        this.p2coef0.clear();
    }

    @FXML
    void submit1(ActionEvent event) {
        this.afisarePolinom1.clear();
        if (this.p1coef5.getText().equals("")) {
            this.polinom.add(new Monom(0, 5));
        } else {
            this.polinom.add(new Monom(Integer.parseInt(this.p1coef5.getText()), 5));
        }

        if (this.p1coef4.getText().equals("")) {
            this.polinom.add(new Monom(0, 4));
        } else {
            this.polinom.add(new Monom(Integer.parseInt(this.p1coef4.getText()), 4));
        }

        if (this.p1coef3.getText().equals("")) {
            this.polinom.add(new Monom(0, 3));
        } else {
            this.polinom.add(new Monom(Integer.parseInt(this.p1coef3.getText()), 3));
        }

        if (this.p1coef2.getText().equals("")) {
            this.polinom.add(new Monom(0, 2));
        } else {
            this.polinom.add(new Monom(Integer.parseInt(this.p1coef2.getText()), 2));
        }

        if (this.p1coef1.getText().equals("")) {
            this.polinom.add(new Monom(0, 1));
        } else {
            this.polinom.add(new Monom(Integer.parseInt(this.p1coef1.getText()), 1));
        }

        if (this.p1coef0.getText().equals("")) {
            this.polinom.add(new Monom(0, 0));
        } else {
            this.polinom.add(new Monom(Integer.parseInt(this.p1coef0.getText()), 0));
        }

        StringBuilder rez = new StringBuilder();
        Iterator var4 = this.polinom.getPolinom().iterator();

        while(var4.hasNext()) {
            Monom m = (Monom)var4.next();
            rez.append(m.toString());
        }

        this.afisarePolinom1.setText(rez.toString());
    }

    @FXML
    void submit2(ActionEvent event) {
        this.afisarePolinom2.clear();
        if (this.p2coef5.getText().equals("")) {
            this.polinom2.add(new Monom(0, 5));
        } else {
            this.polinom2.add(new Monom(Integer.parseInt(this.p2coef5.getText()), 5));
        }

        if (this.p2coef4.getText().equals("")) {
            this.polinom2.add(new Monom(0, 4));
        } else {
            this.polinom2.add(new Monom(Integer.parseInt(this.p2coef4.getText()), 4));
        }

        if (this.p2coef3.getText().equals("")) {
            this.polinom2.add(new Monom(0, 3));
        } else {
            this.polinom2.add(new Monom(Integer.parseInt(this.p2coef3.getText()), 3));
        }

        if (this.p2coef2.getText().equals("")) {
            this.polinom2.add(new Monom(0, 2));
        } else {
            this.polinom2.add(new Monom(Integer.parseInt(this.p2coef2.getText()), 2));
        }

        if (this.p2coef1.getText().equals("")) {
            this.polinom2.add(new Monom(0, 1));
        } else {
            this.polinom2.add(new Monom(Integer.parseInt(this.p2coef1.getText()), 1));
        }

        if (this.p2coef0.getText().equals("")) {
            this.polinom2.add(new Monom(0, 0));
        } else {
            this.polinom2.add(new Monom(Integer.parseInt(this.p2coef0.getText()), 0));
        }

        StringBuilder rez = new StringBuilder();
        Iterator var4 = this.polinom2.getPolinom().iterator();

        while(var4.hasNext()) {
            Monom m = (Monom)var4.next();
            rez.append(m.toString());
        }

        this.afisarePolinom2.setText(rez.toString());
    }
}
