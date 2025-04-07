package com.example.employeeattendance;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employeeapp.R;

public class AttendanceActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText editTextEmpId, editTextDate, editTextStatus;
    Button buttonMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        dbHelper = new DatabaseHelper(this);
        editTextEmpId = findViewById(R.id.editText_emp_id);
        editTextDate = findViewById(R.id.editText_date);
        editTextStatus = findViewById(R.id.editText_status);
        buttonMark = findViewById(R.id.button_mark_attendance);

        buttonMark.setOnClickListener(v -> {
            // ✅ Input Validation
            String empIdStr = editTextEmpId.getText().toString().trim();
            String date = editTextDate.getText().toString().trim();
            String status = editTextStatus.getText().toString().trim();

            if (empIdStr.isEmpty() || date.isEmpty() || status.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int empId;
            try {
                empId = Integer.parseInt(empIdStr); // ✅ Prevents crash due to empty input
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid Employee ID", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Validate Status Input
            if (!status.equalsIgnoreCase("Present") && !status.equalsIgnoreCase("Absent")) {
                Toast.makeText(this, "Status must be 'Present' or 'Absent'", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Insert into database
            if (dbHelper.markAttendance(empId, date, status)) {
                Toast.makeText(this, "Attendance Marked!", Toast.LENGTH_SHORT).show();
                editTextEmpId.setText("");  // ✅ Clear fields after successful marking
                editTextDate.setText("");
                editTextStatus.setText("");
            } else {
                Toast.makeText(this, "Failed to Mark Attendance!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
