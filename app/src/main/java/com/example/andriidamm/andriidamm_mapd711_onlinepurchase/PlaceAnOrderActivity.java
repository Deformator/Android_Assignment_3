package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.ProductListAdapter;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class PlaceAnOrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductListAdapter adapter;

    List<ProductModel> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_an_order);


        products = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));
        products.add(new ProductModel("some Image", "Carrot", "vegetables", 50, 0));

        adapter = new ProductListAdapter(this, products);
        recyclerView.setAdapter(adapter);
    }

    public void onSubmitPressed(View view) {
        Intent i = new Intent(this, CustomerOrderListActivity.class);
        startActivity(i);
    }

    public void onCancelPressed(View view) {
        Intent i = new Intent(this, CustomerOrderListActivity.class);
        startActivity(i);
    }
}
