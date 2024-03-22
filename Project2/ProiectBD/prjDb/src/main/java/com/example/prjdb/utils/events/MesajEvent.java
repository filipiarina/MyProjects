package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Mesaj;

public class MesajEvent implements Event {
    private ChangeEventType type;
    private Mesaj data, oldData;

    public MesajEvent(ChangeEventType type, Mesaj data) {
        this.type = type;
        this.data = data;
    }
    public MesajEvent(ChangeEventType type, Mesaj data, Mesaj oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Mesaj getData() {
        return data;
    }

    public Mesaj getOldData() {
        return oldData;
    }
}