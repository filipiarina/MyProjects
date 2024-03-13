package org.example.businesslogic.strategies;

import org.example.model.Server;
import org.example.model.Task;

import java.util.List;

public class ShortestTimeStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        // iteram prin servere si obtinem serverul cu cel mai mic waitingperiod.
        int minWaitingPeriod = 100000;
        Server targetServer = null;
        for(Server s : servers){
            if(s.getWaitingPeriod() < minWaitingPeriod){
                minWaitingPeriod = s.getWaitingPeriod();
                targetServer = s;
            }
        }
        targetServer.addTask(task);
        System.out.println("Adding task " + task + " to server " + targetServer);
    }
}
