package com.example.prjdb.domain;

public class Inscriere_grup {
    public Tuple<Integer, Integer> id;

    public Inscriere_grup(Tuple<Integer, Integer> id) {
        this.id = id;
    }

    public Tuple<Integer, Integer> getId() {
        return id;
    }

    public void setId(Tuple<Integer, Integer> id) {
        this.id = id;
    }
}
