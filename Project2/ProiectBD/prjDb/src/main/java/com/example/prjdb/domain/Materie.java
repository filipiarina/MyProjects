package com.example.prjdb.domain;

public class Materie {
    public int id_materie;
    public int profesor_id;
    public String denumire_materie;
    public boolean curs;
    public boolean seminar;
    public boolean laborator;

    public Materie(int id_materie, int profesor_id, String denumire_materie, boolean curs, boolean seminar, boolean laborator) {
        this.id_materie = id_materie;
        this.profesor_id = profesor_id;
        this.denumire_materie = denumire_materie;
        this.curs = curs;
        this.seminar = seminar;
        this.laborator = laborator;
    }

    public int getId_materie() {
        return id_materie;
    }

    public int getProfesor_id() {
        return profesor_id;
    }

    public String getDenumire_materie() {
        return denumire_materie;
    }

    public boolean isCurs() {
        return curs;
    }

    public boolean isSeminar() {
        return seminar;
    }

    public boolean isLaborator() {
        return laborator;
    }

    public void setId_materie(int id_materie) {
        this.id_materie = id_materie;
    }

    public void setProfesor_id(int profesor_id) {
        this.profesor_id = profesor_id;
    }

    public void setDenumire_materie(String denumire_materie) {
        this.denumire_materie = denumire_materie;
    }

    public void setCurs(boolean curs) {
        this.curs = curs;
    }

    public void setSeminar(boolean seminar) {
        this.seminar = seminar;
    }

    public void setLaborator(boolean laborator) {
        this.laborator = laborator;
    }
}
