package com.sudoajay.whatsappstatus.BottomFragments.Local.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.sudoajay.whatsappstatus.BuildConfig;
import com.sudoajay.whatsappstatus.CustomToast;
import com.sudoajay.whatsappstatus.R;

import java.io.File;
import java.util.Objects;

public class PhotoVideoView extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView, back_Arrow_ImageView, video_Play_ImageView;
    private ConstraintLayout header_ConstraintLayout, bottom_ConstraintLayout, constraintLayout;
    private VideoView videoView;
    private String tabName, filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_video_view);

        Intent intent = getIntent();
        if(intent != null){
         tabName = intent.getStringExtra("WhichTab");
         filePath = intent.getStringExtra("FilePath");
        }

        Reference();

        // At First Hide It
        HideStatusNavigation();

        if (tabName.equals("photo")) {
            ShowImageView(new File(filePath));
        } else {
            ShowVideoView(new File(filePath));
        }

    }

    private void Reference(){
        videoView = findViewById(R.id.videoView);
        imageView = findViewById(R.id.imageView);
        video_Play_ImageView = findViewById(R.id.video_Play_ImageView);
        back_Arrow_ImageView = findViewById(R.id.back_Arrow_ImageView);
        header_ConstraintLayout = findViewById(R.id.header_ConstraintLayout);
        bottom_ConstraintLayout = findViewById(R.id.bottom_ConstraintLayout);
        constraintLayout = findViewById(R.id.constraintLayout);


//        imageView.setOnClickListener(this);
        constraintLayout.setOnClickListener(this);
        video_Play_ImageView.setOnClickListener(this);
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

    @SuppressLint("ClickableViewAccessibility")
    private void ShowVideoView(final File file) {

        // fixed something
        imageView.setVisibility(View.GONE);
        if(file.exists())
            Toast.makeText(getApplicationContext(),file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
        videoView.setVideoPath(file.getAbsolutePath());


        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                CustomToast.ToastIt(getApplicationContext(),"Sorry, this video cannot be played");
                Bitmap thumb = ThumbnailUtils.createVideoThumbnail(file.getPath(),
                        MediaStore.Images.Thumbnails.MINI_KIND);
                Drawable drawble = new BitmapDrawable(getResources(), thumb);


                videoView.setBackground(drawble);
                return true;
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });
        videoView.start();
    }

    private void ShowImageView(final File file) {
        // fixed something
        videoView.setVisibility(View.GONE);
        video_Play_ImageView.setVisibility(View.GONE);

        Glide.with(getBaseContext())
                .asBitmap()
                .load(Uri.fromFile(file))
                .into(imageView);

    }

    public void open_With(File file) {
        try {
            MimeTypeMap myMime = MimeTypeMap.getSingleton();
            Intent newIntent = new Intent(Intent.ACTION_VIEW);
            String mimeType = myMime.getMimeTypeFromExtension(Objects.requireNonNull(fileExt(file.getAbsolutePath())).substring(1));
            Uri URI = FileProvider.getUriForFile(this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file);
            newIntent.setDataAndType(URI, mimeType);
            newIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            this.startActivity(newIntent);
        } catch (Exception e) {
            CustomToast.ToastIt(getApplicationContext(), "No handler for this type of file.");
        }
    }

    private String fileExt(String url) {
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.lastIndexOf(".") == -1) {
            return null;
        } else {
            String ext = url.substring(url.lastIndexOf(".") + 1);
            if (ext.contains("%")) {
                ext = ext.substring(0, ext.indexOf("%"));
            }
            if (ext.contains("/")) {
                ext = ext.substring(0, ext.indexOf("/"));
            }
            return ext.toLowerCase();

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.video_Play_ImageView:
                open_With(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Ajay.mp4"));
                break;
            case R.id.constraintLayout:
                if (getWindow().getDecorView().getSystemUiVisibility() == View.SYSTEM_UI_FLAG_VISIBLE) {
                    HideStatusNavigation();
                } else {
                    ShowStatusNavigation();
                }
                break;
        }

    }
}
