package com.apppro.pikturesque.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.apppro.pikturesque.Getting_Started.LoginActivity;
import com.apppro.pikturesque.Getting_Started.SignUpActivity;
import com.apppro.pikturesque.R;
import com.apppro.pikturesque.Utils.Firebase;
import com.apppro.pikturesque.Utils.SectionsPagerAdapter;
import com.apppro.pikturesque.Utils.BottomNavViewHelper;
import com.apppro.pikturesque.Utils.UniversalImageLoader;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nostra13.universalimageloader.core.ImageLoader;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0;
    FirebaseAuth mAuth;
    FirebaseUser User;
    Context context = HomeActivity.this;
    public static boolean active = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        User=mAuth.getCurrentUser();

        if(User!=null){
            Log.d(TAG, "onCreate: A user is signed in");
        }
        else{
            Log.d(TAG, "onCreate: A user is not signed in. Going back to login screen.");
            Intent kickToLogin = new Intent(context, LoginActivity.class);
            context.startActivity(kickToLogin);
        }


        Log.d(TAG, "HomeActivity: start");


        //a method that isn't in the onCreate method must be called in the onCreate method or it won't run.
        //initImageLoader must be called BEFORE anything that might use it.
        initImageLoader();
        setBottomNavigationView();
        setViewPager();
    }

    //Universal Image Loader has to be initialized.
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(context);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());

    }
    /*This sets up the viewpager for the Fragments connected to this activity.
     * The process of adding a viewpager in general goes like this.*/
    private void setViewPager () {

        //find the viewpager in the layout file
        ViewPager viewPager=findViewById(R.id.viewPager);

        //declare a SectionsPagerAdapter like this (the getSupportFragmentManager is important)
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //add the Fragments and then call the ViewPager to action.
        adapter.addingFragment(new HomeFragment());
        adapter.addingFragment(new DMFragment());
        viewPager.setAdapter(adapter);

        //these lines allow the icons for the tabs to be displayed.
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_timeline);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_message);
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
