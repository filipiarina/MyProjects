package org.example.businesslogic.strategies;

import org.example.model.Server;
import org.example.model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        int min=10000;
        Server targetServer = null;
        for(Server s : servers){
            if(s.getNrTasks() < min){
                min=s.getNrTasks();
                targetServer = s;
            }
        }
        targetServer.addTask(task);
        System.out.println("Adding task " + task + " to server " + targetServer);
    }
}
