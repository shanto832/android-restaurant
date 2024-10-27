package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationShow extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_show);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button signInButton = findViewById(R.id.signInButton);
        Button signUpButton = findViewById(R.id.signUpButton);
        Button forgotPasswordButton = findViewById(R.id.forgotPasswordButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void signIn() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(AuthenticationShow.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        // Proceed to the next activity
                        Intent intent = new Intent(AuthenticationShow.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(AuthenticationShow.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signUp() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign up success, update UI with the new user's information
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(AuthenticationShow.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                        // Proceed to the next activity
                        Intent intent = new Intent(AuthenticationShow.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If sign up fails, display a message to the user.
                        Toast.makeText(AuthenticationShow.this, "Sign Up Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(AuthenticationShow.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(AuthenticationShow.this, "Reset email sent.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AuthenticationShow.this, "Failed to send reset email.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
