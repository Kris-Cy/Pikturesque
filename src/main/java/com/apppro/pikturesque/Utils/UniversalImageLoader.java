package com.apppro.pikturesque.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.apppro.pikturesque.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


//This class is the Universal Image Loader. It's a library for loading, caching and displaying images on Android.
public class UniversalImageLoader {

    //First thing to do with it is set a default image.
    private static final int defaultImage = R.drawable.ic_smiley;
    private Context mContext;

    //mandatory constructor for this class.
    public UniversalImageLoader(Context context){
        mContext = context;
    }

    /*These are the default configurations I will use for images.
    * A list of configurations can be found on the Universal Image Loader GitHub.*/
   public ImageLoaderConfiguration getConfig(){
       DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
               .showImageOnLoading(defaultImage)
               .showImageForEmptyUri(defaultImage)
               .cacheOnDisk(true).cacheInMemory(true)
               .cacheOnDisk(true).resetViewBeforeLoading(true)
               .imageScaleType(ImageScaleType.EXACTLY)
               .displayer(new FadeInBitmapDisplayer(300))
               .build();
ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mContext)
        .defaultDisplayImageOptions(defaultOptions)
        .memoryCacheSize(100*1024*1024)
        .build();

return configuration;
    }

    /*This method is good for setting a static image. Not a grid of images, or a list of images, or
    * images that will be changing in the Fragment/Activity, a static image, like a profile picture.
    * The image Uri will be the address of the image itself. append will be the extension of the image
    * saying where it comes from. For example, an image from the web named "my_dog.jpeg" will show up
    * as "http://my_dog.jpeg". "my_dog.jpeg" will be the uri and "http://" will be the append.*/
    public static void setImage (String imgURL, String append, ImageView image, final ProgressBar mProgressBar){
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(append + imgURL, image, new ImageLoadingListener() {

         /*The image will display, but while loading there will be an ImageLoadingListener.
         * When the image starts to load, the progress bar will show.
         * When it stops loading, for whatever reason, the progress bar will stop showing.*/
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(mProgressBar!=null)
                    mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(mProgressBar!=null)
                    mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(mProgressBar!=null)
                    mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if(mProgressBar!=null)
                    mProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
