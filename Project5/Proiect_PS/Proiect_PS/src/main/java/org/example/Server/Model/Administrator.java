package org.example.Server.Model;

public class Administrator extends Utilizator{
    private String telefon;

    public Administrator(int idUtilizator, String nume, String email, String parola, String telefon) {
        super(idUtilizator, nume, email, parola);
        this.telefon = telefon;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "telefon='" + telefon + '\'' +
                '}';
    }
}
