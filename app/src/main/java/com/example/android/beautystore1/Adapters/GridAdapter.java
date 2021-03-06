package com.example.android.beautystore1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.R;
import com.squareup.picasso.Picasso;

public class GridAdapter extends BaseAdapter{

    private Context context;
    private DatabaseHelper databaseHelper;


    public GridAdapter(Context context, DatabaseHelper databaseHelper) {
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public int getCount() { return databaseHelper.getAllProducts().size(); }

    @Override
    public Object getItem(int position) {
        return databaseHelper.getAllProducts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.layout_grid_category_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.grdBasic);
        TextView mProduct = convertView.findViewById(R.id.txtName);
        TextView mBrand = convertView.findViewById(R.id.txtBrand);
        TextView mPrice = convertView.findViewById(R.id.txtPrice);


        Picasso.with(context)
                .load(databaseHelper.getAllProducts().get(position).getImageURL())
                .resize(180,180)
                .centerCrop()
                .placeholder(R.drawable.ic_action_name)
                .into(imageView);
        mProduct.setText(databaseHelper.getAllProducts().get(position).getName());
        mBrand.setText(databaseHelper.getAllProducts().get(position).getBrand());
        mPrice.setText("RUR "+Double.toString(databaseHelper.getAllProducts().get(position).getPrice()));

        return convertView;
    }
}
