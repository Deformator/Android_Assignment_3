package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.ClerkOrderListAdapter;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity displays the list of orders to a clerk.
 */
public class ClerkOrderListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ClerkOrderListAdapter adapter;

    List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_order_list);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        orders = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewOrdersClerk);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orders = dataBaseHelper.getAllOrders();
        adapter = new ClerkOrderListAdapter(this, orders);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            SharedPreferences mySettings = getSharedPreferences(RegistrationActivity.PREFERENCES_FILE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = mySettings.edit();
            editor.remove(LoginActivity.USERNAME_CLERK);
            editor.apply();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        return true;

    }
}
