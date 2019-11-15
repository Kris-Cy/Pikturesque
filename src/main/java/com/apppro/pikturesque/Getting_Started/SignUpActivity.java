package com.apppro.pikturesque.Getting_Started;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apppro.pikturesque.Data_Models.User;
import com.apppro.pikturesque.Data_Models.UserAccount;
import com.apppro.pikturesque.Home.HomeActivity;
import com.apppro.pikturesque.R;
import com.apppro.pikturesque.Utils.Firebase;
import com.apppro.pikturesque.Utils.StringManipulator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUp";

    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;
    private Firebase Firebase;
    private String append = "";
    private Context context;
    String email, username, password, confirmPassword;
    EditText etEmail, etPassword, etConfirmPassword, etUsername;
    Button btnCreateAccount;
    FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Signing up! Start!");
        setContentView(R.layout.activity_sign_up);

        context = SignUpActivity.this;

        /*this is for Firebase Authentication. Initialized above, is private.
         * Firebase has an assistant on Android Studio to help you with these parts.*/
        mAuth = FirebaseAuth.getInstance();

        Firebase = new Firebase(context);

        if (mAuth.getCurrentUser()!=null){
            userID = mAuth.getCurrentUser().getUid();
        }

        //these add an instance of and a reference to the Firebase database associated with this project.
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);


        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();


                registerButton();
                onStart();
                onStop();

            }


    private void registerButton() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmail.getText().toString().trim();
                username = etUsername.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                confirmPassword = etConfirmPassword.getText().toString().trim();
                if (areFieldsNull()) {
                    Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.equals(confirmPassword)) {
                        Firebase.registerWithEmail(email, username, password, confirmPassword);

                        //automatically signs the user out and kicks them back to the previous screen.
                        mAuth.signOut();
                        finish();
                    } else {
                        Toast.makeText(context, "Passwords must match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean areFieldsNull() {

        email = etEmail.getText().toString().trim();
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmPassword = etConfirmPassword.getText().toString().trim();

        if (email.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("")) {
            return true;
        }
        return false;
    }

    //commented out but kept for my memory purposes. I might need snippets from this code.

    /*public void appendUsername(){
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d(TAG, "userAuthListener: Is user signed in?");
                FirebaseUser currentUser = mAuth.getCurrentUser();
                Firebase.loginWithEmail(etEmail.getText().toString().trim(), etPassword.getText().toString().trim());
                if (currentUser!=null){
                    //user is signed in.
                    Log.d(TAG, "userAuthListener: This user is signed in: " +currentUser+" !");
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //check to see if username is already taken
                            if (Firebase.isUsernameTaken(username,dataSnapshot)){
                                append=myRef.getKey().substring(2,9);
                                Log.d(TAG, "onDataChange: Username is taken; appending a random string: "+append);

                                Toast.makeText(context, "That username is taken! We'll change it a bit...", Toast.LENGTH_SHORT).show();
                            }
                            username=username+append;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "Failed to read values.", databaseError.toException());
                        }
                    });
                }
                else{
                    //user is not signed in.
                    Log.d(TAG, "userAuthListener: This user is not signed in.");
                }
            }
        };

    }*/
}