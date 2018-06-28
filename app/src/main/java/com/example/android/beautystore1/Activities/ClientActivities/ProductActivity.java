package com.example.android.beautystore1.Activities.ClientActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Models.Cart;
import com.example.android.beautystore1.Models.Product;
import com.example.android.beautystore1.R;
import com.squareup.picasso.Picasso;

public class ProductActivity extends MainActivity {

    Context context;
    ImageView productIMG;
    TextView productName, productBrand, productPrice, productInfo;
    Button btnBuy;

    int productID;
    DatabaseHelper databaseHelper;
    Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        //get the intent extra from the CategoryActivity
        Intent receivedIntent = getIntent();

        //now get the productID we passed as an extra
        productID = receivedIntent.getIntExtra("ID", -1); //NOTE: -1 is just a default value if the ID does not exist

        initViews();
        initObjects();

        selectedProduct = databaseHelper.getProduct(productID);

        getDataFromSQLite();

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCartLine();
                customDialog("Item added to cart", "Do you wish to continue shopping?");
                //Toast.makeText(getApplicationContext(), "Item successfully added to shopping bag", Toast.LENGTH_LONG).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(selectedProduct.getName());
    }

    private void initViews(){
        productName = findViewById(R.id.txtProductName);
        productBrand = findViewById(R.id.txtProductBrand);
        productIMG = findViewById(R.id.imgProduct);
        productPrice = findViewById(R.id.txtProductPrice);
        productInfo = findViewById(R.id.txtProductDescription);
        btnBuy = findViewById(R.id.btnBuy);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(this);
        selectedProduct = new Product();
        context = ProductActivity.this;

    }

    private void getDataFromSQLite(){
        Picasso.with(context)
                .load(selectedProduct.getImageURL())
                .placeholder(R.drawable.ic_action_name)
                .into(productIMG);
        productName.setText(selectedProduct.getName());
        productBrand.setText(selectedProduct.getBrand());
        productPrice.setText(Double.toString(selectedProduct.getPrice()));
        productInfo.setText(selectedProduct.getDescription());
    }

    /**
     * Method to display dialog box - continue shopping or checkout
     * @param title
     * @param message
     */
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

    public void addNewCartLine (){
        Cart cart = new Cart();
        cart.setProductID(selectedProduct.getProduct_id());
        cart.setPrice(selectedProduct.getPrice());
        cart.setProductIMG(selectedProduct.getImageURL());
        cart.setProductName(selectedProduct.getName());
        cart.setQuantity(1);

        databaseHelper.addCartLine(cart);

//        if (status==true){
//            toastMessage("Product added");
//        } else{
//            toastMessage("Error");
//        }
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

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
