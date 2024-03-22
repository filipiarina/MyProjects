package com.example.prjdb.domain;

public class Inscriere {public Tuple<Integer, Integer> id;

    public Inscriere(Tuple<Integer, Integer> id) {
        this.id = id;
    }

    public Tuple<Integer, Integer> getId() {
        return id;
    }

    public void setId(Tuple<Integer, Integer> id) {
        this.id = id;
    }
}
