package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Product;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final byte USER_CUSTOMER = 1;
    public static final byte USER_CLERK = 2;

    public static final String PREFERENCES_FILE_NAME = "MyAppPreferences";
    public static final String USER_TYPE = "USER_TYPE";
    public static final String USERNAME_CUSTOMER = "USERNAME_CUSTOMER";
    public static final String USERNAME_CLERK = "USERNAME_CLERK";

    private DataBaseHelper db;
    private RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = new DataBaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rb = findViewById(R.id.buttonCustomer);

        // Add the list of products in the database if this is the first application launch and
        // no products exist in the db currently.
        List<Product> products = db.getProducts();
        if (products == null || products.size() == 0) {
            addProducts();
        }
    }

    public void onRegistrationPressed(View view) {
        Intent i = new Intent(this, RegistrationActivity.class);
        if (rb.isChecked()) {
            i.putExtra(USER_TYPE, USER_CUSTOMER);
        } else {
            i.putExtra(USER_TYPE, USER_CLERK);
        }
        startActivity(i);
    }

    public void onLoginPressed(View view) {
        SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME,0);
        SharedPreferences.Editor myEditor = settingsFile.edit();

        String userName = ((EditText) findViewById(R.id.editTextUserName)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

        if (rb.isChecked()){
            if (db.isCustomerCredsCorrect(userName, password)){
                myEditor.putString(USERNAME_CUSTOMER, userName);
                myEditor.putInt(USER_TYPE, USER_CUSTOMER);
                myEditor.apply();
                Intent i = new Intent(this, CustomerOrderListActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), R.string.invalid_credentials_message,
                        Toast.LENGTH_LONG).show();
            }
        } else {
            if (db.isClerkCredsCorrect(userName, password)){
                myEditor.putString(USERNAME_CLERK, userName);
                myEditor.putInt(USER_TYPE, USER_CLERK);
                myEditor.apply();
                Intent i = new Intent(this, ClerkOrderListActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), R.string.invalid_credentials_message,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Add the products in the database.
     */
    private void addProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("apricot", "Apricot", "fruit", 50));
        products.add(new Product("banana", "Banana", "fruit", 30));
        products.add(new Product("broccoli", "Broccoli", "vegetables", 20));
        products.add(new Product("cabbage", "Cabbage", "vegetables", 10));
        products.add(new Product("carrot", "Carrot", "vegetables", 150));
        products.add(new Product("cherry", "Cherry", "fruit", 80));
        products.add(new Product("kiwi", "Kiwi", "fruit", 120));
        products.add(new Product("onion", "Onion", "vegetables", 35));
        products.add(new Product("potato", "Potato", "vegetables", 12));
        products.add(new Product("strawberry", "Strawberry", "fruit", 75));

        db.addProducts(products);
    }
}
