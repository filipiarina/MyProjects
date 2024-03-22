package com.clase;

import java.util.ArrayList;
import java.util.List;

public class Polinom {
    private List<Monom> polinom = new ArrayList();

    public Polinom() {
    }

    public List<Monom> getPolinom() {
        return this.polinom;
    }

    public void add(Monom a) {
        this.polinom.add(a);
    }
}
