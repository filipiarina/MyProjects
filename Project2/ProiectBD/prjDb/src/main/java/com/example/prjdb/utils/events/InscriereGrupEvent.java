package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Inscriere_grup;

public class InscriereGrupEvent implements Event {
    private ChangeEventType type;
    private Inscriere_grup data, oldData;

    public InscriereGrupEvent(ChangeEventType type, Inscriere_grup data) {
        this.type = type;
        this.data = data;
    }
    public InscriereGrupEvent(ChangeEventType type, Inscriere_grup data, Inscriere_grup oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Inscriere_grup getData() {
        return data;
    }

    public Inscriere_grup getOldData() {
        return oldData;
    }
}