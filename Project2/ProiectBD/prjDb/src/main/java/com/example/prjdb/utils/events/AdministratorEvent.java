package com.example.prjdb.utils.events;

import com.example.prjdb.domain.Administrator;

public class AdministratorEvent implements Event {
    private ChangeEventType type;
    private Administrator data, oldData;

    public AdministratorEvent(ChangeEventType type, Administrator data) {
        this.type = type;
        this.data = data;
    }
    public AdministratorEvent(ChangeEventType type, Administrator data, Administrator oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Administrator getData() {
        return data;
    }

    public Administrator getOldData() {
        return oldData;
    }
}