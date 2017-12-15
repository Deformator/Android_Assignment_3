package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.CustomerOrderListAdapter;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomerOrderListAdapter adapter;

    List<OrderModel> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_list);

        SharedPreferences mySettings = getSharedPreferences(RegistrationActivity.PREFERENCES_FILE_NAME, 0);
        String userName = mySettings.getString(RegistrationActivity.USERNAME, null);

        orders = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewOrdersCustomer);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orders.add(new OrderModel(1, 100));
        orders.add(new OrderModel(2, 200));
        orders.add(new OrderModel(1233, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));


        adapter = new CustomerOrderListAdapter(this, orders);
        recyclerView.setAdapter(adapter);
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
