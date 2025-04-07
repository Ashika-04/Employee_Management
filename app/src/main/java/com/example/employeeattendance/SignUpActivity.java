package com.example.employeeattendance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText editTextUsername, editTextPassword;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelper = new DatabaseHelper(this);
        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
        buttonRegister = findViewById(R.id.button_register);

        buttonRegister.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            if (dbHelper.registerUser(username, password)) {
                Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

