package com.apppro.pikturesque.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.apppro.pikturesque.Data_Models.User;
import com.apppro.pikturesque.Data_Models.UserAccount;
import com.apppro.pikturesque.Getting_Started.LoginActivity;
import com.apppro.pikturesque.Getting_Started.SignUpActivity;
import com.apppro.pikturesque.Home.HomeActivity;
import com.apppro.pikturesque.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;


/* This is my class for all the firebase methods I'm gonna be using. This makes a bit more sense than trying
* to write up the methods in every activity they might appear in, because there will probably be many.
* I believe the only Firebase related method that isn't in this class is in the launcher activity, but that's
* fine where it is.*/

public class Firebase {
    private static final String TAG = "Firebase";
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String userID;
    private String append = "";
    SignUpActivity signUpActivity = new SignUpActivity();

    //Firebase constructor.
    public Firebase (Context context){
        mContext = context;
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef=database.getReference();


        if (mAuth.getCurrentUser()!=null){
            userID = mAuth.getCurrentUser().getUid();
        }
    }
/*This code is mostly lifted from the Firebase assistant in "Tools". This will sign up the user in Firebase authentication*/

    public void registerWithEmail(final String email, final String username, String password, String confirmPassword){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //sign up successful!

                                    Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(mContext, "Sign up successful!", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                   userID = mAuth.getCurrentUser().getUid();

                                   //this part will add them to the database.
                                    Log.d(TAG, "onComplete: adding user to database...");
                                    addUser(email, username, "Hello!", "");

                                    Log.d(TAG, "onComplete: user has been added to database. Sending verification email");

                                    //send the verification email.
                                    sendVerificationEmail();

                                } else {
                                    //sign up failed, for some reason... Tell the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(mContext, "Sign up failed.", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }

    public void loginWithEmail(String email, String password) {

            /*I got this from the Firebase assistant. mAuth is a firebase
             * authentication library (it's a FirebaseAuth that I named mAuth) and it has
             * the .signInWithEmailAndPassword task included in it.*/
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, go to the main activities of the app.
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                userID = mAuth.getCurrentUser().getUid();


                            } else {
                                // Sign in failed. tell the user. Maybe tell them username or password was incorrect.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(mContext, "Login failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        //This method will send the verification email.
        public void sendVerificationEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Log.d(TAG, "sendVerificationEmail: sending...");
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Log.d(TAG, "onComplete: email has been sent!");
                                Toast.makeText(mContext, "Sent verification email", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Log.d(TAG, "onComplete: email has not been sent. :(");
                                Toast.makeText(mContext, "Couldn't send verification email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        }

    public boolean isUsernameTaken(String username, DataSnapshot dataSnapshot) {
        Log.d(TAG, "isUsernameTaken: checking right now...");
        User user = new User();
        for (DataSnapshot ds : dataSnapshot.child(userID).getChildren()) {
            Log.d(TAG, "isUsernameTaken: checking DataSnapshot " + ds);
            user.setUsername(ds.getValue(User.class).getUsername());

            if (StringManipulator.addSpacesToUsername(user.getUsername()).equals(username)) {
                Log.d(TAG, "isUsernameTaken: found a match!");
                return true;
            }
        }
        return false;
    }


    public void addUser (String email, String username, String bio, String profile_photo){
        User user = new User (email, userID, StringManipulator.removeSpacesFromUsername(username));
        FirebaseDatabase.getInstance().getReference();

        Log.d(TAG, "addUser: adding the user to the 'users' table of the database...");
        myRef.child(mContext.getString(R.string.dbname_users))
                .child(userID)
                .setValue(user);
        Log.d(TAG, "addUser: successfully added (user)!");
        UserAccount account = new UserAccount("", "", 0, 0, profile_photo,StringManipulator.removeSpacesFromUsername(username));

        Log.d(TAG, "addUser: adding user to the 'user_account' table of the database...");
        myRef.child(mContext.getString(R.string.dbname_useraccounts))
                .child(userID)
                .setValue(account);
        Log.d(TAG, "addUser: successfully added (user_account)!");
    }

}


