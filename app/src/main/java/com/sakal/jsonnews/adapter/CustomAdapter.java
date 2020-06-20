package com.sakal.jsonnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakal.jsonnews.R;
import com.sakal.jsonnews.modul.Modul;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Modul> contactList;

    private Context mContext;


    public CustomAdapter(List<Modul> list,Context cntx) {
        this.contactList = list;
        this.mContext = cntx;
        Log.i("kkkk","list---");

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("kkkk","list---1111");

        final Modul mContact = contactList.get(position);

        holder.Title.setText(mContact.getTitle());
        holder.Names.setText(mContact.getNames());
        holder.description.setText(mContact.getDescription());
        Picasso.with(mContext).load(mContact.getUrlToImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        Log.i("kkkk","contactList.size()---"+contactList.size());
        return contactList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView  Names,Title, description;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            Title = (TextView) view.findViewById(R.id.title);
            Names = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            imageView = (ImageView) view.findViewById(R.id.image_view);


        }
    }
}
