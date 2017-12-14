package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CustomerOrderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_list);

        SharedPreferences mySettings = getSharedPreferences(RegistrationActivity.PREFERENCES_FILE_NAME, 0);
        String userName = mySettings.getString(RegistrationActivity.USERNAME, null);
    }

    public void onPlaceAnOrderPressed(View view) {
        Intent i = new Intent(this, PlaceAnOrderActivity.class);
        startActivity(i);
    }

    public void onLogoutPressed(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
