package com.example.brandon.myapplication;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Brandon on 2/27/2017.
 */

public class NotesTable {

    static final String TABLE_NAME = "notes";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_TEXT = "text";
    static final String COLUMN_PRIORITY = "priority";
    static final String COLUMN_COMPLETED = "completed";
    static final String COLUMN_TIME = "time";

    static public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + "(");
        sb.append(COLUMN_ID + " integer primary key autoincrement, " );
        sb.append(COLUMN_TEXT + " text not null, ");
        sb.append(COLUMN_PRIORITY + " text not null, ");
        sb.append(COLUMN_COMPLETED + " text not null, ");
        sb.append(COLUMN_TIME + " text not null);");

        try {
            db.execSQL(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        NotesTable.onCreate(db);
    }

}
