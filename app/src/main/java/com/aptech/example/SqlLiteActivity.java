package com.aptech.example;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SqlLiteActivity extends AppCompatActivity {
    Button btn_save;
    Button btn_load;

    EditText txtFullname;
    EditText txtEmail;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);

        DBHelper.getInstance(this);

        btn_load = findViewById(R.id.btn_load);
        btn_save = findViewById(R.id.btn_save);

        listView = findViewById(R.id.list_view);

        txtEmail = findViewById(R.id.txt_email);
        txtFullname = findViewById(R.id.txt_fullname);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDatabase();
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFromDatabase();
            }
        });

        //binding result set of cursor into list view
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        String query = "SELECT * FROM STUDENT WHERE 1 ORDER BY _id DESC";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        CustomCursorAdapter adapter = new CustomCursorAdapter(this, cursor);

        listView.setAdapter(adapter);
    }

    void saveDatabase() {
        String fullname = txtFullname.getText().toString();
        String email = txtEmail.getText().toString();
        if(fullname.length() == 0 || email.length() == 0) {
            Toast.makeText(this, "Khong duoc de rong fullname & email", Toast.LENGTH_SHORT).show();
            return;
        }

        Student student = new Student(fullname, email);
        StudentEntiry.insert(student);

        txtEmail.setText("");
        txtFullname.setText("");
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    void loadFromDatabase() {
        Student student = StudentEntiry.findLast();
        if(student != null) {
            txtFullname.setText(student.getFullname());
            txtEmail.setText(student.getEmail());
        }
    }
}
