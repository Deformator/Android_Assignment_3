package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.CustomerModel;

public class RegistrationActivity extends AppCompatActivity {

    public static final String PREFERENCES_FILE_NAME = "MyAppPreferences";
    static String USERNAME = "USERNAME";

    String userName ;
    String password;
    String firstName;
    String lastName;
    String city;
    String postalCode;

    DataBaseHelper db;
    CustomerModel customer = new CustomerModel(userName, password, firstName, lastName, city, postalCode);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DataBaseHelper(this);

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



        if (userName.length() == 0 || password.length() == 0 || firstName.length() == 0 || lastName.length() == 0 || city.length() == 0 || postalCode.length() == 0) {
            Toast.makeText(getApplicationContext(), "All fields are required",
                    Toast.LENGTH_LONG).show();

        } else {


           if (db.isUserExist(userName)) {
               Toast.makeText(getApplicationContext(), "Customer name is already exist, please chose another username",
                       Toast.LENGTH_LONG).show();
           } else {
               customer.setUserName(userName);
               customer.setPassword(password);
               customer.setFirstName(firstName);
               customer.setLastName(lastName);
               customer.setCity(city);
               customer.setPostalCode(postalCode);

               db.addCustomer(customer);

               SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME,0);
               SharedPreferences.Editor myEditor = settingsFile.edit();
               myEditor.putString(USERNAME, userName);
               myEditor.apply();

               Intent i = new Intent(this, CustomerOrderListActivity.class);
               startActivity(i);
           }


        }
    }
}
