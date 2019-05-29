package com.sudoajay.whatsappstatus.BottomFragments.Local.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.sudoajay.whatsappstatus.R;
import com.sudoajay.whatsappstatus.Storage.GrabData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoVideo extends Fragment {

    private View view;
    private String tabName;
    private RecyclerView mRecyclerView;
    private final String statusPath = "/WhatsApp/Media/.Statuses/";
    private ArrayList<String> arrayPath;
    public PhotoVideo(final String tabName) {
        this.tabName = tabName;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_local_photovideo, container, false);

        Reference();

        GrabData grabData = new GrabData(statusPath);
        arrayPath = grabData.getArrayPath();


        for(int j = arrayPath.size()-1;j >0 ;j--){
            if(tabName.equalsIgnoreCase("photo")){
                if(arrayPath.get(j).contains(".mp4")){
                    arrayPath.remove(j);
                    continue;
                }
            }else {
                if(arrayPath.get(j).contains(".jpg")){
                    arrayPath.remove(j);
                    continue;
                }
            }

        }

        // Define a layout for RecyclerView

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setLayoutManager(manager);


        // Initialize a new instance of RecyclerView Adapter instance
        RecyclerView.Adapter mAdapter = new LocalAdapter(getContext(),arrayPath,tabName);

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void Reference() {
        // Get the widgets reference from XML layout
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

}

