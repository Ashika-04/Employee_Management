package com.example.employeeattendance;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employeeapp.R;


public class HomeActivity extends AppCompatActivity {
    Button btnEmployeeDetails, btnMarkAttendance, btnViewDetails, btnLeaveApply, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnEmployeeDetails = findViewById(R.id.button_employee_details);
        btnMarkAttendance = findViewById(R.id.button_mark_attendance);
        btnViewDetails = findViewById(R.id.button_view_details);
        btnLeaveApply = findViewById(R.id.button_leave_apply);
        btnLogout = findViewById(R.id.button_logout);

        btnEmployeeDetails.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, EmployeeActivity.class)));
        btnMarkAttendance.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, AttendanceActivity.class)));
        btnViewDetails.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ViewDetailsActivity.class)));
        btnLeaveApply.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, LeaveActivity.class)));
        btnLogout.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        });
    }
}

