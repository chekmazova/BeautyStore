package com.example.android.beautystore1.Activities.ClientActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.android.beautystore1.Adapters.CustomListAdapter;
import com.example.android.beautystore1.Adapters.GridAdapter;
import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Models.Cart;
import com.example.android.beautystore1.Models.Customer;
import com.example.android.beautystore1.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends MainActivity {
    private static final String TAG = "CartActivity";
    private Activity activity;
    DatabaseHelper databaseHelper;
    CustomListAdapter adapter;
    ListView listView;
    ElegantNumberButton btnQuantity;
    TextView txtTotalSum;
    Button btnCheckout;
    double finalSum;
    List<Cart> cartList;
    String customerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Shopping Bag");

        //get the intent extra from the CategoryActivity
        Intent receivedIntent = getIntent();

        //now get the productID we passed as an extra
        customerEmail = receivedIntent.getStringExtra("Email");

        initViews();
        initObjects();
        calculateCartSum();


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customerEmail!=null){
                   // databaseHelper.getCustomerByEmail(customerEmail).setCartItems(cartList);
                    Intent intentCheckout = new Intent(CartActivity.this, CheckoutActivity.class);
                    intentCheckout.putExtra("Email", customerEmail);
                    startActivity(intentCheckout);
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter = new CustomListAdapter(activity, databaseHelper);
        listView.setAdapter(adapter);
        cartList = databaseHelper.getAllCartLines();
        calculateCartSum();
    }

    private void initViews(){
        listView = findViewById(R.id.listView_cart);
        btnQuantity = findViewById(R.id.number_button);
        btnCheckout = findViewById(R.id.btn_checkout);
        txtTotalSum = findViewById(R.id.txtCartSum);
    }

    private void initObjects(){
        activity = this;
        databaseHelper = new DatabaseHelper(this);
        adapter = new CustomListAdapter(activity, databaseHelper);
        listView.setAdapter(adapter);
        cartList = databaseHelper.getAllCartLines();

    }

    private void calculateCartSum(){
        //calculate total price
        finalSum = 0;
        for (Cart cart : cartList)//problem is we don't have price in the model
        {
            int productID = cart.getProductID();
            finalSum += databaseHelper.getProduct(productID).getPrice()*cart.getQuantity();
            txtTotalSum.setText("TOTAL: "+finalSum + " RUR");
        }
    }

    public void onClickUpdateSUM(View view){
        adapter = new CustomListAdapter(activity, databaseHelper);
        listView.setAdapter(adapter);
        cartList = databaseHelper.getAllCartLines();
        calculateCartSum();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id== android.R.id.home){
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
