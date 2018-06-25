package com.example.android.beautystore1.Activities.ClientActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Helpers.InputValidation;
import com.example.android.beautystore1.Models.Customer;
import com.example.android.beautystore1.Models.User;
import com.example.android.beautystore1.R;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private final AppCompatActivity activity = LoginActivity.this;
    ScrollView loginLayout;
    TextInputLayout textInputLayoutEmail, textInputLayoutPassword;
    TextInputEditText inputEmail, inputPassword;
    AppCompatTextView staffLogin;
    Button btnRegister, btnSignIn;

    InputValidation inputValidation;
    DatabaseHelper mDatabaseHelper;

    String email;
    String hashPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Login to your Account");

        initViews();
        initObjects();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyFromSQLite();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(activity, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });

    }

    private void initViews(){
        loginLayout = findViewById(R.id.login_form);
        textInputLayoutEmail = findViewById(R.id.inputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.inputLayoutPassword);

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        staffLogin = findViewById(R.id.textViewLinkStaffLogin);

        btnRegister = findViewById(R.id.btn_register);
        btnSignIn = findViewById(R.id.btn_signin);
    }

    private void initObjects(){
        inputValidation = new InputValidation(this);
        mDatabaseHelper = new DatabaseHelper(this);
    }

    /**
     * Method to check if the
     */
    private void verifyFromSQLite() {
        //Extracting the text from the input fields
        email = inputEmail.getText().toString().trim();
        String temp_password = inputPassword.getText().toString().trim();

        //Function to secure password with SQLite
        try {
            hashPass = hashPassword(temp_password);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        if (!inputValidation.isInputEditTextFilled(inputEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(inputEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(inputPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        if (mDatabaseHelper.checkCustomerExist(email, hashPass)) {

            Intent checkoutIntent = new Intent(activity, CheckoutActivity.class);
            startActivity(checkoutIntent);
        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(loginLayout, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        }
    }

    /**
     * Shared preferences
     */

    public void saveInfo (View view){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", inputEmail.getText().toString().trim());
        editor.putString("password", inputPassword.getText().toString().trim());
        editor.apply();

        Toast.makeText(this, "Email is saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Function for Hashing using SQLite
     * @param password
     * @return BigInteger
     * @throws NoSuchAlgorithmException
     */
    private String hashPassword (String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes(), 0, password.length());
        return new BigInteger(1,messageDigest.digest()).toString(16); //heximal value
    }

    /**
     * Method to clear all input EditText
     */
    private void emptyInputEditText(){
        inputEmail.setText("");
        inputPassword.setText("");
    }

    //displays back arrow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
