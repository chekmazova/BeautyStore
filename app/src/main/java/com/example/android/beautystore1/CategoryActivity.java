package com.example.android.beautystore1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    ArrayList<String> categories;
    GridView gridView;
    GridAdapter adapter;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categories = new ArrayList<>();
        activity = this;

        GridView gridView = findViewById(R.id.grdView);
        categories.add("ID1");
        categories.add("ID2");
        categories.add("ID3");
        categories.add("ID4");

        adapter = new GridAdapter(activity, categories);
        gridView.setAdapter(adapter);

    }
}
