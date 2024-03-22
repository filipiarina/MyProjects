package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Utilizator;

public class UtilizatorEvent implements Event {
    private ChangeEventType type;
    private Utilizator data, oldData;

    public UtilizatorEvent(ChangeEventType type, Utilizator data) {
        this.type = type;
        this.data = data;
    }
    public UtilizatorEvent(ChangeEventType type, Utilizator data, Utilizator oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Utilizator getData() {
        return data;
    }

    public Utilizator getOldData() {
        return oldData;
    }
}