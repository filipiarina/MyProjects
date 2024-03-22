package com.clase;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Monom {
    private int coeficient;
    private int exponent;
    boolean parcurs;

    public Monom(int coeficient, int exponent) {
        this.coeficient = coeficient;
        this.exponent = exponent;
    }

    public void setCoeficient(int coeficient) {
        this.coeficient = coeficient;
    }

    public int getCoeficient() {
        return this.coeficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    protected int getExponent() {
        return this.exponent;
    }

    public String toString() {
        if (this.coeficient == 0 && this.exponent == 0) {
            return "";
        } else if (this.coeficient == 0) {
            return "";
        } else if (this.exponent == 0 && this.coeficient > 0) {
            return "+" + this.coeficient;
        } else if (this.exponent == 0 && this.coeficient < 0) {
            return String.valueOf(this.coeficient);
        } else if (this.coeficient == 1 && this.exponent == 1) {
            return "X";
        } else {
            return this.exponent > 0 && this.coeficient > 0 ? "+" + this.coeficient + "X^" + this.exponent : this.coeficient + "X^" + this.exponent;
        }
    }

    public void setParcurs(boolean parcurs) {
        this.parcurs = parcurs;
    }

    public boolean getParcurs() {
        return this.parcurs;
    }
}
