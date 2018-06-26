package com.example.android.beautystore1.Activities.ManagementActivities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Models.Product;
import com.example.android.beautystore1.R;

import java.util.ArrayList;

public class ManageProductsActivity extends AppCompatActivity {

    private final AppCompatActivity activity = ManageProductsActivity.this;
    private EditText name, brand, description, imageURL, volume, price;
    private Spinner spinner_subcategoryID;
    Button btnRegister, btnView;

    ArrayList<String> subcategoryArrayList;
    ArrayAdapter<String> subcategoryArrayAdapter;
    DatabaseHelper databaseHelper;

    String selected_subcatName;
    int selected_subcatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_products);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Manage Product Info");

        initViews();
        initObjects();

        //launching a spinner
        databaseHelper.Open();
        Cursor cursor = databaseHelper.getAllSubcategoriesCursor();
        while (cursor.moveToNext()){
            subcategoryArrayList.add(cursor.getString(1));
        }
        spinner_subcategoryID.setAdapter(subcategoryArrayAdapter);
        databaseHelper.Close();

        //set spinner onClickListener
        spinner_subcategoryID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_subcatName = spinner_subcategoryID.getSelectedItem().toString();

                databaseHelper.Open();
                Cursor cursor_subcat = databaseHelper.fetch_subcategoryByName(selected_subcatName);
                if (cursor_subcat.getCount()>0){
                    selected_subcatID = cursor_subcat.getInt(0);
                    toastMessage("" + selected_subcatID);
                }
                databaseHelper.Close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ViewProductsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews(){
        name = findViewById(R.id.txt_product_name);
        brand = findViewById(R.id.txt_brand);
        description = findViewById(R.id.txt_description);
        imageURL = findViewById(R.id.txt_imageURL);
        volume = findViewById(R.id.txt_volume);
        price = findViewById(R.id.txt_price);
        spinner_subcategoryID = findViewById(R.id.spinner_subcategory);
        btnRegister = findViewById(R.id.btnRegisterProduct);
        btnView = findViewById(R.id.btnViewProduct);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(this);
        subcategoryArrayList = new ArrayList<>();
        subcategoryArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, subcategoryArrayList);

    }

    public void btnAddSubcategory (View view){
        Product product = new Product();
        product.setName(name.getText().toString().trim());
        product.setBrand(brand.getText().toString().trim());
        product.setDescription(description.getText().toString().trim());
        product.setSubcategoryID(selected_subcatID);
        product.setImageURL(imageURL.getText().toString().trim());
        product.setVolume(volume.getText().toString().trim());
        product.setPrice(price.getText().toString().trim());

        boolean status = databaseHelper.addProduct(product);

        if (status==true){
            Toast.makeText(activity, "Product added", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
        }
        emptyInputEditText();
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        name.setText(null);
        brand.setText(null);
        description.setText(null);
        imageURL.setText(null);
        volume.setText(null);
        price.setText(null);
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
