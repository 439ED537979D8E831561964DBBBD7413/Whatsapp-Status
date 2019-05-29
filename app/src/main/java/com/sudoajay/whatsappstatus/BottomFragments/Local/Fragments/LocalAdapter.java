package com.sudoajay.whatsappstatus.BottomFragments.Local.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sudoajay.whatsappstatus.R;

import java.io.File;
import java.util.ArrayList;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder> {
    private Context mContext;
    private String tabName;
    private ArrayList<String> arrayPath;

    public LocalAdapter(final Context context, final ArrayList<String> arrayPath, final String tabName) {
        mContext = context;
        this.arrayPath = arrayPath;
        this.tabName = tabName;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView localCardViewImageView;

        public ViewHolder(View v) {
            super(v);
            localCardViewImageView = v.findViewById(R.id.localCardViewImageView);

        }
    }

    @Override
    public LocalAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_local_card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Check_For_Extension(arrayPath.get(position), holder.localCardViewImageView);
        // Set a random height for TextView
        holder. localCardViewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PhotoVideoView.class);
                intent.putExtra("WhichTab",tabName);
                intent.putExtra("FilePath",arrayPath.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayPath.size();
    }

    public void Check_For_Extension(String path, ImageView imageView) {

        try {
            int i = path.lastIndexOf('.');
            String extension = "";
            if (i > 0) {
                extension = path.substring(i + 1);
            }

            if (extension.equals("jpg") || extension.equals("mp4")) {
                // Images || Videos
                Glide.with(mContext)
                        .asBitmap()
                        .load(Uri.fromFile(new File(path)))
                        .into(imageView);
            }
        } catch (Exception e) {
            imageView.setImageResource(R.drawable.photo_local_icon);
        }

    }
}