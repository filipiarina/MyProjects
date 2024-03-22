package com.example.prjdb.domain;

public class Administrator {
    public int id_administrator;
    public String tip;

    public Administrator(int id_administrator, String tip) {
        this.id_administrator = id_administrator;
        this.tip = tip;
    }

    public int getId_administrator() {
        return id_administrator;
    }

    public String getTip() {
        return tip;
    }

    public void setId_administrator(int id_administrator) {
        this.id_administrator = id_administrator;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
