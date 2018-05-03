package com.aptech.example;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Diep.Tran on 5/3/18.
 */

public class CustomAdapter extends BaseAdapter {
    Activity context;
    List<Cook> cookList;

    public CustomAdapter(Activity context, List<Cook> cookList) {
        this.context = context;
        this.cookList = cookList;
    }

    @Override
    public int getCount() {
        return cookList.size();
    }

    @Override
    public Object getItem(int position) {
        return cookList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row;
        row = inflater.inflate(R.layout.item_list_view_2, parent, false);

        TextView title, detail, price;
        ImageView imageView;
        ImageView i1;
        title = (TextView) row.findViewById(R.id.txt_title);
        detail = (TextView) row.findViewById(R.id.txt_description);
        price = (TextView) row.findViewById(R.id.txt_price);
        imageView = row.findViewById(R.id.imageView);

        title.setText(cookList.get(position).getTitle());
        detail.setText(cookList.get(position).getDescription());
        price.setText(cookList.get(position).getPrice());

        if(position % 2 == 0) {
            imageView.setImageResource(R.drawable.burger);
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }

        return (row);
    }
}
