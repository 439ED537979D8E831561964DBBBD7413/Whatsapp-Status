package com.sudoajay.whatsappstatus.BottomFragments.Download;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sudoajay.whatsappstatus.R;

import java.util.List;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.MyViewHolder> {

    private List<String> stringList;
    private Activity activity;

    public Recyclerview_Adapter(final Activity activity, final List<String> list) {
        stringList = list;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView coverImageView, infoImageView;
        public TextView nameTextView, pathTextView;

        public MyViewHolder(View v) {
            super(v);
            nameTextView = v.findViewById(R.id.nameTextView);
            pathTextView = v.findViewById(R.id.pathTextView);
            coverImageView = v.findViewById(R.id.coverImageView);
            infoImageView = v.findViewById(R.id.infoImageView);


        }
    }

    @NonNull
    @Override
    public Recyclerview_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recyclerview, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nameTextView.setText(stringList.get(position));
    }


    @Override
    public int getItemCount() {
        return stringList.size();
    }


}
