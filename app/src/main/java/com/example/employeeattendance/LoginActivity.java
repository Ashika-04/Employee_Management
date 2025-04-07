package com.example.employeeattendance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.employeeapp.R;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    Button buttonLogin, buttonSignup;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Find views
        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
        buttonLogin = findViewById(R.id.button_login);
        buttonSignup = findViewById(R.id.button_signup);

        // Login button click listener
        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.checkUser(username, password)) {
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish(); // Close login activity
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // Sign-up button click listener
        buttonSignup.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignUpActivity.class)));
    }
}
