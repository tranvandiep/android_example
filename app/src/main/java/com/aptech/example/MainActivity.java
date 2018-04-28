package com.aptech.example;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etFullname;
    Button btnSave;
    Button btnReset;
    Button btnNewActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding - find
        etFullname = findViewById(R.id.et_fullname);
        btnSave = findViewById(R.id.btn_show);
        btnReset = findViewById(R.id.btn_reset);
        btnNewActivity = findViewById(R.id.btn_new_activity);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //source code
                String fullname = etFullname.getText().toString();
                Toast.makeText(MainActivity.this, fullname, Toast.LENGTH_SHORT).show();
                saveSharedPreferences();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //source code
                clearData();
            }
        });

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //source code
                showNewActivity();
            }
        });

        Log.d(MainActivity.class.getName(), "onCreate.....");
    }

    void saveSharedPreferences() {
        //khai bao
        SharedPreferences sharedPreferences = getSharedPreferences("Example", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //save
        String fullname = etFullname.getText().toString();
        editor.putString("fullname", fullname);

        //commit & close
        editor.commit();
    }

    void clearData() {
        etFullname.setText("");
    }

    @Override
    public void onBackPressed() {
    }

    void showNewActivity() {
        Intent intent = new Intent(MainActivity.this, ShowProfileActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(MainActivity.class.getName(), "onStart.....");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(MainActivity.class.getName(), "onResume.....");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(MainActivity.class.getName(), "onPause.....");
//        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(MainActivity.class.getName(), "onStop.....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(MainActivity.class.getName(), "onDestroy.....");
    }
}
