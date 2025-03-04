package org.example.Server.Model;

public class LocatieDeseu {
    private int idLocatie;
    private String adresaLocatie;
    private String tipDeseu;
    private String stadiu;

    public LocatieDeseu(int idLocatie, String adresaLocatie, String tipDeseu, String stadiu) {
        this.idLocatie = idLocatie;
        this.adresaLocatie = adresaLocatie;
        this.tipDeseu = tipDeseu;
        this.stadiu = stadiu;
    }

    public int getIdLocatie() {
        return idLocatie;
    }

    public void setIdLocatie(int idLocatie) {
        this.idLocatie = idLocatie;
    }

    public String getAdresaLocatie() {
        return adresaLocatie;
    }

    public void setAdresaLocatie(String adresaLocatie) {
        this.adresaLocatie = adresaLocatie;
    }

    public String getTipDeseu() {
        return tipDeseu;
    }

    public void setTipDeseu(String tipDeseu) {
        this.tipDeseu = tipDeseu;
    }

    public String getStadiu() {
        return stadiu;
    }

    public void setStadiu(String stadiu) {
        this.stadiu = stadiu;
    }

    @Override
    public String toString() {
        return "LocatieDeseu{" +
                "idLocatie=" + idLocatie +
                ", adresaLocatie='" + adresaLocatie + '\'' +
                ", tipDeseu='" + tipDeseu + '\'' +
                ", stadiu='" + stadiu + '\'' +
                '}';
    }
}
