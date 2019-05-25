package com.sudoajay.whatsappstatus.BottomFragments.Local.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.sudoajay.whatsappstatus.R;

public class PhotoVideoView extends Activity {
    private PopupWindow mPopupWindow;
    ImageView imageView;

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_video_view);
        imageView = (ImageView) findViewById(R.id.imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HideStatusBar();

//                // Initialize a new instance of LayoutInflater service
//                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                // Inflate the custom layout/view
//                View customView = inflater.inflate(R.layout.activity_popup_photo_video_view, null);
//
//                /*
//                    public PopupWindow (View contentView, int width, int height)
//                        Create a new non focusable popup window which can display the contentView.
//                        The dimension of the window must be passed to this constructor.
//
//                        The popup does not provide any background. This should be handled by
//                        the content view.
//
//                    Parameters
//                        contentView : the popup's content
//                        width : the popup's width
//                        height : the popup's height
//                */
//                // Initialize a new instance of popup window
//                mPopupWindow = new PopupWindow(
//                        customView,
//                        ViewPager.LayoutParams.WRAP_CONTENT,
//                        ViewPager.LayoutParams.WRAP_CONTENT
//                );
//
//                // Set an elevation value for popup window
//                // Call requires API level 21
//                if (Build.VERSION.SDK_INT >= 21) {
//                    mPopupWindow.setElevation(100.0f);
//                }
//                mPopupWindow.setAnimationStyle(R.style.Animation);
//
//                // Get a reference for the custom view close button
////                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
////
////                // Set a click listener for the popup window close button
////                closeButton.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        // Dismiss the popup window
////                        mPopupWindow.dismiss();
////                    }
////                });
//
//                /*
//                    public void showAtLocation (View parent, int gravity, int x, int y)
//                        Display the content view in a popup window at the specified location. If the
//                        popup window cannot fit on screen, it will be clipped.
//                        Learn WindowManager.LayoutParams for more information on how gravity and the x
//                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
//                        to specifying Gravity.LEFT | Gravity.TOP.
//
//                    Parameters
//                        parent : a parent view to get the getWindowToken() token from
//                        gravity : the gravity which controls the placement of the popup window
//                        x : the popup's x location offset
//                        y : the popup's y location offset
//                */
//                // Finally, show the popup window at the center location of root relative layout
//                mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });


    }

    public void HideStatusBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);



//        // Hide status bar
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
// Show status bar
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


}
