package com.apppro.pikturesque.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.apppro.pikturesque.Camera.CameraActivity;
import com.apppro.pikturesque.Home.HomeActivity;
import com.apppro.pikturesque.Notifications.NotificationsActivity;
import com.apppro.pikturesque.R;
import com.apppro.pikturesque.Search.SearchActivity;
import com.apppro.pikturesque.User.UserActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

public class BottomNavViewHelper {

    /*This method is what will give the bottom navigation view its functionality
     touching an icon on it will open an activity for the page that icon represents.*/
public static void useBottomNavigation(final Context context, BottomNavigationView view){

    view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
switch (menuItem.getItemId()) {
    case R.id.home:
            Intent goToHome = new Intent(context, HomeActivity.class);
            context.startActivity(goToHome);
        break;
    case R.id.search:
            Intent goToSearch = new Intent(context, SearchActivity.class);
            context.startActivity(goToSearch);
        break;
    case R.id.camera:
            Intent goToCamera = new Intent(context, CameraActivity.class);
            context.startActivity(goToCamera);
        break;
    case R.id.note:
            Intent goToNote = new Intent(context, NotificationsActivity.class);
            context.startActivity(goToNote);
        break;
    case R.id.user:
            Intent goToUser = new Intent(context, UserActivity.class);
            context.startActivity(goToUser);
        break;
}
            return false;
        }

    });

}
}
