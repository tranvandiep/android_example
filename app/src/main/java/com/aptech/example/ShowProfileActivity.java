package com.aptech.example;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        TextView txtFullname = findViewById(R.id.txt_fullname);
        //khai bao
        SharedPreferences sharedPreferences = getSharedPreferences("Example", Activity.MODE_PRIVATE);

        //save
        String fullname = sharedPreferences.getString("fullname", "");
        txtFullname.setText(fullname);

    }
}
