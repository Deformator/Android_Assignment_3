package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.ClerkOrderListAdapter;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class ClerkOrderListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ClerkOrderListAdapter adapter;

    List<OrderModel> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_order_list);

        SharedPreferences mySettings = getSharedPreferences(RegistrationActivity.PREFERENCES_FILE_NAME, 0);
        String userName = mySettings.getString(RegistrationActivity.USERNAME, null);

        orders = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewOrdersClerk);
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
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));
        orders.add(new OrderModel(3, 300));


        adapter = new ClerkOrderListAdapter(this, orders);
        recyclerView.setAdapter(adapter);
    }

    public void onLogoutPressed(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
