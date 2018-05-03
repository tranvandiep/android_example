package com.aptech.example;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by Diep.Tran on 5/3/18.
 */

//https://www.tutorialspoint.com/android/android_audio_capture.htm
    //https://www.javatpoint.com/playing-video-in-android-example
    //https://www.tutorialspoint.com/android/android_camera.htm
public class CustomView extends LinearLayout {
    public CustomView(Context context) {
        super(context);
        initGui();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initGui();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGui();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initGui();
    }

    private void initGui() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        inflater.inflate(R.layout.item_list_view_2, this, true);
    }
}
