package org.example.businesslogic;

import org.example.businesslogic.SimulationManager;

import java.util.Timer;
import java.util.TimerTask;

public class SimulationTimer extends TimerTask {
    private final SimulationManager manager;
    private Timer timer;
    private int timeLimit ;
    private int simulationTime = 0;

    public SimulationTimer(int timeLimit, Timer timer, SimulationManager manager) {
        this.timeLimit = timeLimit;
        this.timer=timer;
        this.manager = manager;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    @Override
    public void run() {
        System.out.println("\n" + simulationTime + "/" + timeLimit);
        float b=0;
        b=manager.medieServire();
        manager.updateSimulation(simulationTime);
        int a=0;
        a=manager.peakHour(simulationTime);
        if(simulationTime == timeLimit){
            timer.cancel();
            System.out.println("Simulation ended");
            System.out.println("Peak Hour:" + a);
            System.out.println("Average Service Time:" + b);
        }
        simulationTime++;
    }
}
