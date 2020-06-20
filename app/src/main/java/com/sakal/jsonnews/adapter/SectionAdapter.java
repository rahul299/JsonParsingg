package com.sakal.jsonnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sakal.jsonnews.R;
import com.sakal.jsonnews.modul.Sectionmodul;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.MyViewHolder> {

    private List<Sectionmodul> contactList;
    private Context mContext;
    private onItemClickListener onItemClickListener;

    public SectionAdapter(List<Sectionmodul> contactList, Context mContext) {
        this.contactList = contactList;
        this.mContext = mContext;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.section_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Sectionmodul mContact = contactList.get(position);
        holder.name.setText(mContact.getSectionNAme());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickListener != null)
                    onItemClickListener.onItemCLickListener(position, (ArrayList<Sectionmodul>) contactList);

            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

        }
    }
    public interface onItemClickListener {
        public void onItemCLickListener(int position, ArrayList<Sectionmodul> videos);

    }
    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
