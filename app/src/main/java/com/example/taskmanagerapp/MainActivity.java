package com.example.taskmanagerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText taskID, taskTitle, taskDescription, dueDate;
    private Button btnInsert, btnUpdate, btnDelete, btnView;
    private TextView titleText, textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskID = findViewById(R.id.taskID);
        taskTitle = findViewById(R.id.tasktitle);
        taskDescription = findViewById(R.id.taskdescription);
        dueDate = findViewById(R.id.duedate);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnupdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnView = findViewById(R.id.btnView);
        titleText = findViewById(R.id.title);
        textTitle = findViewById(R.id.texttitle);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewTaskDetails();
            }
        });
    }


    private void addTask() {
        String taskId = taskID.getText().toString().trim();
        String title = taskTitle.getText().toString().trim();
        String description = taskDescription.getText().toString().trim();
        String dueDateText = dueDate.getText().toString().trim();


        if (!taskId.isEmpty() && !title.isEmpty() && !description.isEmpty() && !dueDateText.isEmpty()) {
            textTitle.setText("Task Added: " + title);
        } else {
            textTitle.setText("Please fill all fields.");
        }
    }


    private void updateTask() {
        String taskId = taskID.getText().toString().trim();
        String title = taskTitle.getText().toString().trim();
        String description = taskDescription.getText().toString().trim();
        String dueDateText = dueDate.getText().toString().trim();


        if (!taskId.isEmpty() && !title.isEmpty() && !description.isEmpty() && !dueDateText.isEmpty()) {
            textTitle.setText("Task Updated: " + title);
        } else {
            textTitle.setText("Please fill all fields.");
        }
    }


    private void deleteTask() {
        String taskId = taskID.getText().toString().trim();
        if (!taskId.isEmpty()) {

            textTitle.setText("Task Deleted: ID " + taskId);
        } else {
            textTitle.setText("Please enter a valid Task ID.");
        }
    }


    private void viewTaskDetails() {
        String taskId = taskID.getText().toString().trim();
        if (!taskId.isEmpty()) {
            textTitle.setText("Task Details for ID: " + taskId + "\nTitle: Task Title\nDescription: Task Description\nDue Date: 2025-04-10");
        } else {
            textTitle.setText("Please enter a valid Task ID.");
        }
    }
}
