package com.example.brandon.myapplication;

/**
 * Created by Brandon on 2/27/2017.
 */

public class Note {
    private long _id;
    private String text;
    private String priority;
    private long time;
    private boolean completed;

    static public Note create(String text, String priority) {
        Note note = new Note();
        note.setText(text);
        note.setPriority(priority);
        note.setTime(System.currentTimeMillis());
        note.setCompleted(false);
        return note;
    }

    public Note(String text, String priority) {
        this.text = text;
        this.priority = priority;
        this.time = System.currentTimeMillis();
        this.completed = false;
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id=" + _id +
                ", text='" + text + '\'' +
                ", priority='" + priority + '\'' +
                ", time=" + time +
                ", completed=" + completed +
                '}';
    }

    public Note() {}

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
