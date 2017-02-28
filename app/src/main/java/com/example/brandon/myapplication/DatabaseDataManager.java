package com.example.brandon.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Brandon on 2/27/2017.
 */

public class DatabaseDataManager {
    private Context context;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private NoteDAO noteDAO;

    public DatabaseDataManager(Context context) {
        this.context = context;
        dbOpenHelper = new DatabaseOpenHelper(context);
        db = dbOpenHelper.getWritableDatabase();
        noteDAO = new NoteDAO(db);
    }

    public void close() {
        if (db.isOpen()) {
            db.close();
        }
    }

    public NoteDAO getNoteDAO() {
        return noteDAO;
    }

    public long saveNote(Note note) {
        return noteDAO.save(note);
    }

    public boolean updateNote(Note note) {
        return noteDAO.update(note);
    }

    public boolean deleteNote(Note note) {
        return noteDAO.delete(note);
    }

    public Note getNote(long id) {
        return noteDAO.get(id);
    }

    public List<Note> getAllNotes() {
        return noteDAO.getAll();
    }


}
