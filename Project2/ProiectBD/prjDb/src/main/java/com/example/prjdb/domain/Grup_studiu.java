package com.example.prjdb.domain;

public class Grup_studiu {
    public int id_grup;
    public int materie_id;

    public Grup_studiu(int id_grup, int materie_id) {
        this.id_grup = id_grup;
        this.materie_id = materie_id;
    }

    public int getId_grup() {
        return id_grup;
    }

    public int getMaterie_id() {
        return materie_id;
    }

    public void setId_grup(int id_grup) {
        this.id_grup = id_grup;
    }

    public void setMaterie_id(int materie_id) {
        this.materie_id = materie_id;
    }
}
