package com.example.android.beautystore1.Activities.ClientActivities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Fragments.DeliveryFragment;
import com.example.android.beautystore1.Fragments.PickupFragment;
import com.example.android.beautystore1.R;

public class CheckoutActivity extends MainActivity {
    private Activity activity;
    DatabaseHelper databaseHelper;
    
    String customerEmail;
    EditText customerNameF;
    EditText customerNameL;
    EditText orderEmail;
    EditText customerPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getSupportActionBar().setTitle("Checkout");
        //Arrow back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //get the intent extra from the CategoryActivity
        Intent receivedIntent = getIntent();

        //now get the productID we passed as an extra
        customerEmail = receivedIntent.getStringExtra("Email");

    }

    private void initViews(){
        customerNameF = findViewById(R.id.new_name);
        customerNameL = findViewById(R.id.new_last_name);
        orderEmail = findViewById(R.id.new_email);
        customerPhone = findViewById(R.id.phone);
    }

    public void onChange (View view){
        Button delivery = findViewById(R.id.btn_delivery);
        Button pickup = findViewById(R.id.btn_pickup);
        Fragment fragment;

        if (view == delivery){
            fragment = new DeliveryFragment();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_delivery, fragment);
            fragmentTransaction.commit();
        }

        else if (view == pickup){
            fragment = new PickupFragment();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_delivery, fragment);
            fragmentTransaction.commit();
        }
    }
}
