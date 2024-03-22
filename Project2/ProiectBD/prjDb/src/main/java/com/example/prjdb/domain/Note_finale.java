package com.example.prjdb.domain;

public class Note_finale {
    public float nota_finala;
    public Tuple<Integer, Integer> id;

    public Note_finale(float nota_finala, Tuple<Integer, Integer> id) {
        this.nota_finala = nota_finala;
        this.id = id;
    }

    public float getNota_finala() {
        return nota_finala;
    }

    public void setNota_finala(float nota_finala) {
        this.nota_finala = nota_finala;
    }

    public Tuple<Integer, Integer> getId() {
        return id;
    }

    public void setId(Tuple<Integer, Integer> id) {
        this.id = id;
    }
}
