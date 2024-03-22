package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Materie;

public class MaterieEvent implements Event {
    private ChangeEventType type;
    private Materie data, oldData;

    public MaterieEvent(ChangeEventType type, Materie data) {
        this.type = type;
        this.data = data;
    }
    public MaterieEvent(ChangeEventType type, Materie data, Materie oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Materie getData() {
        return data;
    }

    public Materie getOldData() {
        return oldData;
    }
}