package com.aptech.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewActivity extends AppCompatActivity {
    ListView listView;
    List<Cook> cookList = new ArrayList<>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        listView = findViewById(R.id.list_view);

        //setup date
        for(int i=0;i<10;i++) {
            cookList.add(new Cook("", "Title " + i, "description " + i, "" + i));
        }

        adapter = new CustomAdapter(this, cookList);

        //binding adapter into listview
        listView.setAdapter(adapter);

        //add event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cookList.add(new Cook("", "Title " + i, "description " + i, "" + i));

                adapter.notifyDataSetChanged();
            }
        });
    }
}
