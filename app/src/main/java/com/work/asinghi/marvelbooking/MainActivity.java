package com.work.asinghi.marvelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.work.asinghi.marvelbooking.adapter.CustomAdapter;
import com.work.asinghi.marvelbooking.model.Locations;
import com.work.asinghi.marvelbooking.model.Marvel;
import com.work.asinghi.marvelbooking.network.GetLocationList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetLocationList.AsyncResponse {

    List<Locations> locationsList = new ArrayList<Locations>();
    RecyclerView recyclerView;
    TextView hiText;
    ImageView heartToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hiText = findViewById(R.id.hiText);
        initsearch();
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void initsearch() {
        String api = "http://www.mocky.io/v2/5c261ccb3000004f0067f6ec";
        new GetLocationList(MainActivity.this).execute(api);
    }

    @Override
    public void processFinish(String output) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Marvel marvel = gson.fromJson(output, Marvel.class);
        hiText.setText(getString(R.string.hiName, marvel.component1()));
        locationsList = marvel.component2();

        recyclerView.setAdapter(new CustomAdapter(getApplicationContext(), locationsList, new CustomAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(@NotNull View view, @NotNull final Locations item, @NotNull final int position) {
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                intent.putExtra("date", item.getDate());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("place", item.getPlace());
                intent.putExtra("rate", item.getRate());
                intent.putExtra("url", item.getUrl());

                startActivity(intent);

                final ImageView picture=(ImageView)view.findViewById(R.id.heart);
                picture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       locationsList.get(position).setLike(!item.getLike());
                       recyclerView.getAdapter().notifyDataSetChanged();

                    }
                });
            }
        }));

    }

}
