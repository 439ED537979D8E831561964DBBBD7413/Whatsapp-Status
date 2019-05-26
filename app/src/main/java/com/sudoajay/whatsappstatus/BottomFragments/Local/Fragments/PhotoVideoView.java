package com.sudoajay.whatsappstatus.BottomFragments.Local.Fragments;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.sudoajay.whatsappstatus.R;

public class PhotoVideoView extends AppCompatActivity {
    private PopupWindow mPopupWindow;
    ImageView imageView;

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_video_view);
        imageView =  findViewById(R.id.imageView);

        // At First Hide It
        HideStatusNavigation();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getWindow().getDecorView().getSystemUiVisibility() == View.SYSTEM_UI_FLAG_VISIBLE) {
                    HideStatusNavigation();
                } else {
                    ShowStatusNavigation();
                }

                // Set an elevation value for popup window
                // Call requires API level 21
                if (Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(100.0f);
                }
                mPopupWindow.setAnimationStyle(R.style.Animation);
            }
        });


    }

    //    Hide Status
    @SuppressLint("ResourceType")
    private void HideStatusNavigation() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


    }

    //    Show Status
    private void ShowStatusNavigation() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);

          }


}
