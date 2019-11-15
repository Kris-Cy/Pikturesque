package com.apppro.pikturesque.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.apppro.pikturesque.R;
import com.apppro.pikturesque.Utils.BottomNavViewHelper;
import com.apppro.pikturesque.Utils.GridImageAdapter;
import com.apppro.pikturesque.Utils.UniversalImageLoader;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = "UserActivity";
    Context context = UserActivity.this;
    public static final int ACTIVITY_NUM=4;
    public static boolean active = false;
    private ImageView profilePicture;
    ImageView ivSettingsIcon;
    Button btnEditProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        profilePicture=findViewById(R.id.profilePicture);
        ivSettingsIcon=findViewById(R.id.ivSettingsIcon);
        btnEditProfile=findViewById(R.id.btnEditProfile);

        //a method that isn't in the onCreate method must be called in the onCreate method or it won't run.
        setBottomNavigationView();
        setSettingsIcon();
        setProfileImage();
        temporaryGrid();
        widgets();

    }

    //an image to be set to the profile picture space, that isn't the default one that loads automatically when there is no image to be set.
    private void setProfileImage(){
        String imgUrl = "http://storage.googleapis.com/xebia-blog/1/2017/01/Android-Logo-PNG.png";

        /*Inside the brackets, the parameters are : the String for the image URL, the append for it
         * (which in this case is blank, since "http://" is acceptable as part of the string in the Universal
         * Image Loader library, then the widget to which this image will be applied, then the progress bar, which is null right now.*/
        UniversalImageLoader.setImage(imgUrl, "", profilePicture, null);
    }

    //just a temporary grid of images. I put in the URLs myself just to see if it would work and how it would look.
    private void temporaryGrid(){
        ArrayList<String>imgURLs = new ArrayList<>();
        imgURLs.add("http://clipart-library.com/images/8TEb9p4ec.jpg");
        imgURLs.add("http://clipart-library.com/images/pT5ra4Xgc.jpg");
        imgURLs.add("http://clipart-library.com/clipart/LTd5aE6Xc.htm");
        imgURLs.add("http://clipart-library.com/clipart/LTd5aE6Xc.htm");
        imgURLs.add("http://clipart-library.com/images/8czraEpKi.jpg");
        imgURLs.add("http://clipart-library.com/images/8iEb9L6rT.jpg");
        imgURLs.add("http://clipart-library.com/images/Acbra69di.jpg");
        imgURLs.add("http://clipart-library.com/images/Bcgrann9i.jpg");
        imgURLs.add("http://clipart-library.com/images/dc9raLboi.jpg");
        imgURLs.add("http://clipart-library.com/images/8T65adXkc.jpg");
        imgURLs.add("http://clipart-library.com/images/di45adEjT.jpg");
        imgURLs.add("http://clipart-library.com/images/8T65apKyc.jpg");
        imgURLs.add("http://clipart-library.com/images/8T65adEGc.jpg");

        imgGridView(imgURLs);

    }

    //this is the grid view where the images will go.
    private void imgGridView(ArrayList<String> imgURLs){
        GridView gridView = findViewById(R.id.UserPageGridView);
        GridImageAdapter adapter = new GridImageAdapter(context, R.layout.layout_grid_imageview, "",imgURLs);
        gridView.setAdapter(adapter);

        /*the images will try to load at whatever size they were taken in. Let's fix that.
        * the images in the grid will span the entire width of the screen in total, and there are 3 of them
        * so, each image will be one third the width of the screen.*/
        int gridwidth = getResources().getDisplayMetrics().widthPixels;
        int picturewidth = gridwidth/3;
        gridView.setColumnWidth(picturewidth);
    }

    //this is where the widgets in the activity will go.
    private void widgets (){
        profilePicture = findViewById(R.id.profilePicture);
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



    private void setSettingsIcon(){
        ivSettingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Settings icon clicked! going to the settings!");
                Intent goToUserSettings = new Intent(UserActivity.this, UserSettingsActivity.class);
                startActivity (goToUserSettings);
            }
        });

    }
}
