package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Grup_studiu;

public class GrupStudiuEvent implements Event {
    private ChangeEventType type;
    private Grup_studiu data, oldData;

    public GrupStudiuEvent(ChangeEventType type, Grup_studiu data) {
        this.type = type;
        this.data = data;
    }
    public GrupStudiuEvent(ChangeEventType type, Grup_studiu data, Grup_studiu oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Grup_studiu getData() {
        return data;
    }

    public Grup_studiu getOldData() {
        return oldData;
    }
}