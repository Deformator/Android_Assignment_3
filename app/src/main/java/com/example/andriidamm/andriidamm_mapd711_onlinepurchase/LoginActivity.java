package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataBaseHelper db = new DataBaseHelper(this);
    }

    public void onRegistrationPressed(View view) {
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);
    }

    public void onLoginPressed(View view) {
        Intent i = new Intent(this, CustomerOrderListActivity.class);
        startActivity(i);
    }
}
