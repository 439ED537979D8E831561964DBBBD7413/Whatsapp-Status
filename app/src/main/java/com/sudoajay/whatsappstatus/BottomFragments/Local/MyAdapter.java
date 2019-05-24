package com.sudoajay.whatsappstatus.BottomFragments.Local;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sudoajay.whatsappstatus.BottomFragments.Local.Fragments.PhotoVideo;

public class MyAdapter extends FragmentPagerAdapter {

    private String[] tabName= {"photo","video"};
    private int totalTabs;
    private Context mContext;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.mContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PhotoVideo(tabName[0]);
            case 1:
                return new PhotoVideo(tabName[1]);
            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}