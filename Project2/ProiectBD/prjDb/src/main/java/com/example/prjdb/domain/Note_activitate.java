package com.example.prjdb.domain;

public class Note_activitate {
    public float nota;
    public Tuple<Integer, Integer> id;


    public Note_activitate(float nota, Tuple<Integer, Integer> id) {
        this.nota = nota;
        this.id = id;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Tuple<Integer, Integer> getId() {
        return id;
    }

    public void setId(Tuple<Integer, Integer> id) {
        this.id = id;
    }
}
