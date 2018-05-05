package com.aptech.example;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Diep.Tran on 5/5/18.
 */

public class CustomCursorAdapter extends CursorAdapter{
    Context context;

    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_list_view_2, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView email = view.findViewById(R.id.txt_title);
        TextView fullname = view.findViewById(R.id.txt_description);

        email.setText(cursor.getString(cursor.getColumnIndex(Student.ID_EMAIL)));

        fullname.setText(cursor.getString(cursor.getColumnIndex(Student.ID_FULL_NAME)));
    }
}
