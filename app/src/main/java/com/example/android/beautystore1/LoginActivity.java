package com.example.android.beautystore1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText login;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.btn_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        login = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }
    public void saveInfo (View view){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", login.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.apply();

        Toast.makeText(this, "Email is saved", Toast.LENGTH_SHORT).show();
    }

}
