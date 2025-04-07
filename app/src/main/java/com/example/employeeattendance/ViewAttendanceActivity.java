package com.example.employeeattendance;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewAttendanceActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    TextView textViewAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        dbHelper = new DatabaseHelper(this);
        textViewAttendance = findViewById(R.id.textView_attendance);

        displayAttendanceReport();
    }

    private void displayAttendanceReport() {
        Cursor cursor = dbHelper.getAttendanceReport();
        StringBuilder report = new StringBuilder();

        while (cursor.moveToNext()) {
            report.append("Employee ID: ").append(cursor.getInt(0)).append("\n")
                    .append("Date: ").append(cursor.getString(1)).append("\n")
                    .append("Status: ").append(cursor.getString(2)).append("\n\n");
        }

        textViewAttendance.setText(report.toString());
        cursor.close();
    }
}

