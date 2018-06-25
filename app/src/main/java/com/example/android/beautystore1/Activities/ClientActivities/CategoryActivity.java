package com.example.android.beautystore1.Activities.ClientActivities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.beautystore1.Adapters.GridAdapter;
import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Models.Category;
import com.example.android.beautystore1.Models.Product;
import com.example.android.beautystore1.R;

public class CategoryActivity extends MainActivity {
    private static final String TAG = "CategoryActivity";

    public Activity activity;
    DatabaseHelper databaseHelper;
    GridView gridView;
    GridAdapter adapter;

    private String selectedCategory;
    private int selectedCategoryId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //get the intent extra from the MainActivity
        Intent receivedIntent = getIntent();

        //now get the categoryID we passed as an extra
        selectedCategoryId = receivedIntent.getIntExtra("ID", -1); //NOTE: -1 is just a default value if the ID does not exist

        //now get the categoryName we passed as an extra
        selectedCategory = receivedIntent.getStringExtra("name");

        activity = this;
        databaseHelper = new DatabaseHelper(this);

        gridView = findViewById(R.id.grdView);
        adapter = new GridAdapter(activity, databaseHelper);
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int product_ID = databaseHelper.getAllProducts().get(position).getProduct_id();
                toastMessage("Product ID is "+product_ID);
                Log.d(TAG, "onItemClick: The ID is: "+product_ID);

                    Intent viewCategoryIntent = new Intent(CategoryActivity.this, ProductActivity.class);
                    //Passing the product ID to another Activity
                    viewCategoryIntent.putExtra("ID", product_ID);
                    startActivity(viewCategoryIntent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //set the title to show the current selected categoryName in the header
        getSupportActionBar().setTitle(selectedCategory);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
