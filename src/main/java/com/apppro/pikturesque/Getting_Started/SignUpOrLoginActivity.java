package com.apppro.pikturesque.Getting_Started;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.apppro.pikturesque.R;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpOrLoginActivity extends AppCompatActivity {
Button btnSignUp, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_or_login);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignupActivity = new Intent (SignUpOrLoginActivity.this, SignUpActivity.class);
                startActivity(goToSignupActivity);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLoginActivity = new Intent (SignUpOrLoginActivity.this, LoginActivity.class);
                startActivity(goToLoginActivity);
            }
        });
    }
}
