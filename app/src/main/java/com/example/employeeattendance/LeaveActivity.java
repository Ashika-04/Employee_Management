package com.example.employeeattendance;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;

public class LeaveActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText editTextEmpId, editTextLeaveDate;
    Button buttonApplyLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        dbHelper = new DatabaseHelper(this);
        editTextEmpId = findViewById(R.id.editText_emp_id);
        editTextLeaveDate = findViewById(R.id.editText_leave_date);
        buttonApplyLeave = findViewById(R.id.button_apply_leave);

        editTextLeaveDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year1, month1, dayOfMonth) -> {
                        String selectedDate = year1 + "-" + (month1 + 1) + "-" + dayOfMonth;
                        editTextLeaveDate.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });

        buttonApplyLeave.setOnClickListener(v -> {
            int empId = Integer.parseInt(editTextEmpId.getText().toString());
            String leaveDate = editTextLeaveDate.getText().toString();

            if (dbHelper.markAttendance(empId, leaveDate, "Absent")) {
                Toast.makeText(this, "Leave Applied Successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to Apply Leave!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
