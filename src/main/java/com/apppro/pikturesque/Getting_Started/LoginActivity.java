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

import com.apppro.pikturesque.Home.HomeActivity;
import com.apppro.pikturesque.R;
import com.apppro.pikturesque.Utils.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;
    private Context context;
    private Firebase Firebase;
    private String email, password;

    EditText etEmailLogin, etPasswordLogin;
    Button btnLoginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=LoginActivity.this;

        /*this is for Firebase Authorization. Initialized above, is private.
         * Firebase has an assistant on Android Studio to help you with these parts.*/
        mAuth = FirebaseAuth.getInstance();
        Firebase = new Firebase(context);

        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btnLoginUser = findViewById(R.id.btnLoginUser);

        loginButton();
    }


    private void loginButton() {
        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmailLogin.getText().toString().trim();
                password = etPasswordLogin.getText().toString().trim();

                //This first if clause makes sure the email and password fields both have stuff in them.
                if (!email.equals("") && !password.equals("")) {
                    Firebase.loginWithEmail(email, password);
                    FirebaseUser user = mAuth.getCurrentUser();
                    //We only want the user to be able to log in after having verified their email.
                    try {
                        if (user.isEmailVerified()){
                            Log.d(TAG, "onClick: Email is verified. going to home activity.");
                            Intent goToHome = new Intent(context, HomeActivity.class);
                            context.startActivity(goToHome);
                        }
                        else {
                            Log.d(TAG, "onClick: email is not verified. Tell the user.");
                            Toast.makeText(context, "Verify your email first!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    /*this will catch the NullPointerException of the user having entered a blank space.
                    * It really is only necessary for the fact that I have a try statement up there.
                    * In the event that there is a blank space, a NullPointerException won't be thrown
                    * as that logic is handled in the if... else statement that this is enclosed in. */
                    catch (NullPointerException e){
                        Log.d(TAG, "onClick: NullPointerException "+e.getMessage());
                        Toast.makeText(context, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                    }
                }

                /*you have to specify for something to happen in the case that either of the fields is blank
                 *or the app will crash if someone tries to press the login with empty fields.*/
                else {
                    Toast.makeText(LoginActivity.this, "email or password is blank", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

