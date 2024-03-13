package org.example.businesslogic;

import org.example.businesslogic.strategies.ShortestQueueStrategy;
import org.example.businesslogic.strategies.ShortestTimeStrategy;
import org.example.businesslogic.strategies.Strategy;
import org.example.model.Server;
import org.example.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers = new ArrayList<>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
       // strategy = new ShortestQueueStrategy();
        strategy = new ShortestTimeStrategy();
       // changeStrategy(SelectionPolicy.SHORTEST_QUEUE);
       // changeStrategy(SelectionPolicy.SHORTEST_TIME);
        for(int i=0; i<maxNoServers; i++){
            Server s= new Server("Server " + i);
            servers.add(s);
            Thread t = new Thread(s);
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ShortestTimeStrategy();

        if(policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ShortestQueueStrategy();
    }

    public void dispatchTask(Task t){
        strategy.addTask(servers,t);
    }


    public void stopServers() {
        for (Server s : servers) {
            s.stopServer();
        }
    }

    public String logStatus() {
        // for each server, print
        // Queue 1: (1,2,2);
        //Queue 2: closed
        String schedulerStatus="";
        for (Server s: servers) {
            schedulerStatus += s.logStatus() + "\n";
        }
        return schedulerStatus;
    }

    public List<Server> getServers() {
        return servers;
    }
}
