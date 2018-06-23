package com.example.android.beautystore1.Activities.ClientActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.beautystore1.R;

public class AccountDetailsActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Account Details");

        TextView deactivate = findViewById(R.id.textView27);
        deactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog("Notification","Are you sure you want to deactivate your account?");
            }
        });
    }

    public void customDialog(String title, String message){
        final AlertDialog.Builder builderBox = new AlertDialog.Builder(this);
        builderBox.setTitle(title);
        builderBox.setMessage(message);
        builderBox.setIcon(R.drawable.ic_action_cart);

        builderBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        builderBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
        builderBox.show();
    }
}
