package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.CustomerOrderListAdapter;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Order;

import java.util.List;

/**
 * This activity displays the list of orders placed by the customer.
 */
public class CustomerOrderListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomerOrderListAdapter adapter;
    SharedPreferences mySettings;
    DataBaseHelper dataBaseHelper;

    List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_list);

        dataBaseHelper = new DataBaseHelper(this);
        mySettings = getSharedPreferences(RegistrationActivity.PREFERENCES_FILE_NAME, MODE_PRIVATE);

        recyclerView = findViewById(R.id.recyclerViewOrdersCustomer);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orders = dataBaseHelper.getOrdersByCustomerId(mySettings.getString(LoginActivity.USERNAME_CUSTOMER, null));

        adapter = new CustomerOrderListAdapter(this, orders);
        recyclerView.setAdapter(adapter);
    }

    public void onPlaceAnOrderPressed(View view) {
        Intent i = new Intent(this, PlaceAnOrderActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            SharedPreferences.Editor editor = mySettings.edit();
            editor.remove(LoginActivity.USERNAME_CUSTOMER);
            editor.apply();
            finish();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        return true;
    }
}
