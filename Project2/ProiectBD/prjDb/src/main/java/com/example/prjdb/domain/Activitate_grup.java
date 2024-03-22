package com.example.prjdb.domain;

public class Activitate_grup {
    public int id_activitate_grup;
    public String descriere_activitate;
    public String data_activitate;
    public String ora_inceput_activitate;
    public String durata_activitate;
    public int nr_min_participanti;
    public String durata_exp_accept;
    public int student_id;
    public int grup_id;

    public Activitate_grup(int id_activitate_grup, String descriere_activitate, String data_activitate, String ora_inceput_activitate,
                           String durata_activitate, int nr_min_participanti, String durata_exp_accept, int student_id, int grup_id) {
        this.id_activitate_grup = id_activitate_grup;
        this.descriere_activitate = descriere_activitate;
        this.data_activitate = data_activitate;
        this.ora_inceput_activitate = ora_inceput_activitate;
        this.durata_activitate = durata_activitate;
        this.nr_min_participanti = nr_min_participanti;
        this.durata_exp_accept = durata_exp_accept;
        this.student_id = student_id;
        this.grup_id = grup_id;
    }

    public Activitate_grup(Integer aLong, Integer materie_id, String tip_activitate, Boolean colocviu, Boolean examen, String procent, Integer nr_max_participanti) {
    }


    public int getId_activitate_grup() {
        return id_activitate_grup;
    }

    public String getDescriere_activitate() {
        return descriere_activitate;
    }

    public String getData_activitate() {
        return data_activitate;
    }

    public String getOra_inceput_activitate() {
        return ora_inceput_activitate;
    }

    public String getDurata_activitate() {
        return durata_activitate;
    }

    public int getNr_min_participanti() {
        return nr_min_participanti;
    }

    public String getDurata_exp_accept() {
        return durata_exp_accept;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getGrup_id() {
        return grup_id;
    }

    public void setId_activitate_grup(int id_activitate_grup) {
        this.id_activitate_grup = id_activitate_grup;
    }

    public void setDescriere_activitate(String descriere_activitate) {
        this.descriere_activitate = descriere_activitate;
    }

    public void setData_activitate(String data_activitate) {
        this.data_activitate = data_activitate;
    }

    public void setOra_inceput_activitate(String ora_inceput_activitate) {
        this.ora_inceput_activitate = ora_inceput_activitate;
    }

    public void setDurata_activitate(String durata_activitate) {
        this.durata_activitate = durata_activitate;
    }

    public void setNr_min_participanti(int nr_min_participanti) {
        this.nr_min_participanti = nr_min_participanti;
    }

    public void setDurata_exp_accept(String durata_exp_accept) {
        this.durata_exp_accept = durata_exp_accept;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setGrup_id(int grup_id) {
        this.grup_id = grup_id;
    }
}
