package com.apppro.pikturesque.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.apppro.pikturesque.R;
import com.apppro.pikturesque.Utils.BottomNavViewHelper;
import com.apppro.pikturesque.Utils.SectionsPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class NotificationsActivity extends AppCompatActivity {
    private static final String TAG = "NotificationsActivity";
    Context context= NotificationsActivity.this;
    public static final int ACTIVITY_NUM=3;
    public static boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //a method that isn't in the onCreate method must be called in the onCreate method or it won't run.
        setBottomNavigationView();
        setViewPager();
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

    /*This sets up the viewpager for the Fragments connected to this activity.
    * The process of adding a viewpager in general goes like this.*/
    private void setViewPager () {
        //find the viewpager in the layout file
        ViewPager viewPager=findViewById(R.id.viewPager);

        //declare a SectionsPagerAdapter like this (the getSupportFragmentManager is important)
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //add the Fragments and then call the ViewPager to action.
        adapter.addingFragment(new NotificationsFragment());
        adapter.addingFragment(new FollowsFragment());
        viewPager.setAdapter(adapter);

        //these lines allow the icons for the tabs to be displayed.
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_notification_bell_empty);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_follows_nonew);
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
