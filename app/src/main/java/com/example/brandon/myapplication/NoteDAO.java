package com.example.brandon.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 2/27/2017.
 */

public class NoteDAO {
    private SQLiteDatabase db;

    public NoteDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Note note) {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_TEXT, note.getText());
        values.put(NotesTable.COLUMN_PRIORITY, note.getPriority());
        values.put(NotesTable.COLUMN_COMPLETED, note.isCompleted() + "");
        values.put(NotesTable.COLUMN_TIME, note.getTime() + "");

        return db.insert(NotesTable.TABLE_NAME, null, values);
    }

    public boolean update(Note note) {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_TEXT, note.getText());
        values.put(NotesTable.COLUMN_PRIORITY, note.getPriority());
        values.put(NotesTable.COLUMN_COMPLETED, note.isCompleted() + "");
        values.put(NotesTable.COLUMN_TIME, note.getTime() + "");

        return db.update(NotesTable.TABLE_NAME, values, NotesTable.COLUMN_ID + "=?", new String[]{note.get_id() + ""}) > 0;
    }

    public boolean delete(Note note) {
        return db.delete(NotesTable.TABLE_NAME, NotesTable.COLUMN_ID + "=?", new String[]{note.get_id() + ""}) > 0;
    }

    public Note get(long id) {
        Note note = null;

        Cursor c  = db.query(true, NotesTable.TABLE_NAME, new String[]{NotesTable.COLUMN_ID,
                NotesTable.COLUMN_TEXT, NotesTable.COLUMN_PRIORITY, NotesTable.COLUMN_COMPLETED, NotesTable.COLUMN_TIME},
                NotesTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            note = buildNoteFromCursor(c);
            if (!c.isClosed()) {
                c.close();
            }
        }

        return note;
    }

    public List<Note> getAll() {
        List<Note> notes = new ArrayList<>();

        Cursor c = db.query(NotesTable.TABLE_NAME, new String[]{NotesTable.COLUMN_ID,
                        NotesTable.COLUMN_TEXT, NotesTable.COLUMN_PRIORITY, NotesTable.COLUMN_COMPLETED, NotesTable.COLUMN_TIME},
                null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            do {
                Note note = buildNoteFromCursor(c);
                if (note != null) {
                    notes.add(note);
                }
            } while (c.moveToNext());
            if (!c.isClosed()) {
                c.close();
            }
        }
        return notes;
    }

    private Note buildNoteFromCursor(Cursor c) {
        Note note = null;
        if (c != null) {
            note = new Note();
            note.set_id(c.getLong(0));
            note.setText(c.getString(1));
            note.setPriority(c.getString(2));
            note.setCompleted(Boolean.parseBoolean(c.getString(3)));
            note.setTime(Long.parseLong(c.getString(4)));
        }
        return note;
    }
}
