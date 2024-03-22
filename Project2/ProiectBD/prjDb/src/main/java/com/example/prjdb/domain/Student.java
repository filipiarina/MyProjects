package com.example.prjdb.domain;

public class Student {
    public int id_student;
    public String an_studiu;
    public int grup_id;

    public Student(int id_student, String an_studiu, int grup_id) {
        this.id_student = id_student;
        this.an_studiu = an_studiu;
        this.grup_id = grup_id;
    }



    public int getId_student() {
        return id_student;
    }

    public String getAn_studiu() {
        return an_studiu;
    }

    public int getGrup_id() {
        return grup_id;
    }

    public void setAn_studiu(String an_studiu) {
        this.an_studiu = an_studiu;
    }

    public void setGrup_id(int grup_id) {
        this.grup_id = grup_id;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }
}
