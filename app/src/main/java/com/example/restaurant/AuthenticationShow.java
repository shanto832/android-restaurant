package com.example.restaurant;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AuthenticationShow extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_show);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button signInButton = findViewById(R.id.signInButton);
        Button signUpButton = findViewById(R.id.signUpButton);
        Button forgotPasswordButton = findViewById(R.id.forgotPasswordButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthenticationShow.this, SignInInfo.class);
                intent.putExtra("EMAIL", emailEditText.getText().toString());
                intent.putExtra("PASSWORD", passwordEditText.getText().toString());
                startActivity(intent);
            }
        });

        // You can set onClick listeners for sign up and forgot password buttons here
    }
}
