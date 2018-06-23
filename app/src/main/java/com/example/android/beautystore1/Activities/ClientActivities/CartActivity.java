package com.example.android.beautystore1.Activities.ClientActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.beautystore1.Adapters.CustomListAdapter;
import com.example.android.beautystore1.R;

public class CartActivity extends MainActivity {
    String[] productArray = {"Turbo Power Twin Turbo 2800", "Pro Thermal Styler"};
    String[] priceArray = {"1895 RUR", "5505 RUR" };
    Integer[] quantityArray = {1, 1};
    Integer[] imgArray = {R.drawable.ic_id1, R.drawable.ic_id4};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Shopping Bag");

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.cart_item_layout, productArray,
                priceArray, quantityArray, imgArray);
        listView = findViewById(R.id.listView_cart);
        listView.setAdapter(adapter);

        Button button = findViewById(R.id.btn_checkout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
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