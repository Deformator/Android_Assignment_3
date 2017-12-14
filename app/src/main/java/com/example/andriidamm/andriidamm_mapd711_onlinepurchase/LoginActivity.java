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
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.ClerkModel;

public class LoginActivity extends AppCompatActivity {
    DataBaseHelper db;

    public static final String PREFERENCES_FILE_NAME = "MyAppPreferences";
    static String USERNAME = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DataBaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ClerkModel clerk = new ClerkModel("Alfred", "Batman", "Alfred", "Pennyworth");
//        db.addClerk(clerk);


    }

    public void onRegistrationPressed(View view) {

        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);

    }

    public void onLoginPressed(View view) {
        SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME,0);
        SharedPreferences.Editor myEditor = settingsFile.edit();

        RadioButton rb = (RadioButton) findViewById(R.id.buttonCustomer);
        String userName = ((EditText) findViewById(R.id.editTextUserName)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

        if (rb.isChecked()){
            if (db.isCustomerCredsCorrect(userName, password)){
                myEditor.putString(USERNAME, userName);
                myEditor.apply();
                Intent i = new Intent(this, CustomerOrderListActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Please, provide valid credentials",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            if (db.isClerkCredsCorrect(userName, password)){
                Intent i = new Intent(this, ClerkOrderListActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Please, provide valid credentials",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
