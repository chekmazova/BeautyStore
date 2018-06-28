package com.example.android.beautystore1.Activities.ClientActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.android.beautystore1.Adapters.CustomListAdapter;
import com.example.android.beautystore1.Adapters.GridAdapter;
import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Models.Cart;
import com.example.android.beautystore1.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends MainActivity {
    public Activity activity;
    DatabaseHelper databaseHelper;
    ListView listView;
    CustomListAdapter adapter;
    ElegantNumberButton mQuantity;
    Button btnCheckout;
    double finalSum;
    List<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Shopping Bag");

        initViews();
        initObjects();

    }



    private void initViews(){
        listView = findViewById(R.id.listView_cart);
        mQuantity = findViewById(R.id.number_button);
        btnCheckout = findViewById(R.id.btn_checkout);
    }

    private void initObjects(){
        activity = this;
        databaseHelper = new DatabaseHelper(this);
        adapter = new CustomListAdapter(activity, databaseHelper);
        listView.setAdapter(adapter);

        cartList = databaseHelper.getAllCartLines();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
