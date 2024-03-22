package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Activitate;

public class ActivitateEvent implements Event {
    private ChangeEventType type;
    private Activitate data, oldData;

    public ActivitateEvent(ChangeEventType type, Activitate data) {
        this.type = type;
        this.data = data;
    }
    public ActivitateEvent(ChangeEventType type, Activitate data, Activitate oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Activitate getData() {
        return data;
    }

    public Activitate getOldData() {
        return oldData;
    }
}