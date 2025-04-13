package org.app.model;

import java.util.Date;

public class TaskModel {
    private long id;
    private Date date;
    private String text;
    private boolean done;

    public TaskModel(long id, Date date, String text, boolean done) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
