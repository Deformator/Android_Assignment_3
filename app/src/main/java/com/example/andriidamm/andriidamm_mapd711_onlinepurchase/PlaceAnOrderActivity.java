package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.ProductListAdapter;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Order;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Product;

import java.util.ArrayList;
import java.util.List;

import static com.example.andriidamm.andriidamm_mapd711_onlinepurchase.RegistrationActivity.PREFERENCES_FILE_NAME;

/**
 * This activity shows the list of products to the customer to place an order.
 */
public class PlaceAnOrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductListAdapter adapter;
    DataBaseHelper dataBaseHelper;
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_an_order);

        dataBaseHelper = new DataBaseHelper(this);

        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        products = dataBaseHelper.getProducts();
        adapter = new ProductListAdapter(this, products);
        recyclerView.setAdapter(adapter);
    }

    public void onSubmitPressed(View view) {

        int totalOrderPrice = 0;

        // Find all ordered product ids and calculate total order price.
        List<Integer> orderedProductIds = new ArrayList<>();
        for (Product product: products) {
            if (product.getQuantity() > 0) {
                totalOrderPrice += (product.getPrice() * product.getQuantity());
                orderedProductIds.add(product.getProductId());
            }
        }

        if (orderedProductIds.size() == 0) {
            Toast.makeText(this, "Please select atleast one product", Toast.LENGTH_LONG).show();
            return;
        }

        // Get customer user Id from shared preferences.
        SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        String customerId = settingsFile.getString(LoginActivity.USERNAME_CUSTOMER, null);

        // Create a new order
        Order newOrder = new Order(customerId, Utils.convertArrayListToString(orderedProductIds)
                , null, totalOrderPrice, Order.OrderStatus.PENDING.toString());
        dataBaseHelper.addOrder(newOrder);

        Toast.makeText(this, "Order placed successfully", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, CustomerOrderListActivity.class);
        startActivity(i);
    }

    public void onCancelPressed(View view) {
        Intent i = new Intent(this, CustomerOrderListActivity.class);
        startActivity(i);
    }
}
