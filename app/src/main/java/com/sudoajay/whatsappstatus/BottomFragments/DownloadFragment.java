package com.sudoajay.whatsappstatus.BottomFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sudoajay.whatsappstatus.MainActivity;
import com.sudoajay.whatsappstatus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {
    private MainActivity main_Activity;

    public DownloadFragment() {
        // Required empty public constructor
    }
    public DownloadFragment createInstance(MainActivity main_Activity  ) {
        this.main_Activity = main_Activity;
        return this;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download, container, false);
    }

}
