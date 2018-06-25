package com.example.android.beautystore1.Activities.ManagementActivities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Models.Category;
import com.example.android.beautystore1.Models.Store;
import com.example.android.beautystore1.Models.Subcategory;
import com.example.android.beautystore1.R;

import java.util.ArrayList;

public class ManageStoreInfoActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText category_name;
    EditText store_address;
    EditText working_hours;
    EditText phone_number;
    EditText subcategory_name;
    Spinner mSpinner;
    ArrayList<String> mCategoryArrayList;
    ArrayAdapter<String> mCategoryArrayAdapter;
    String selection;
    String selectedCategory;
    int selected_categoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_store_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Manage Store Info");

        category_name = findViewById(R.id.txt_category);
        store_address = findViewById(R.id.txt_store_address);
        working_hours = findViewById(R.id.txt_working_hours);
        phone_number = findViewById(R.id.txt_store_phone);
        subcategory_name = findViewById(R.id.txt_subcategory);
        mSpinner = findViewById(R.id.spinner_category);
        mCategoryArrayList = new ArrayList<>();
        mCategoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mCategoryArrayList);
        databaseHelper = new DatabaseHelper(this);

        //launching a spinner
        databaseHelper.Open();
        Cursor cursor = databaseHelper.getAllCategoriesCursor();
        while (cursor.moveToNext()){
            selection = cursor.getString(1);
            mCategoryArrayList.add(selection);
        }
        mSpinner.setAdapter(mCategoryArrayAdapter);
        databaseHelper.Close();

        //set spinner onClickListener
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = mSpinner.getSelectedItem().toString();

                databaseHelper.Open();
                Cursor cursor_categoryID = databaseHelper.fetch_categoryByName(selectedCategory);
                if (cursor_categoryID.getCount()>0){
                    selected_categoryID = cursor_categoryID.getInt(0);
                    toastMessage("" + selected_categoryID);
                }
                databaseHelper.Close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
 }

    public void btnAddCategory (View view){
        String categoryName = category_name.getText().toString();
        Category category = new Category(categoryName);
        databaseHelper.Open();
        boolean status = databaseHelper.addCategory(category);
        databaseHelper.Close();
        if (status==true){
            Toast.makeText(ManageStoreInfoActivity.this, "Category added", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(ManageStoreInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
        category_name.setText("");
    }

    public void btnAddSubcategory (View view){

        Subcategory subcategory = new Subcategory();
        databaseHelper.Open();
        subcategory.setSubcategory_name(subcategory_name.getText().toString());
        subcategory.setCategoryID(selected_categoryID);
        boolean status = databaseHelper.insert_subcategory(subcategory);
        databaseHelper.Close();
        if (status==true){
            Toast.makeText(ManageStoreInfoActivity.this, "Subcategory added", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(ManageStoreInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
        subcategory_name.setText("");
    }

    public void btnAddStore (View view){
        String storeAddress = store_address.getText().toString();
        String storeWorkingHours = working_hours.getText().toString();
        String storePhoneNum = phone_number.getText().toString();
        Store store = new Store(storeAddress, storeWorkingHours, storePhoneNum);
        databaseHelper.Open();
        boolean status = databaseHelper.insert_store(store);
        databaseHelper.Close();
        if (status==true){
            Toast.makeText(ManageStoreInfoActivity.this, "Store added", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(ManageStoreInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
        store_address.setText("");
        working_hours.setText("");
        phone_number.setText("");
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
