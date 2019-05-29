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
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.sudoajay.whatsappstatus.BuildConfig;

import com.sudoajay.whatsappstatus.R;

import java.io.File;
import java.util.Objects;

public class PhotoVideoView extends AppCompatActivity {
    private ImageView imageView, video_Play_ImageView;
    private ConstraintLayout header_ConstraintLayout, bottom_ConstraintLayout;
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
        header_ConstraintLayout = findViewById(R.id.header_ConstraintLayout);
        bottom_ConstraintLayout = findViewById(R.id.bottom_ConstraintLayout);
        TextView file_Name_TextView = findViewById(R.id.file_Name_TextView);


        file_Name_TextView.setText(new File(filePath).getName());
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.video_Play_ImageView:
                open_With(new File(filePath));
                videoView.setVideoPath(filePath);
                videoView.stopPlayback();
                videoView.start();
                break;
            case R.id.constraintLayout:
                if (getWindow().getDecorView().getSystemUiVisibility() == View.SYSTEM_UI_FLAG_VISIBLE) {
                    HideStatusNavigation();
                } else {
                    ShowStatusNavigation();
                }
                break;
            case R.id.share_TextView:
            case R.id.share_ImageView:
                ShareIt();
                break;
            case R.id.save_ImageView:
            case R.id.save_TextView:
                new SaveFile(PhotoVideoView.this, filePath, tabName);
                break;
            case R.id.back_Arrow_ImageView:
                onBackPressed();
                break;
        }

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
        try {
            // fixed something
            imageView.setVisibility(View.GONE);
            if (file.exists())
                videoView.setVideoPath(file.getAbsolutePath());


            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    ToastIt( "Sorry, this video cannot be played");
                    ShowVideoThumb();
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
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    ShowVideoThumb();
                }
            });
        } catch (Exception ignored) {

        }
    }

    private void ShowImageView(final File file) {
        try {
            // fixed something
            videoView.setVisibility(View.GONE);
            video_Play_ImageView.setVisibility(View.GONE);

            Glide.with(getBaseContext())
                    .asBitmap()
                    .load(Uri.fromFile(file))
                    .into(imageView);
        } catch (Exception ignored) {

        }
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
            ToastIt( "No handler for this type of file.");
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

    private void ShowVideoThumb(){
        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(filePath,
                MediaStore.Images.Thumbnails.MINI_KIND);
        Drawable drawble = new BitmapDrawable(getResources(), thumb);
        videoView.setBackground(drawble);
    }


    private void ShareIt() {

        if (tabName.equals("photo")) {
            Intent shareIntent;
            Uri bmpUri = Uri.parse("file://" + filePath);
            shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey Check This Image - ");
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, "Share with"));
        } else {
            Intent shareIntent;
            Uri bmpUri = Uri.parse("file://" + filePath);
            shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey Check This Video - ");
            shareIntent.setType("video/*");
            startActivity(Intent.createChooser(shareIntent, "Share with"));
        }
    }
    private void ToastIt(final String mess){
        Toast.makeText(getApplicationContext(),mess,Toast.LENGTH_SHORT).show();
    }

}
