package org.example.model;

public class Task implements Comparable {
    public int ID;
    public int tArrival;
    public int tService;

    public Task(int ID, int tArrival, int tService) {
        this.ID = ID;
        this.tArrival = tArrival;
        this.tService = tService;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(tArrival, ((Task) o).tArrival);
    }

    @Override
    public String toString() {
        return "(" + ID + ", " + tArrival + ", " + tService + "); ";
    }

    public int gettArrival() {
        return tArrival;
    }

    public int getID() {
        return ID;
    }

    public int gettService() {
        return tService;
    }
}
