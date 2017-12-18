package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.CustomerOrderListAdapter;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Order;

import static com.example.andriidamm.andriidamm_mapd711_onlinepurchase.RegistrationActivity.PREFERENCES_FILE_NAME;

/**
 * This activity displays the order details to a clerk.
 */
public class ClerkOrderDetailsActivity extends AppCompatActivity {

    private Spinner spinnerOrderStatus;

    private DataBaseHelper dataBaseHelper;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_order_details);

        int orderId = getIntent().getIntExtra(CustomerOrderListAdapter.KEY_ORDER_ID, -1);
        dataBaseHelper = new DataBaseHelper(this);
        order = dataBaseHelper.getOrderDetailsById(orderId);

        init();
    }

    /**
     * Initialize and bind all views.
     */
    private void init() {
        TextView tvOrderId = findViewById(R.id.tv_orderId);
        TextView tvCustomerId = findViewById(R.id.tv_customerId);
        TextView tvProductIds = findViewById(R.id.tv_productIds);
        TextView tvEmployeeId = findViewById(R.id.tv_employeeId);
        TextView tvOrderDate = findViewById(R.id.tv_orderDate);
        TextView tvOrderPrice = findViewById(R.id.tv_orderPrice);
        spinnerOrderStatus = findViewById(R.id.spinner_orderStatus);

        tvOrderId.setText(String.valueOf(order.getOrderId()));
        tvCustomerId.setText(order.getCustomerId());
        tvProductIds.setText(order.getProductIds());
        tvEmployeeId.setText(TextUtils.equals(order.getEmployeeId(), null) ? "Not Assigned" : order.getEmployeeId());
        tvOrderDate.setText(order.getOrderDate());
        tvOrderPrice.setText(String.valueOf(order.getPrice()));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{Order.OrderStatus.PENDING.toString(), Order.OrderStatus.PROCESSED.toString()});
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinnerOrderStatus.setAdapter(adapter);
        if (TextUtils.equals(Order.OrderStatus.PENDING.toString(), order.getStatus())) {
            spinnerOrderStatus.setSelection(0);
        } else {
            spinnerOrderStatus.setSelection(1);
        }
    }

    public void onUpdateClicked(View view) {
        if (TextUtils.equals((String)spinnerOrderStatus.getSelectedItem(), order.getStatus())) {
            Toast.makeText(this, "Nothing to update !", Toast.LENGTH_LONG).show();
        } else {
            // Get clerk Id from shared preferences.
            SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
            String clerkId = settingsFile.getString(LoginActivity.USERNAME_CLERK, null);
            dataBaseHelper.updateOrderDetailsByClerk(order.getOrderId(), clerkId, (String)spinnerOrderStatus.getSelectedItem());

            Intent intent = new Intent(this, ClerkOrderListActivity.class);
            startActivity(intent);
        }
    }

    public void onCancelClicked(View view) {
        finish();
    }
}
