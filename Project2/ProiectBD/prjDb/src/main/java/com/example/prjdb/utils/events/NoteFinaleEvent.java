package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Note_finale;

public class NoteFinaleEvent implements Event {
    private ChangeEventType type;
    private Note_finale data, oldData;

    public NoteFinaleEvent(ChangeEventType type, Note_finale data) {
        this.type = type;
        this.data = data;
    }
    public NoteFinaleEvent(ChangeEventType type, Note_finale data, Note_finale oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Note_finale getData() {
        return data;
    }

    public Note_finale getOldData() {
        return oldData;
    }
}