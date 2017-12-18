package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.ClerkOrderDetailsActivity;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.R;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Order;

import java.util.List;

import static com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls.CustomerOrderListAdapter.KEY_ORDER_ID;

/**
 * Adapter class for the list of orders displayed to the clerk.
 */

public class ClerkOrderListAdapter extends RecyclerView.Adapter<ClerkOrderListAdapter.OrderViewHolder> {

    private Context mCtx;
    private List<Order> orderList;

    public ClerkOrderListAdapter(Context mCtx, List<Order> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
    }

    @Override
    public ClerkOrderListAdapter.OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.clerk_cell, parent, false);

        return new ClerkOrderListAdapter.OrderViewHolder(view);
    }


    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.textViewOrderNumber.setText(String.valueOf(order.getOrderId()));
        holder.textViewPriceNumber.setText(String.valueOf(order.getPrice()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView textViewOrder, textViewOrderNumber, textViewPrice, textViewPriceNumber;
        Button buttonLookOrder;

        public OrderViewHolder(View itemView) {
            super(itemView);

            textViewOrder = itemView.findViewById(R.id.textViewOrder);
            textViewOrderNumber = itemView.findViewById(R.id.textViewOrderNumber);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewPriceNumber = itemView.findViewById(R.id.textViewPriceNumber);
            buttonLookOrder = itemView.findViewById(R.id.buttonLookOrder);

            buttonLookOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mCtx, ClerkOrderDetailsActivity.class);
                    intent.putExtra(KEY_ORDER_ID, orderList.get(ClerkOrderListAdapter.OrderViewHolder.this.getLayoutPosition()).getOrderId());
                    mCtx.startActivity(intent);
                }
            });
        }
    }
}
