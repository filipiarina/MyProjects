package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Profesor;

public class ProfesorEvent implements Event {
    private ChangeEventType type;
    private Profesor data, oldData;

    public ProfesorEvent(ChangeEventType type, Profesor data) {
        this.type = type;
        this.data = data;
    }
    public ProfesorEvent(ChangeEventType type, Profesor data, Profesor oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Profesor getData() {
        return data;
    }

    public Profesor getOldData() {
        return oldData;
    }
}