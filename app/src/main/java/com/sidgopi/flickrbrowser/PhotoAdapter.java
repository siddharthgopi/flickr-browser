package com.sidgopi.flickrbrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CouponDunia on 23/06/16.
 */


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    private List<Photo> photoList = new ArrayList<>();
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author, tags;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            author = (TextView) view.findViewById(R.id.author);
            tags = (TextView) view.findViewById(R.id.tags);
        }
    }


    public PhotoAdapter(Context context,  List<Photo> photoList) {
        mContext = context;
        this.photoList = photoList;
        Log.d("adapter const called", " OK");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_list_row, parent, false);
        Log.d("layout inflated", " OK");

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.title.setText(photo.getTitle());
        Log.d("photo get-title", photo.getTitle());
        holder.author.setText(photo.getAuthor());
        holder.tags.setText(photo.getTags());
    }

    @Override
    public int getItemCount() {
        Log.d("length adapter  ", String.valueOf(photoList.size()));
        return photoList.size();
    }

}