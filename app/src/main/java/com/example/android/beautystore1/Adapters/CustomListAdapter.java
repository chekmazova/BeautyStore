package com.example.android.beautystore1.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.beautystore1.R;

public class CustomListAdapter extends ArrayAdapter {

    private final Activity context;
    private final String[] productArray;
    private final String[] priceArray;
    private final Integer[] quantityArray;
    private final Integer[] imgArray;


    public CustomListAdapter(Activity context, int resource, String[] productArray,
                             String[] priceArray, Integer[] quantityArray, Integer[] imgArray) {
        super(context, resource, productArray);
        this.context = context;
        this.productArray = productArray;
        this.priceArray = priceArray;
        this.quantityArray = quantityArray;
        this.imgArray = imgArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View basicView = inflater.inflate(R.layout.cart_item_layout, null, true);

        TextView mProduct = basicView.findViewById(R.id.txtProduct);
        TextView mPrice = basicView.findViewById(R.id.txtPrice);
        TextView mQuantity = basicView.findViewById(R.id.txt_quant);
        ImageView mImage = basicView.findViewById(R.id.pic_product);

        mProduct.setText( productArray[position]);
        mPrice.setText(priceArray[position]);
        mQuantity.setText(quantityArray[position].toString());
        mImage.setImageResource(imgArray[position]);

        return basicView;
    }
}
