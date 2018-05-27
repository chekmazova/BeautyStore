package com.example.android.beautystore1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] categories = new  String [] {"Salon Equipment", "Lashmaking", "Manicure", "Hairdressing", "Massage",
            "Waxing", "Accessories"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.lw_categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, categories);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent = new Intent(view.getContext(), CategoryActivity.class);
                    startActivityForResult(intent,0);
                }
                if (position==1){
                    Intent intent = new Intent(view.getContext(), CategoryActivity.class);
                    startActivityForResult(intent,1);
                }
                if (position==2){
                    Intent intent = new Intent(view.getContext(), CategoryActivity.class);
                    startActivityForResult(intent,2);
                }
                if (position==3){
                    Intent intent = new Intent(view.getContext(), CategoryActivity.class);
                    startActivityForResult(intent,3);
                }
                if (position==4){
                    Intent intent = new Intent(view.getContext(), CategoryActivity.class);
                    startActivityForResult(intent,4);
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
            case R.id.search:
                return true;
            case R.id.cart:
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                break;
            case R.id.options:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
