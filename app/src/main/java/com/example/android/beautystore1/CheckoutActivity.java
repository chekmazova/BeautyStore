package com.example.android.beautystore1;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //getActionBar().setTitle("Checkout");
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
