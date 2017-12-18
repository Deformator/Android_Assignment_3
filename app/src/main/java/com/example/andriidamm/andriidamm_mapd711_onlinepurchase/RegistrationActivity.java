package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Clerk;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Customer;

public class RegistrationActivity extends AppCompatActivity {

    public static final String PREFERENCES_FILE_NAME = "MyAppPreferences";

    byte userType;

    String userName;
    String password;
    String firstName;
    String lastName;
    String city;
    String postalCode;

    DataBaseHelper db;

    TextView tvRegistrationTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new DataBaseHelper(this);

        tvRegistrationTitle = findViewById(R.id.textViewRegistration);
        userType = getIntent().getByteExtra(LoginActivity.USER_TYPE, (byte) 0);

        if (userType == LoginActivity.USER_CUSTOMER) {
            tvRegistrationTitle.setText(R.string.cust_registration);
            findViewById(R.id.row_city).setVisibility(View.VISIBLE);
            findViewById(R.id.row_postal_code).setVisibility(View.VISIBLE);
        } else {
            tvRegistrationTitle.setText(R.string.clerk_registration);
            findViewById(R.id.row_city).setVisibility(View.GONE);
            findViewById(R.id.row_postal_code).setVisibility(View.GONE);
        }
    }

    public void onCancelPressed(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void onSubmitPressed(View view) {

        userName = ((EditText) findViewById(R.id.editTextUserName)).getText().toString();
        password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
        firstName = ((EditText) findViewById(R.id.editTextFirstName)).getText().toString();
        lastName = ((EditText) findViewById(R.id.editTextLastName)).getText().toString();
        city = ((EditText) findViewById(R.id.editTextCity)).getText().toString();
        postalCode = ((EditText) findViewById(R.id.editTextPostalCode)).getText().toString();

        if (userName.length() == 0 || password.length() == 0 || firstName.length() == 0 || lastName.length() == 0) {
            Toast.makeText(getApplicationContext(), "All fields are required",
                    Toast.LENGTH_LONG).show();
        } else {

            if (userType == LoginActivity.USER_CUSTOMER) {

                if (city.length() == 0 || postalCode.length() == 0) {
                    Toast.makeText(getApplicationContext(), "All fields are required",
                            Toast.LENGTH_LONG).show();
                }

                if (db.isCustomerExisting(userName)) {
                    Toast.makeText(getApplicationContext(), "Customer name already exists, please chose another username",
                            Toast.LENGTH_LONG).show();
                } else {
                    Customer customer = new Customer(userName, password, firstName, lastName, city, postalCode);
                    db.addCustomer(customer);

                    SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
                    SharedPreferences.Editor myEditor = settingsFile.edit();
                    myEditor.putString(LoginActivity.USERNAME_CUSTOMER, userName);
                    myEditor.putInt(LoginActivity.USER_TYPE, LoginActivity.USER_CUSTOMER);
                    myEditor.apply();

                    Intent i = new Intent(this, CustomerOrderListActivity.class);
                    startActivity(i);
                }
            } else {
                if (db.isClerkExisting(userName)) {
                    Toast.makeText(getApplicationContext(), "Clerk name already exists, please chose another username",
                            Toast.LENGTH_LONG).show();
                } else {
                    Clerk clerk = new Clerk(userName, password, firstName, lastName);
                    db.addClerk(clerk);

                    SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
                    SharedPreferences.Editor myEditor = settingsFile.edit();
                    myEditor.putString(LoginActivity.USERNAME_CLERK, userName);
                    myEditor.putInt(LoginActivity.USER_TYPE, LoginActivity.USER_CLERK);
                    myEditor.apply();

                    Intent i = new Intent(this, ClerkOrderListActivity.class);
                    startActivity(i);
                }
            }

        }
    }
}
