package com.apppro.pikturesque.Getting_Started;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.apppro.pikturesque.Home.HomeActivity;
import com.apppro.pikturesque.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LauncherActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "LauncherActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_launcher);




        /*this is for Firebase Authorization. Initialized above, is private.
        * Firebase has an assistant on Android Studio to help you with these parts.*/
         mAuth = FirebaseAuth.getInstance();

    }
            @Override
            public void onStart() {
                super.onStart();
                // Check if a user is signed in (non-null) and, if one is, head to the main activities of the app.
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser!=null)
                {
                    Log.d(TAG, "onStart: This user is signed in: "+ currentUser.getUid());

                    Intent goToHome =new Intent(LauncherActivity.this, HomeActivity.class);
                    startActivity(goToHome);
                    finish();
                }

                //if one isn't, go to the activity that asks you to sign up or log in.
                else
                {
                    Intent goToSignUpOrLogin = new Intent(LauncherActivity.this, SignUpOrLoginActivity.class);
                    startActivity (goToSignUpOrLogin);
                    finish();
                }

            }
        }



