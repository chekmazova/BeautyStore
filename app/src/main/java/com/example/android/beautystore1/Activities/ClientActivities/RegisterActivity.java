package com.example.android.beautystore1.Activities.ClientActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.android.beautystore1.Database.DatabaseHelper;
import com.example.android.beautystore1.Helpers.InputValidation;
import com.example.android.beautystore1.Models.Customer;
import com.example.android.beautystore1.R;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends MainActivity {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutLastName;
    private TextInputLayout textInputLayoutPhoneNumber;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditFirstName;
    private TextInputEditText textInputEditLastName;
    private TextInputEditText textInputEditPhoneNumber;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private CheckBox checkbox_subscribe;
    private AppCompatButton appCompatButtonRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Customer mCustomer;

    String mFirstName;
    String mLastName;
    String mPhoneNumber;
    String mEmail;
    String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Registration");

        Button button = findViewById(R.id.appCompatButtonRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();
        initObjects();

        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToSQLite();
            }
        });
    }

    /**
     * Method to initialize views
     */
    private void initViews(){
        nestedScrollView = findViewById(R.id.nestedScrollView);

        textInputLayoutFirstName = findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutLastName = findViewById(R.id.textInputLayoutLastName);
        textInputLayoutPhoneNumber = findViewById(R.id.textInputLayoutPhone);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        textInputEditFirstName = findViewById(R.id.textInputEditTextFirstName);
        textInputEditLastName = findViewById(R.id.textInputEditTextLAstName);
        textInputEditPhoneNumber = findViewById(R.id.textInputEditTextPhone);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);

        checkbox_subscribe = findViewById(R.id.checkboxSubscribe);
        appCompatButtonRegister = findViewById(R.id.appCompatButtonRegister);
    }

    /**
     * Method to initialize Objects
     */
    private void initObjects(){
        inputValidation = new InputValidation(this);
        databaseHelper = new DatabaseHelper(this);
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditFirstName, textInputLayoutFirstName, getString(R.string.error_message_name))){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditLastName, textInputLayoutLastName, getString(R.string.error_message_name))){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if (!databaseHelper.checkEmailExist(textInputEditTextEmail.getText().toString().trim())){
            mFirstName = textInputEditFirstName.getText().toString().trim();
            mLastName = textInputEditLastName.getText().toString().trim();
            mPhoneNumber = textInputEditPhoneNumber.getText().toString().trim();
            mEmail = textInputEditTextEmail.getText().toString().trim();
            mPassword = textInputEditTextPassword.getText().toString().trim();

            //Function to secure password with SQLite
            String hashPass = null;
            try {
                hashPass = hashPassword(mPassword);
            } catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
            //Function to secure password with SQLite

            mCustomer = new Customer(mFirstName, mLastName, mPhoneNumber, mEmail, hashPass);
            databaseHelper.Open();
            databaseHelper.insert_customer(mCustomer);
            databaseHelper.Close();

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();

        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }

        Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        intentLogin.putExtra("EMAIL", mEmail);
        startActivity(intentLogin);
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
     * Method to clear the data from EditText fields
     */
    private void emptyInputEditText(){
        textInputEditFirstName.setText("");
        textInputEditLastName.setText("");
        textInputEditPhoneNumber.setText("");
        textInputEditTextEmail.setText("");
        textInputEditTextPassword.setText("");
    }

    /**
     * Method to set the arrow back
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
