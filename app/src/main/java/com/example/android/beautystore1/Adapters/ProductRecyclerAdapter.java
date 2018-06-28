package com.example.android.beautystore1.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Models.Product;
import com.example.android.beautystore1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>{
    private static final String TAG = "ProductRecyclerAdapter";
    private List<Product> listProducts;
    private Context context;

    public ProductRecyclerAdapter(List<Product> listProducts, Context context) {
        this.listProducts = listProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate recycler item
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product_view_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Picasso.with(context)
                .load(listProducts.get(position).getImageURL())
                .placeholder(R.drawable.ic_action_name)
                .into(holder.imageViewProduct);
        holder.textViewID.setText(listProducts.get(position).getProduct_id().toString());
        holder.textViewProduct.setText(listProducts.get(position).getName());
        holder.textViewPrice.setText(Double.toString(listProducts.get(position).getPrice())+"RUR");
    }

    @Override
    public int getItemCount() {
        Log.v(ProductRecyclerAdapter.class.getSimpleName(),""+listProducts.size());
        return listProducts.size();
    }

    /**
     * ViewHolder Class
     */
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView textViewProduct;
        public AppCompatTextView textViewPrice;
        public AppCompatTextView textViewID;
        public AppCompatImageView imageViewProduct;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewProduct = itemView.findViewById(R.id.textViewProduct);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewID = itemView.findViewById(R.id.textViewIDP);
            imageViewProduct = itemView.findViewById(R.id.imageProduct);
        }
    }
}
