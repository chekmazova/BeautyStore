package com.example.android.beautystore1.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.android.beautystore1.ProductActivity;
import com.example.android.beautystore1.R;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter{
    ArrayList names;
    public static Activity activity;

    public GridAdapter(Activity activity, ArrayList names) {
        this.names = names;
        this.activity=activity;
    }


    @Override
    public int getCount() { return names.size(); }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(activity);
            convertView = inflater.inflate(R.layout.grid_category_item, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.grdBasic);

        switch (names.get(position).toString()){
            case "ID1": imageView.setImageResource(R.drawable.ic_id1);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ProductActivity.class);
                    activity.startActivity(intent);
                    activity.setContentView(R.layout.activity_product);
                }
            });

                break;
            case "ID2": imageView.setImageResource(R.drawable.ic_id2);
                break;
            case "ID3": imageView.setImageResource(R.drawable.ic_id3);
                break;
            case "ID4": imageView.setImageResource(R.drawable.ic_id4);
        }
        return convertView;
    }
}
