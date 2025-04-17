package com.example.taskmanagerapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText taskTitle, taskDescription, dueDate;
    private Button btnInsert, btnUpdate, btnDelete, btnView;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskTitle = findViewById(R.id.tasktitle);
        taskDescription = findViewById(R.id.taskdescription);
        dueDate = findViewById(R.id.duedate);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnupdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnView = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleTXT = taskTitle.getText().toString();
                String taskDescriptionTXT = taskDescription.getText().toString();
                String dateTXT = dueDate.getText().toString();

                Boolean checkinsertdata = DB.inserttaskdata(titleTXT, taskDescriptionTXT, dateTXT);

                if (checkinsertdata) {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleTXT = taskTitle.getText().toString();
                String taskDescriptionTXT = taskDescription.getText().toString();
                String dateTXT = dueDate.getText().toString();

                Boolean checkupdatedata = DB.updatetaskdata(titleTXT, taskDescriptionTXT, dateTXT);

                if (checkupdatedata) {
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleTXT = taskTitle.getText().toString();

                Boolean checkdeletedata = DB.deletetaskdata(titleTXT);

                if (checkdeletedata) {
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Task Title :" + res.getString(0) + "\n");
                    buffer.append("Task Description :" + res.getString(1) + "\n");
                    buffer.append("Due Date :" + res.getString(2) + "\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Task Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
