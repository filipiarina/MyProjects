package com.example.prjdb.utils.events;

import com.example.prjdb.domain.Activitate_grup;

public class ActivitateGrupEvent implements Event {
    private ChangeEventType type;
    private Activitate_grup data, oldData;

    public ActivitateGrupEvent(ChangeEventType type, Activitate_grup data) {
        this.type = type;
        this.data = data;
    }
    public ActivitateGrupEvent(ChangeEventType type, Activitate_grup data, Activitate_grup oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Activitate_grup getData() {
        return data;
    }

    public Activitate_grup getOldData() {
        return oldData;
    }
}