package com.example.taskmanagerapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "taskManager.db";
    public static final int DATABASE_VERSION = 1;

    // Table and Column Names
    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_TASK_ID = "_id";
    public static final String COLUMN_TASK_TITLE = "title";
    public static final String COLUMN_TASK_DESCRIPTION = "description";
    public static final String COLUMN_DUE_DATE = "due_date";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TASK_TITLE + " TEXT, " +
                    COLUMN_TASK_DESCRIPTION + " TEXT, " +
                    COLUMN_DUE_DATE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
