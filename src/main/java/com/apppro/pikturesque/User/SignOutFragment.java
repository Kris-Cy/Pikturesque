package com.apppro.pikturesque.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apppro.pikturesque.Getting_Started.LoginActivity;
import com.apppro.pikturesque.Getting_Started.SignUpOrLoginActivity;
import com.apppro.pikturesque.R;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignOutFragment extends Fragment {
    private static final String TAG = "SignOutFragment";
private FirebaseAuth mAuth;

Button btnSignOut;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_out, container, false);
        mAuth=FirebaseAuth.getInstance();
        btnSignOut = view.findViewById(R.id.btnSignOut);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Signing out...");
                mAuth.signOut();
                Intent kickToLogin = new Intent (getActivity(), LoginActivity.class);
                kickToLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(kickToLogin);
                getActivity().finish();
            }
        });


        return view;
    }
}
