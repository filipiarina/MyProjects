package com.example.prjdb.domain;

public class Activitate {
    public int id_activitate;
    public int materie_id;
    public String tip_activitate;
    public boolean colocviu;
    public boolean examen;
    public String procent;
    public int nr_maxim_participanti;

    public Activitate(int id_activitate, int materie_id, String tip_activitate, boolean colocviu, boolean examen, String procent, int nr_maxim_participanti) {
        this.id_activitate = id_activitate;
        this.materie_id = materie_id;
        this.tip_activitate = tip_activitate;
        this.colocviu = colocviu;
        this.examen = examen;
        this.procent = procent;
        this.nr_maxim_participanti = nr_maxim_participanti;
    }

    public int getId_activitate() {
        return id_activitate;
    }

    public int getMaterie_id() {
        return materie_id;
    }

    public String getTip_activitate() {
        return tip_activitate;
    }

    public boolean isColocviu() {
        return colocviu;
    }

    public boolean isExamen() {
        return examen;
    }

    public String getProcent() {
        return procent;
    }

    public int getNr_maxim_participanti() {
        return nr_maxim_participanti;
    }

    public void setId_activitate(int id_activitate) {
        this.id_activitate = id_activitate;
    }

    public void setMaterie_id(int materie_id) {
        this.materie_id = materie_id;
    }

    public void setTip_activitate(String tip_activitate) {
        this.tip_activitate = tip_activitate;
    }

    public void setColocviu(boolean colocviu) {
        this.colocviu = colocviu;
    }

    public void setExamen(boolean examen) {
        this.examen = examen;
    }

    public void setProcent(String procent) {
        this.procent = procent;
    }

    public void setNr_maxim_participanti(int nr_maxim_participanti) {
        this.nr_maxim_participanti = nr_maxim_participanti;
    }


}
