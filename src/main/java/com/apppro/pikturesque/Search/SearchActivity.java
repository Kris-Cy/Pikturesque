package com.apppro.pikturesque.Search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.apppro.pikturesque.R;
import com.apppro.pikturesque.Utils.BottomNavViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    Context context = SearchActivity.this;
    public static final int ACTIVITY_NUM=1;
    public static boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //a method that isn't in the onCreate method must be called in the onCreate method or it won't run.
        setBottomNavigationView();
    }

    //Bottom navigation view must be set on all pages that it has a function in.
    private void setBottomNavigationView(){
        Log.d(TAG, "setBottomNavigationView: Start");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        BottomNavViewHelper.useBottomNavigation(context, bottomNavigationView);

        //the bottom navigation view won't highlight the right icon without these lines.
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem (ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        active=false;
    }
}
