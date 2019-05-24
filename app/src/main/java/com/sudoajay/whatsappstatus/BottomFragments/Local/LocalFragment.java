package com.sudoajay.whatsappstatus.BottomFragments.Local;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sudoajay.whatsappstatus.MainActivity;
import com.sudoajay.whatsappstatus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalFragment extends Fragment {

    private MainActivity main_Activity;
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public LocalFragment() {
        // Required empty public constructor
    }

    public LocalFragment createInstance(MainActivity main_Activity) {
        this.main_Activity = main_Activity;
        return this;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_local, container, false);

        Reference();


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(main_Activity.getApplicationContext()
                , getChildFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private void Reference() {
        tabLayout = view.findViewById(R.id.localTab);
        viewPager = view.findViewById(R.id.viewPager_Local);
    }

}
