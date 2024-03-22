package com.example.prjdb.utils.events;


import com.example.prjdb.domain.Note_activitate;

public class NoteActivitateEvent implements Event {
    private ChangeEventType type;
    private Note_activitate data, oldData;

    public NoteActivitateEvent(ChangeEventType type, Note_activitate data) {
        this.type = type;
        this.data = data;
    }
    public NoteActivitateEvent(ChangeEventType type, Note_activitate data, Note_activitate oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Note_activitate getData() {
        return data;
    }

    public Note_activitate getOldData() {
        return oldData;
    }
}