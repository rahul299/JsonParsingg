package com.sakal.jsonnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sakal.jsonnews.adapter.SectionAdapter;
import com.sakal.jsonnews.modul.Sectionmodul;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  SectionAdapter.onItemClickListener {
    RecyclerView recyclerView;
    private List<Sectionmodul> mProtectedAppList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.section_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProtectedAppList.add(
                new Sectionmodul("ENTERTAINMENT",
                        "https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=35f2ae92ec3d4b0cbce3cc8e0da0a580")
        );

        mProtectedAppList.add(
                new Sectionmodul("TECHNOLOGY",
                        "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=35f2ae92ec3d4b0cbce3cc8e0da0a580")
        );

        mProtectedAppList.add(
                new Sectionmodul("HEALTH",
                        "https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=35f2ae92ec3d4b0cbce3cc8e0da0a580")
        );

        mProtectedAppList.add(
                new Sectionmodul("SPORTS",
                        "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=35f2ae92ec3d4b0cbce3cc8e0da0a580")
        );

        mProtectedAppList.add(
                new Sectionmodul("SCIENCE",
                        "https://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=35f2ae92ec3d4b0cbce3cc8e0da0a580")
        );
        mProtectedAppList.add(
                new Sectionmodul("BUSINESS",
                        "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=35f2ae92ec3d4b0cbce3cc8e0da0a580")
        );
        SectionAdapter sectionAdapter = new SectionAdapter(mProtectedAppList, this);
        recyclerView.setAdapter(sectionAdapter);
        sectionAdapter.setOnItemClickListener(MainActivity.this);

    }

    @Override
    public void onItemCLickListener(int position, ArrayList<Sectionmodul> videos) {
        Log.i("hhh","onItemCLickListener---");
        Sectionmodul info = videos.get(position);
        Intent intent = new Intent(this, FirstActivity.class);
        intent.putExtra("url",info.getUrl());
        intent.putExtra("Name",info.getSectionNAme());
        this.startActivity ( intent );
    }
}