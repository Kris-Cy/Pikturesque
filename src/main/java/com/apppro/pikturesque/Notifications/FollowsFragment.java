package com.apppro.pikturesque.Notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apppro.pikturesque.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*This is a Fragment class. It lets you operate within an activity without having to open an entirely new one.
* It's also how tabs work. It won't run properly unless you call the fragment class like down below and
* initialize the onCreateView of the Fragment like down below.*/

public class FollowsFragment extends Fragment {
    private static final String TAG = "FollowsFragment";
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follows, container, false);

        return view;
    }
}
