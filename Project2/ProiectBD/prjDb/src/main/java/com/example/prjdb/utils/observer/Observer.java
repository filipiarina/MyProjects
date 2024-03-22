package com.example.prjdb.utils.observer;


import com.example.prjdb.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}