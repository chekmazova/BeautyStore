package com.example.android.beautystore1.Activities.ClientActivities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.beautystore1.Activities.ManagementActivities.ManageProductsActivity;
import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Activities.ManagementActivities.ManageStoreInfoActivity;
import com.example.android.beautystore1.Models.Category;
import com.example.android.beautystore1.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DatabaseHelper databaseHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Beauty Point");

        listView = findViewById(R.id.lw_categories);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: display data in a listView");
        databaseHelper.Open();
        Cursor cursor = databaseHelper.getAllCategoriesCursor();
        //get the data and append to a list
        ArrayList listCategories = new ArrayList();
        while (cursor.moveToNext()) {
            //add the data from database to column 1
            //then add it to the ArrayList
            listCategories.add(cursor.getString(1));
        }

        //Create ListAdapter and set the adapter
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listCategories);
        listView.setAdapter(adapter);

        //set onItemClickListener to the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String category_name = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You clicked on "+category_name);

                databaseHelper.Open();
                Cursor cursor_categoryID = databaseHelper.fetch_categoryByName(category_name); //get the ID associated with that name
                int categoryID = -1;
                if (cursor_categoryID.getCount()>0){
                    categoryID = cursor_categoryID.getInt(0);
                    toastMessage(""+categoryID);
                }
                databaseHelper.Close();

                if (categoryID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + categoryID);
                    Intent viewCategoryIntent = new Intent(MainActivity.this, CategoryActivity.class);
                    //Passing the category ID and name to another Activity
                    viewCategoryIntent.putExtra("ID", categoryID);
                    viewCategoryIntent.putExtra("name", category_name);
                    startActivity(viewCategoryIntent);
                } else {
                    toastMessage("No ID associated with that category");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
                return true;
            case R.id.menu_cart:
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_home:
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.menu_personal_account:
                Intent intent4 = new Intent(getApplicationContext(), AccountDetailsActivity.class);
                startActivity(intent4);
                break;

                case R.id.menu_login:
                Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.menu_staff_login:
                Intent intent3 = new Intent(getApplicationContext(), ManageProductsActivity.class);
                startActivity(intent3);
                break;
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
