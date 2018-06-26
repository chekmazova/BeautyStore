package com.example.android.beautystore1.Activities.ManagementActivities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.beautystore1.Activities.ClientActivities.MainActivity;
import com.example.android.beautystore1.Adapters.ProductRecyclerAdapter;
import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Listeners.RecyclerTouchListener;
import com.example.android.beautystore1.Models.Product;
import com.example.android.beautystore1.R;

import java.util.ArrayList;
import java.util.List;

public class ViewProductsActivity extends AppCompatActivity {

    private AppCompatActivity activity = ViewProductsActivity.this;
    private RecyclerView recyclerViewProducts;
    private ProductRecyclerAdapter productRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    private List<Product> listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        recyclerViewProducts = findViewById(R.id.recyclerViewProduct);

        initObjects();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerViewProducts.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerViewProducts, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));
    }

    private void initObjects(){
        listProducts = new ArrayList<>();
        productRecyclerAdapter = new ProductRecyclerAdapter(listProducts, activity);
        databaseHelper = new DatabaseHelper(activity);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProducts.setLayoutManager(layoutManager);
        recyclerViewProducts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProducts.setHasFixedSize(true);
        recyclerViewProducts.setAdapter(productRecyclerAdapter);

        getDataFromSQLite();
    }

    /**
     *
     * this method is to fetch all product records from SQLite
     */
    private void getDataFromSQLite(){
        //AsyncTask is used that SQLite operation not block the UI thread
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                listProducts.clear();
                listProducts.addAll(databaseHelper.getAllProducts());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                productRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    /**
     * Update product info in db and refresh the productList
     * @param position
     */
    private void updateProduct (int position) {
        Product product = databaseHelper.getProduct(position);
        // updating product in db
        databaseHelper.updateProduct(product);

        // refreshing the list
        listProducts.set(position, product);
        productRecyclerAdapter.notifyItemChanged(position);
    }


    private void deleteProduct(int position) {
        // deleting the product from db
        databaseHelper.deleteProduct(listProducts.get(position));

        // removing the note from the list
        listProducts.remove(position);
        productRecyclerAdapter.notifyItemRemoved(position);
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showEditProductDialog(listProducts.get(position), position);
                } else {
                    deleteProduct(position);
                    toastMessage("Product was removed!");
                }
            }
        });
        builder.show();
    }

    /**
     * Popping out Dialog box
     */
    public void showEditProductDialog (Product product, final int position){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.dialog_edit_product_layout, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(ViewProductsActivity.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText name = view.findViewById(R.id.txt_product_name1);
        final EditText brand = view.findViewById(R.id.txt_brand1);
        final EditText description = view.findViewById(R.id.txt_description1);
        final EditText imageURL = view.findViewById(R.id.txt_imageURL1);
        final EditText volume = view.findViewById(R.id.txt_volume1);
        final EditText price = view.findViewById(R.id.txt_price1);
        final Button btnSave = view.findViewById(R.id.btnRegisterProduct1);
        final Button btnCancel = view.findViewById(R.id.btnViewProduct1);

        name.setText(product.getName());
        brand.setText(product.getBrand());
        description.setText(product.getDescription());
        imageURL.setText(product.getImageURL());
        volume.setText(product.getVolume());
        price.setText(product.getPrice());


        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.getWindow().setLayout(1100,1300);
        alertDialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct(position);
                toastMessage("Product info updated!");
                alertDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


//        Dialog dialog = new Dialog((this));
//        dialog.setContentView(R.layout.dialog_edit_product_layout);
//        dialog.setTitle("Edit Product Info");
//        dialog.getWindow().setLayout(1100,1300);
//        dialog.show();
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
