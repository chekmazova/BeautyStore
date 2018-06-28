package com.example.android.beautystore1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Models.Cart;
import com.example.android.beautystore1.Models.Product;
import com.example.android.beautystore1.R;
import com.squareup.picasso.Picasso;

public class CustomListAdapter extends BaseAdapter {

    private Context context;
    private DatabaseHelper databaseHelper;
    Cart cartItem;

    public CustomListAdapter(Context context, DatabaseHelper databaseHelper) {
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public int getCount() {
        return databaseHelper.getAllCartLines().size();
    }

    @Override
    public Object getItem(int position) {
        return databaseHelper.getAllCartLines().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.layout_cart_item, null);
        }

        TextView mProduct = convertView.findViewById(R.id.txtProduct);
        TextView mPrice = convertView.findViewById(R.id.txtPrice);
        //TextView mQuantity = basicView.findViewById(R.id.txt_quant);
        final ElegantNumberButton btnQuantity = convertView.findViewById(R.id.number_button);
        ImageView mImage = convertView.findViewById(R.id.pic_product);

        cartItem = databaseHelper.getAllCartLines().get(position);

        Picasso.with(context)
                .load(databaseHelper.getProduct(cartItem.getProductID()).getImageURL())
                .placeholder(R.drawable.ic_action_name)
                .into(mImage);
        mProduct.setText(databaseHelper.getProduct(cartItem.getProductID()).getName());
        mPrice.setText("RUR "+ Double.toString(databaseHelper.getProduct(cartItem.getProductID()).getPrice()));
        btnQuantity.setNumber(cartItem.getQuantity().toString());

        btnQuantity.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quant = btnQuantity.getNumber();
                if (!quant.equals("0")) {
                    updateCartLine(position, Integer.parseInt(quant));
                    toastMessage("Quantity is updated!");
                } else {
                    toastMessage("Product deleted from the cart!");
                }
            }
        });

        return convertView;
    }

    private void updateCartLine (int position, int quantity) {
        Cart cart = databaseHelper.getAllCartLines().get(position);
        //updating Cart details in a list
        cart.setQuantity(quantity);

        // updating product in db
        databaseHelper.updateCartItem(cart);

        // refreshing the list
        databaseHelper.getAllCartLines().set(position, cart);
        notifyDataSetChanged();
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage (String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
