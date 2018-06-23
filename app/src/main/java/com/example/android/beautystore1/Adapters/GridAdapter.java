package com.example.android.beautystore1.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.beautystore1.Activities.ClientActivities.ProductActivity;
import com.example.android.beautystore1.R;

public class GridAdapter extends BaseAdapter{
    private final String[] productArray;
    private final String[] descriptionArray;
    private final String[] priceArray;
    private final Integer[] imgArray;
    private Activity activity;
    //ArrayList names;


//    public GridAdapter(Activity activity, ArrayList names) {
//        this.names = names;
//        this.activity=activity;
//    }


    public GridAdapter(Activity activity, String[] productArray, String[] descriptionArray, String[] priceArray, Integer[] imgArray) {
        this.productArray = productArray;
        this.descriptionArray = descriptionArray;
        this.priceArray = priceArray;
        this.imgArray = imgArray;
        this.activity = activity;
    }

    @Override
    public int getCount() { return priceArray.length; }

    @Override
    public Object getItem(int position) {
        return productArray[position];
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

        ImageView imageView = convertView.findViewById(R.id.grdBasic);
        TextView mProduct = convertView.findViewById(R.id.txtName);
        TextView mDescription = convertView.findViewById(R.id.txtDescription);
        TextView mPrice = convertView.findViewById(R.id.txtPrice);

        mProduct.setText(productArray[position]);
        mDescription.setText(descriptionArray[position]);
        mPrice.setText(priceArray[position]);
        imageView.setImageResource(imgArray[position]);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ProductActivity.class);
                activity.startActivity(intent);
                activity.setContentView(R.layout.activity_product);
            }
        });


//        switch (names.get(position).toString()){
//            case "ID1": imageView.setImageResource(R.drawable.ic_id1);
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(activity, ProductActivity.class);
//                    activity.startActivity(intent);
//                    activity.setContentView(R.layout.activity_product);
//                }
//            });
//
//                break;
//            case "ID2": imageView.setImageResource(R.drawable.ic_id2);
//                break;
//            case "ID3": imageView.setImageResource(R.drawable.ic_id3);
//                break;
//            case "ID4": imageView.setImageResource(R.drawable.ic_id4);
//        }
        return convertView;
    }
}
