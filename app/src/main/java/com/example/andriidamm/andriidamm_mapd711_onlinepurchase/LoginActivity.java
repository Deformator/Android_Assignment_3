package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DataBaseHelper(this);
    }

    public void onRegistrationPressed(View view) {

        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);

    }

    public void onLoginPressed(View view) {

        String userName = ((EditText) findViewById(R.id.editTextUserName)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

        if (db.isCustomerCredsCorrect(userName, password)){
            Intent i = new Intent(this, CustomerOrderListActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Please, provide valid credentials",
                    Toast.LENGTH_LONG).show();
        }

    }
}
