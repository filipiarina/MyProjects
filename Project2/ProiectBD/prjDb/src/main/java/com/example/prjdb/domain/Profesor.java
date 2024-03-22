package com.example.prjdb.domain;

public class Profesor {
    public int id_profesor;
    public int nr_min_ore;
    public int nr_maxim_ore;
    public String cursuri;
    public String departament;

    public Profesor(int id_profesor, int nr_min_ore, int nr_maxim_ore, String cursuri, String departament) {
        this.id_profesor = id_profesor;
        this.nr_min_ore = nr_min_ore;
        this.nr_maxim_ore = nr_maxim_ore;
        this.cursuri = cursuri;
        this.departament = departament;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public int getNr_min_ore() {
        return nr_min_ore;
    }

    public int getNr_maxim_ore() {
        return nr_maxim_ore;
    }

    public String getCursuri() {
        return cursuri;
    }

    public String getDepartament() {
        return departament;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public void setNr_min_ore(int nr_min_ore) {
        this.nr_min_ore = nr_min_ore;
    }

    public void setNr_maxim_ore(int nr_maxim_ore) {
        this.nr_maxim_ore = nr_maxim_ore;
    }

    public void setCursuri(String cursuri) {
        this.cursuri = cursuri;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }
}
