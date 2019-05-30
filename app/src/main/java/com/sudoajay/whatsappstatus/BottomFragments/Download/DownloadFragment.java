package com.sudoajay.whatsappstatus.BottomFragments.Download;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sudoajay.whatsappstatus.MainActivity;
import com.sudoajay.whatsappstatus.R;
import com.sudoajay.whatsappstatus.Storage.GrabData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {
    private MainActivity main_Activity;
    private View view;
    private RecyclerView recyclerView;
    private Recyclerview_Adapter recyclerview_adapter;

    public DownloadFragment() {
        // Required empty public constructor
    }
    public DownloadFragment createInstance(MainActivity main_Activity  ) {
        this.main_Activity = main_Activity;
        return this;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_download, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        // Grab the data From Folder
        GrabData grabData = new GrabData(getActivity(),getResources().getString(R.string.app_name));

        for(String app :grabData.getArrayPath()){
            Toast.makeText(getContext(),app,Toast.LENGTH_SHORT).show();
        }


        recyclerview_adapter = new Recyclerview_Adapter(getActivity(),grabData.getArrayPath());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerview_adapter);


        // Inflate the layout for this fragment
        return view ;
    }

}
