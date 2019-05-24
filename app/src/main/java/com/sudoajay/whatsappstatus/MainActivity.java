package com.sudoajay.whatsappstatus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sudoajay.whatsappstatus.BottomFragments.DownloadFragment;
import com.sudoajay.whatsappstatus.BottomFragments.Local.LocalFragment;
import com.sudoajay.whatsappstatus.BottomFragments.OnlineFragment;
import com.sudoajay.whatsappstatus.Permission.AndroidExternalStoragePermission;

public class MainActivity extends AppCompatActivity {

    // Global Variable
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Reference();


        // set up External storage Permission
        AndroidExternalStoragePermission androidExternalStoragePermission = new AndroidExternalStoragePermission(
                MainActivity.this,MainActivity.this);
        androidExternalStoragePermission.call_Thread();

        // menu
        final Menu menu = bottomNavigationView.getMenu();

        bottomNavigationView.setSelectedItemId(R.id.localTab);
        LocalFragment localFragment = new LocalFragment();
        fragment = localFragment.createInstance(MainActivity.this);
        Replace_Fragments();
        menu.findItem(R.id.localTab).setIcon(R.drawable.folder_open_icon);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menu.findItem(R.id.localTab).setIcon(R.drawable.folder_close_icon);
                menu.findItem(R.id.onlineTab).setIcon(R.drawable.folder_network_close);
                menu.findItem(R.id.DownloadsTab).setIcon(R.drawable.arrow_down_close_icon);
                switch (menuItem.getItemId()) {
                    case R.id.localTab:
                        LocalFragment localFragment = new LocalFragment();
                        fragment = localFragment.createInstance(MainActivity.this);
                        Replace_Fragments();
                        menuItem.setIcon(R.drawable.folder_open_icon);
                        return true;

                    case R.id.onlineTab:
                        OnlineFragment onlineFragment = new OnlineFragment();
                        fragment = onlineFragment.createInstance(MainActivity.this);
                        Replace_Fragments();
                        menuItem.setIcon(R.drawable.folder_network_open);
                        return true;
                    case R.id.DownloadsTab:
                        DownloadFragment downloadFragment = new DownloadFragment();
                        fragment = downloadFragment.createInstance(MainActivity.this);
                        menuItem.setIcon(R.drawable.arrow_down_open_icon);
                        Replace_Fragments();
                        return true;
                }

                return false;
            }
        });


    }

    public void Reference() {
        // Reference Object
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // create object
        fragment = new Fragment();

    }

    // Replace Fragments
    public void Replace_Fragments() {

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_Layout, fragment);
            ft.commit();
        }
    }

    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            Finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, " Click Back Again To Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void Finish() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);

    }
}
