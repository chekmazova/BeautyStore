package com.example.android.beautystore1.Activities.ClientActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.android.beautystore1.Adapters.GridAdapter;
import com.example.android.beautystore1.R;

public class CategoryActivity extends MainActivity {

    String[] products = {"Professional hair dryer Basic-2", "Diffuser Harizma", "Professional hair dryer Harizma BASIC", "Rotating hitch-hair dryer"};
    String[] prices = {"1895 RUR", "285 RUR", "2300 RUR", "5505 RUR"};
    String[] descriptions = {"Basic-2 2000Вт Green", "Diffuser for prof hair dryers", "Harizma BASIC Coral", "With two nozzles (800W)"};
    Integer[] imgArray = {R.drawable.ic_id1, R.drawable.ic_id2, R.drawable.ic_id3, R.drawable.ic_id4};


    //ArrayList<String> categories;
    GridView gridView;
    GridAdapter adapter;
    public Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().setTitle("Hairdressing");

       // categories = new ArrayList<>();
        activity = this;

        GridView gridView = findViewById(R.id.grdView);
//        categories.add("ID1");
//        categories.add("ID2");
//        categories.add("ID3");
//        categories.add("ID4");

        adapter = new GridAdapter(activity, products, descriptions, prices, imgArray);
        gridView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
