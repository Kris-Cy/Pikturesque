package com.apppro.pikturesque.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apppro.pikturesque.R;
import com.apppro.pikturesque.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class EditProfileFragment extends Fragment {
    private ImageView profilePicture;
    private static final String TAG = "EditProfileFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        //have to create an imageview on which i can apply the Universal Image Loader.
        profilePicture= view.findViewById(R.id.UserProfilePicture);

        //methods outside the onCreate method must be called here or they won't run.
        setProfileImage();

        //back arrow navigation
        ImageView ivBackArrow = view.findViewById(R.id.ivBackArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        return view;
    }


    //an image to be set to the profile picture space, that isn't the default one that loads automatically when there is no image to be set.
    private void setProfileImage (){
        String imgUrl = "http://storage.googleapis.com/xebia-blog/1/2017/01/Android-Logo-PNG.png";

        /*Inside the brackets, the parameters are : the String for the image URL, the append for it
        * (which in this case is blank, since "http://" is acceptable as part of the string in the Universal
        * Image Loader library, then the widget to which this image will be applied, then the progress bar, which is null right now.*/
        UniversalImageLoader.setImage(imgUrl, "", profilePicture, null);
    }
}
