package com.apppro.pikturesque.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.apppro.pikturesque.R;
import com.apppro.pikturesque.Utils.BottomNavViewHelper;
import com.apppro.pikturesque.Utils.SectionsStatePagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class UserSettingsActivity extends AppCompatActivity {
    private static final String TAG = "UserSettingsActivity";
    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private RelativeLayout relativeLayout;
    public static final int ACTIVITY_NUM=4;
    Context context = UserSettingsActivity.this;

ImageView ivBackArrow;
Button btnEditProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        viewPager = findViewById(R.id.viewPager);
        relativeLayout=findViewById(R.id.relativeLayoutTop);

        setSettingsList();
        setFragments();
        setBottomNavigationView();

        //back arrow navigation
        ivBackArrow=findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Back button was clicked.");
                finish();
            }
        });

    }

    //You need this in order to set up the options menu; follow those steps.
    private void setSettingsList(){
        Log.d(TAG, "setSettingsList: Settings List forming");

        //find the list view in the layout
        ListView lvAccountSettingsOptions=findViewById(R.id.lvAccountSettingsOptions);

        //then do an array list
        ArrayList<String> options = new ArrayList<>();

        //add the list items to the array list
        options.add("Edit profile");
        options.add("Sign Out");

        /*then create an array adapter. pass the context and the android layout (the word "android" must be typed)
        and the array list*/
        ArrayAdapter adapter = new ArrayAdapter(UserSettingsActivity.this, android.R.layout.simple_expandable_list_item_1, options);

        //then connect them with [name of list view].setAdapter([name of adapter)]
        lvAccountSettingsOptions.setAdapter(adapter);

        lvAccountSettingsOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                setViewPager(position);
            }
        });
    }

    //A ViewPager and a Pager Adapter are both needed in order to use fragments.
    //The pager adapter will add the fragments I am going to be using.
    private void setFragments(){
    pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
    pagerAdapter.addFragment(new EditProfileFragment(), "Edit Profile");
    pagerAdapter.addFragment(new SignOutFragment(), "Sign out");
    }
    private void setViewPager (int fragmentNumber){
        relativeLayout.setVisibility(View.GONE);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(fragmentNumber);
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
}
