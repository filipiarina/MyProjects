package org.example.model;

import org.example.model.Task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private String name;

    private boolean isStopAsked = false;

    public Server(String name){
        this.name = name;
        waitingPeriod= new AtomicInteger(0);
        tasks = new ArrayBlockingQueue<>(100);
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.tService);
    }

    public int getNrTasks(){
        return tasks.size();
    }

    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }

    @Override
    public void run() {
        while (!isStopAsked) {
         /*   try {
                Task task = tasks.take();
                System.out.println(name + " processing task " + task);
                for (int i=0;i<task.tService; i++) {
                    System.out.println(name + " waiting time: " + waitingPeriod.toString());
                    Thread.sleep(1000);
                    waitingPeriod.decrementAndGet();
                }
                System.out.println(name + " finished processing task " + task);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            try {
                if(tasks.size()!=0){
                    Task t=tasks.element();
                    Thread.sleep(t.gettService()*1000);
                    waitingPeriod.set(waitingPeriod.get()-t.gettService());
                    tasks.remove();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Server{" +
                "tasks=" + tasks +
                ", waitingPeriod=" + waitingPeriod +
                ", name='" + name + '\'' +
                '}';
    }

    public void stopServer() {
        isStopAsked = true;
        System.out.println("Server " + name + " is being stopped");
    }

    public String logStatus() {
        if (tasks.isEmpty()) {
            return name + ": closed";
        }
        else {
            String serverStatus = name + ": ";
            for (Task t: tasks) {
                serverStatus += t.toString();
            }
            return serverStatus;
        }
    }

    public int sizeT(){
        return  tasks.size();
    }
}