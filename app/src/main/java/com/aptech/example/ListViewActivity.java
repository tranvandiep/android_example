package com.aptech.example;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        //lay list view
        listView = findViewById(R.id.list_view);

        //khai bao adapter
        items = new String[]{"Android", "Java", "Php", "Hadoop", "Javascript"};
        adapter = new ArrayAdapter<String>(this, R.layout.item_list_view, items);

        //noi adapter vao list view
        listView.setAdapter(adapter);

        //add event listener when user click on item.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position >= items.length) return;
                String value = items[position];
                Toast.makeText(ListViewActivity.this, value, Toast.LENGTH_SHORT).show();

                //add once more into listview - start
                String[] newItems = new String[items.length + 1];
                for (int i=0;i<items.length;i++) {
                    newItems[i] = items[i];
                }
                newItems[items.length] = value + "-" + position;

                items = newItems;

                adapter = new ArrayAdapter<String>(ListViewActivity.this, R.layout.item_list_view, items);

                //noi adapter vao list view
                listView.setAdapter(adapter);
                //end

                showAlertDialog(value);
            }
        });
    }

    void showAlertDialog(String content) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(content);
        alertDialogBuilder.setTitle("Thong bao!!!");
        alertDialogBuilder.setPositiveButton("yes",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int arg1) {
                    dialog.dismiss();
                    finish();
                }
        });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
