package org.example.businesslogic;

import org.example.gui.SimulationFrame;
import org.example.model.Server;
import org.example.model.Task;

import java.util.*;

public class SimulationManager {
    public int timeLimit = 20;
    public int maxProcessingTime = 10;
    public int minProcessingTime = 2;
    public int numberOfServers = 2;
    public int numberOfClients = 10;
    public SelectionPolicy selPol = SelectionPolicy.SHORTEST_TIME;
    public Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks = new ArrayList<>();

    private int maxP=0;
    private int peakH;

    private float medie=0;

    SimulationTimer simulationTimer;

    public SimulationManager(){
        generateNRandomTasks();
        System.out.println(generatedTasks);

        scheduler = new Scheduler(numberOfServers, 10);
        runSimulation();
    }

    public int peakHour(int time){
        int suma=0;
        for(Server s:scheduler.getServers()){
            suma+=s.sizeT();
        }
        if(suma>maxP){
            maxP=suma;
            peakH=time;
        }
        return peakH;
    }

    public float medieServire(){
        for (Task t:generatedTasks) {
            medie+=t.gettService();
        }
        medie=medie/numberOfClients;
        return medie;
    }

    private void runSimulation() {
        Timer timer = new Timer();
        simulationTimer = new SimulationTimer(timeLimit, timer, this);
        timer.scheduleAtFixedRate(simulationTimer,0,1000);
    }

    public void updateSimulation(int simulationTime)
    {
        for(Task t : generatedTasks) {
            if(simulationTimer.getSimulationTime() == t.tArrival){
                scheduler.dispatchTask(t);
            }
        }
        if (simulationTime == timeLimit)
        {
            scheduler.stopServers();
        }
        logStatus(simulationTime);
    }

    private void logStatus(int simulationTime) {
        String managerStatus = "Time " + simulationTime + "\n"
                + "Waiting clients: " + getWaitingClients(simulationTime) + "\n"
                + scheduler.logStatus();
        System.out.println(managerStatus);
    }

    private String getWaitingClients(int simulationTime) {
        //sa afisam o lista de tasks a caror tArrival e mai mare sau egal decat simulationTime.
        String waitingClients="";
        for (Task t:generatedTasks) {
            if (t.tArrival > simulationTime) {
                waitingClients += t.toString();
            }
        }
        return waitingClients;
    }

    private void generateNRandomTasks(){
        for(int i=0; i< numberOfClients; i++){
            int randomServiceTime = (int) (minProcessingTime + Math.random()*(maxProcessingTime-minProcessingTime));
            int randomArrivalTime= (int) (Math.random()*timeLimit);
            Task t = new Task(i, randomArrivalTime, randomServiceTime);
            generatedTasks.add(t);
        }
        Collections.sort(generatedTasks);
    }

    public static void main(String[] args) {
        SimulationManager app = new SimulationManager();
    }

    public void stopSimulation() {

    }
}