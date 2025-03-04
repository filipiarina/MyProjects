package org.example.Server.Model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    protected List<Observable> obsList = new ArrayList<>();

    public void add(Observable obs) {
        this.obsList.add(obs);
        this.notifyObservers();
    }

    public void delete(Observable obs) {
        this.obsList.remove(obs);
        this.notifyObservers();
    }

    public void notifyObservers() {
        for (Observable obs : this.obsList) {
            obs.update(this);
        }
    }

    public List<Observable> getObsList() {
        return this.obsList;
    }

    public void setObsList(List<Observable> obsList) {
        this.obsList = obsList;
    }
}
