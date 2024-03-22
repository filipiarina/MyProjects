package com.example.prjdb.domain;

public class Utilizator {
    public Integer id_user;
    public String CNP;
    public String nume;
    public String prenume;
    public String adresa;
    public String email;
    public String parola;
    public String nr_telefon;
    public String cont_iban;
    public String nr_cont;
    public String nr_contact;
    public String tip;

    public Utilizator(Integer id_user, String CNP, String nume, String prenume, String adresa, String email, String parola, String nr_telefon, String cont_iban, String nr_cont, String nr_contact, String tip) {
        this.id_user = id_user;
        this.CNP = CNP;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.email = email;
        this.parola = parola;
        this.nr_telefon = nr_telefon;
        this.cont_iban = cont_iban;
        this.nr_cont = nr_cont;
        this.nr_contact = nr_contact;
        this.tip = tip;
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getCNP() {
        return CNP;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public String getNr_telefon() {
        return nr_telefon;
    }

    public String getCont_iban() {
        return cont_iban;
    }

    public String getNr_cont() {
        return nr_cont;
    }

    public String getNr_contact() {
        return nr_contact;
    }

    public String getTip() {
        return tip;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon = nr_telefon;
    }

    public void setCont_iban(String cont_iban) {
        this.cont_iban = cont_iban;
    }

    public void setNr_cont(String nr_cont) {
        this.nr_cont = nr_cont;
    }

    public void setNr_contact(String nr_contact) {
        this.nr_contact = nr_contact;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    public String getParola() {return parola;}

    public void setParola(String parola) {this.parola = parola;}
}
