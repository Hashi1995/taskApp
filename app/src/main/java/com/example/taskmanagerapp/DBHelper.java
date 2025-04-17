package com.example.taskmanagerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Taskdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Userdetails(taskTitle TEXT PRIMARY KEY, taskDescription TEXT, dueDate TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS Userdetails");
    }

    public Boolean inserttaskdata(String title, String taskDescription, String date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("taskTitle", title);
        contentValues.put("taskDescription", taskDescription);
        contentValues.put("dueDate", date);

        long result = DB.insert("Userdetails", null, contentValues);
        return result != -1; // returns true if inserted, false otherwise
    }

    public Boolean updatetaskdata(String title, String taskDescription, String date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("taskDescription", taskDescription);
        contentValues.put("dueDate", date);

        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE taskTitle = ?", new String[]{title});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "taskTitle=?", new String[]{title});
            return result != -1;
        } else {
            return false;
        }
    }


    public Boolean deletetaskdata(String title) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE taskTitle = ?", new String[]{title});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "taskTitle=?", new String[]{title});
            return result != -1;
        } else {
            return false;
        }
    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Userdetails", null);
    }
}

