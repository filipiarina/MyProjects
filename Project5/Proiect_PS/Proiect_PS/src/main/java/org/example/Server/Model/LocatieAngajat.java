package org.example.Server.Model;

public class LocatieAngajat {
    private Integer idLocatieAngajat;
    private Integer idLocatieDeseu;
    private Integer idAngajat;

    public LocatieAngajat(Integer idLocatieAngajat, Integer idLocatieDeseu, Integer idAngajat) {
        this.idLocatieAngajat = idLocatieAngajat;
        this.idLocatieDeseu = idLocatieDeseu;
        this.idAngajat = idAngajat;
    }

    public Integer getIdLocatieAngajat() {
        return idLocatieAngajat;
    }

    public void setIdLocatieAngajat(Integer idLocatieAngajat) {
        this.idLocatieAngajat = idLocatieAngajat;
    }

    public Integer getIdLocatieDeseu() {
        return idLocatieDeseu;
    }

    public void setIdLocatieDeseu(Integer idCoordonator) {
        this.idLocatieDeseu = idCoordonator;
    }

    public Integer getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(Integer idAngajat) {
        this.idAngajat = idAngajat;
    }
}
