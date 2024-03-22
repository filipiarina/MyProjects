package com.example.prjdb.domain;

public class Mesaj {
    public int id_mesaj;
    public String continut;
    public int student_id;
    public int grup_id;

    public Mesaj(int id_mesaj, String continut, int student_id, int grup_id) {
        this.id_mesaj = id_mesaj;
        this.continut = continut;
        this.student_id = student_id;
        this.grup_id = grup_id;
    }

    public int getId_mesaj() {
        return id_mesaj;
    }

    public String getContinut() {
        return continut;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getGrup_id() {
        return grup_id;
    }

    public void setId_mesaj(int id_mesaj) {
        this.id_mesaj = id_mesaj;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setGrup_id(int grup_id) {
        this.grup_id = grup_id;
    }
}
