package com.apppro.pikturesque.Utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

//This is my square image view class. It lets me make my images into squares.
public class SquareImageView extends AppCompatImageView {

    //Constructors for this class
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
/*This override is what's gonna give my images shape. Usually, one would pass in a width measurement
* spec and a height measurement spec, but in this case, I want a square, so it will be two of the same specs
* to be cast on the images. Since we already know what the width of the images will be (1/3 of the width of the screen),
* we just let the height to be cast onto the images be that too.*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
