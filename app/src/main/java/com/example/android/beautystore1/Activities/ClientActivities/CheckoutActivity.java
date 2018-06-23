package com.example.android.beautystore1.Activities.ClientActivities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.beautystore1.Fragments.DeliveryFragment;
import com.example.android.beautystore1.Fragments.PickupFragment;
import com.example.android.beautystore1.R;

public class CheckoutActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getSupportActionBar().setTitle("Checkout");

        //Arrow back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
