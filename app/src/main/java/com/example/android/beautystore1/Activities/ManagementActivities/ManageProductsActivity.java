package com.example.android.beautystore1.Activities.ManagementActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.beautystore1.R;

public class ManageProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_products);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Manage Product Info");
    }
}
