package com.example.employeeattendance;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employeeapp.R;

public class EmployeeActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText editTextName, editTextPosition;
    Button buttonAdd, buttonDelete, buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        dbHelper = new DatabaseHelper(this);
        editTextName = findViewById(R.id.editText_name);
        editTextPosition = findViewById(R.id.editText_position);
        buttonAdd = findViewById(R.id.button_add);
        buttonDelete = findViewById(R.id.button_delete);
        buttonUpdate = findViewById(R.id.button_update);

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String position = editTextPosition.getText().toString();

            if (dbHelper.addEmployee(name, position)) {
                Toast.makeText(this, "Employee Added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to Add Employee!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDelete.setOnClickListener(v -> {
            String id = editTextName.getText().toString();
            dbHelper.deleteEmployee(Integer.parseInt(id));
            Toast.makeText(this, "Employee Deleted!", Toast.LENGTH_SHORT).show();
        });

        buttonUpdate.setOnClickListener(v -> {
            String id = editTextName.getText().toString();
            String name = editTextName.getText().toString();
            String position = editTextPosition.getText().toString();

            if (dbHelper.updateEmployee(Integer.parseInt(id), name, position)) {
                Toast.makeText(this, "Employee Updated!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
