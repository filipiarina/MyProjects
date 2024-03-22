package com.clase;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Iterator;

public class Operatii {
    public Operatii() {
    }

    public static Polinom adunare(Polinom pol1, Polinom pol2) {
        Polinom rezultatPol = new Polinom();
        Iterator var5 = pol1.getPolinom().iterator();

        Monom m2;
        while(var5.hasNext()) {
            m2 = (Monom)var5.next();
            Iterator var7 = pol2.getPolinom().iterator();

            while(var7.hasNext()) {
                m2 = (Monom)var7.next();
                if (m2.getExponent() == m2.getExponent()) {
                    m2.setParcurs(true);
                    m2.setParcurs(true);
                    Monom rezMonom = new Monom(m2.getCoeficient() + m2.getCoeficient(), m2.getExponent());
                    rezultatPol.add(rezMonom);
                }
            }
        }

        var5 = pol1.getPolinom().iterator();

        while(var5.hasNext()) {
            m2 = (Monom)var5.next();
            if (!m2.getParcurs()) {
                rezultatPol.add(m2);
            }
        }

        var5 = pol2.getPolinom().iterator();

        while(var5.hasNext()) {
            m2 = (Monom)var5.next();
            if (!m2.getParcurs()) {
                rezultatPol.add(m2);
            }
        }

        return rezultatPol;
    }

    public static Polinom scadere(Polinom pol1, Polinom pol2) {
        Polinom rezultatPol = new Polinom();
        Iterator var5 = pol1.getPolinom().iterator();

        Monom m2;
        while(var5.hasNext()) {
            m2 = (Monom)var5.next();
            Iterator var7 = pol2.getPolinom().iterator();

            while(var7.hasNext()) {
                m2 = (Monom)var7.next();
                if (m2.getExponent() == m2.getExponent()) {
                    m2.setParcurs(true);
                    m2.setParcurs(true);
                    Monom rezMonom = new Monom(m2.getCoeficient() - m2.getCoeficient(), m2.getExponent());
                    rezultatPol.add(rezMonom);
                }
            }
        }

        var5 = pol1.getPolinom().iterator();

        while(var5.hasNext()) {
            m2 = (Monom)var5.next();
            if (!m2.getParcurs()) {
                rezultatPol.add(m2);
            }
        }

        var5 = pol2.getPolinom().iterator();

        while(var5.hasNext()) {
            m2 = (Monom)var5.next();
            if (!m2.getParcurs()) {
                m2.setCoeficient(0 - m2.getCoeficient());
                rezultatPol.add(m2);
            }
        }

        return rezultatPol;
    }

    public static Polinom derivare(Polinom pol1) {
        Polinom rezultatPol = new Polinom();
        Iterator var3 = pol1.getPolinom().iterator();

        while(var3.hasNext()) {
            Monom m = (Monom)var3.next();
            m.setCoeficient(m.getCoeficient() * m.getExponent());
            m.setExponent(m.getExponent() - 1);
            rezultatPol.add(m);
        }

        return rezultatPol;
    }

    public static Polinom produs(Polinom pol1, Polinom pol2) {
        Polinom rezultatPol = new Polinom();
        Iterator var5 = pol1.getPolinom().iterator();

        while(var5.hasNext()) {
            Monom m = (Monom)var5.next();
            Iterator var7 = pol2.getPolinom().iterator();

            while(var7.hasNext()) {
                Monom n = (Monom)var7.next();
                Monom rezMonom = new Monom(m.getCoeficient() * n.getCoeficient(), m.getExponent() + n.getExponent());
                rezultatPol.add(rezMonom);
            }
        }

        return rezultatPol;
    }
}
