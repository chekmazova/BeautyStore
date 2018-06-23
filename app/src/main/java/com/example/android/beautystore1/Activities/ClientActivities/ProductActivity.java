package com.example.android.beautystore1.Activities.ClientActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.beautystore1.R;

public class ProductActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setTitle("Hairdressing");

        Button button = findViewById(R.id.btnBuy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog("Item added to cart", "Do you wish to continue shopping?");
                //Toast.makeText(getApplicationContext(), "Item successfully added to shopping bag", Toast.LENGTH_LONG).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //Function to display dialog box - continue shopping or checkout
    public void customDialog(String title, String message){
        final AlertDialog.Builder builderBox = new AlertDialog.Builder(this);
        builderBox.setTitle(title);
        builderBox.setMessage(message);
        builderBox.setIcon(R.drawable.ic_action_cart);

        builderBox.setNegativeButton("Continue Shopping", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        builderBox.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
        builderBox.show();
    }

    //displays return arrow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //share function
    public void onClickShare(View view) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Please have a look at this product I would like to buy at the <Piont of Beauty>");
            startActivity(Intent.createChooser(intent, "Share via..."));
    }
}
