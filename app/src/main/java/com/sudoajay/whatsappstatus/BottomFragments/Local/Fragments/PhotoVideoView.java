package com.sudoajay.whatsappstatus.BottomFragments.Local.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.sudoajay.whatsappstatus.R;

public class PhotoVideoView extends AppCompatActivity {
    private ImageView imageView, back_Arrow_ImageView;
    private ConstraintLayout header_ConstraintLayout,bottom_ConstraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_video_view);

        Reference();

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


            }
        });


    }

    private void Reference(){
        imageView = findViewById(R.id.imageView);
        back_Arrow_ImageView = findViewById(R.id.back_Arrow_ImageView);
        header_ConstraintLayout = findViewById(R.id.header_ConstraintLayout);
        bottom_ConstraintLayout = findViewById(R.id.bottom_ConstraintLayout);
    }


    //    Hide Status
    @SuppressLint("ResourceType")
    private void HideStatusNavigation() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        header_ConstraintLayout.setVisibility(View.INVISIBLE);
        bottom_ConstraintLayout.setVisibility(View.INVISIBLE);

    }

    //    Show Status
    private void ShowStatusNavigation() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);

        header_ConstraintLayout.setVisibility(View.VISIBLE);
        bottom_ConstraintLayout.setVisibility(View.VISIBLE);

    }


}
