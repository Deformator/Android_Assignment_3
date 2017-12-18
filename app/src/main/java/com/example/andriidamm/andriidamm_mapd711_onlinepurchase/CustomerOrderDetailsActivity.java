package com.example.andriidamm.andriidamm_mapd711_onlinepurchase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.CustomerOrderListAdapter;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.DataBaseHelper;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Order;

/**
 * This activity displays the order details to a customer.
 */
public class CustomerOrderDetailsActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_details);

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
        TextView tvOrderStatus = findViewById(R.id.tv_orderStatus);
        Button cancelOrderButton = findViewById(R.id.button_cancel_order);

        tvOrderId.setText(String.valueOf(order.getOrderId()));
        tvCustomerId.setText(order.getCustomerId());
        tvProductIds.setText(order.getProductIds());
        tvEmployeeId.setText(TextUtils.equals(order.getEmployeeId(), null) ? "Not Assigned" : order.getEmployeeId());
        tvOrderDate.setText(order.getOrderDate());
        tvOrderPrice.setText(String.valueOf(order.getPrice()));
        tvOrderStatus.setText(order.getStatus());

        if (TextUtils.equals(order.getStatus(), Order.OrderStatus.PROCESSED.toString())) {
            cancelOrderButton.setEnabled(false);
        }
    }

    public void onCancelOrderClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.order_cancellation_confirmation_message)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Delete order entry from database.
                        dataBaseHelper.deleteOrderById(order.getOrderId());
                        Toast.makeText(CustomerOrderDetailsActivity.this, "Order deleted successfully !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CustomerOrderDetailsActivity.this, CustomerOrderListActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do nothing
                    }
                });

        builder.create().show();
    }

    public void onBackClicked(View view) {
        finish();
    }
}
