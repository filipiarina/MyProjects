package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Inscriere;

public class InscriereEvent implements Event {
    private ChangeEventType type;
    private Inscriere data, oldData;

    public InscriereEvent(ChangeEventType type, Inscriere data) {
        this.type = type;
        this.data = data;
    }
    public InscriereEvent(ChangeEventType type, Inscriere data, Inscriere oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Inscriere getData() {
        return data;
    }

    public Inscriere getOldData() {
        return oldData;
    }
}