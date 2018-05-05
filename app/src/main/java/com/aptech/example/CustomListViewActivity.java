package com.aptech.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.io.ObjectInputStream;
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
        //Write
//        for(int i=0;i<10;i++) {
//            cookList.add(new Cook("", "Title " + i, "description " + i, "" + i));
//        }
//
//        //save cookList into File - Internal Storage
//        try {
//            ObjectOutputStream out = new ObjectOutputStream(openFileOutput("vidu.obj", MODE_PRIVATE));
//            out.writeObject(cookList);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Read
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(openFileInput("vidu.obj"));
            try {
                cookList = (List<Cook>) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //save cooklist into file - External Storage
        //Write
//        for(int i=0;i<10;i++) {
//            cookList.add(new Cook("", "Title " + i, "description " + i, "" + i));
//        }
//
//        //save cookList into File - Internal Storage
//        try {
//            String filename = Environment.getExternalStorageState() + "/vidu.obj";
//            FileOutputStream o = new FileOutputStream(filename);
//            ObjectOutputStream out = new ObjectOutputStream(o);
//            out.writeObject(cookList);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Read
//        ObjectInputStream in = null;
//        try {
//
//            String filename = Environment.getExternalStorageState() + "/vidu.obj";
//            FileInputStream o = new FileInputStream(filename);
//
//            in = new ObjectInputStream(o);
//            try {
//                cookList = (List<Cook>) in.readObject();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//
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
