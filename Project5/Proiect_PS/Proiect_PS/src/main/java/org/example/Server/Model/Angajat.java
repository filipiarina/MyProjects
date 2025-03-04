package org.example.Server.Model;

public class Angajat extends Utilizator{
    private String departament;
    private Integer aniExperienta;

    public Angajat(int idUtilizator, String nume, String email, String parola, String departament, Integer aniExperienta) {
        super(idUtilizator, nume, email, parola);
        this.departament = departament;
        this.aniExperienta = aniExperienta;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public Integer getAniExperienta() {
        return aniExperienta;
    }

    public void setAniExperienta(Integer aniExperienta) {
        this.aniExperienta = aniExperienta;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "departament='" + departament + '\'' +
                ", aniExperienta=" + aniExperienta +
                '}';
    }
}
