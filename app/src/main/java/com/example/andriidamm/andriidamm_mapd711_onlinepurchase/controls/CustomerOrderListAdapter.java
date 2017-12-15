package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.R;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.OrderModel;

import java.util.List;

/**
 * Created by andriidamm on 2017-12-14.
 */

public class CustomerOrderListAdapter extends RecyclerView.Adapter<CustomerOrderListAdapter.OrderViewHolder>{

    private Context mCtx;
    private List<OrderModel> orderList;

    public CustomerOrderListAdapter(Context mCtx, List<OrderModel> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.customer_cell, parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderModel order = orderList.get(position);

        holder.textViewOrderNumber.setText(String.valueOf(order.getOrderId()));
        holder.textViewPriceNumber.setText(String.valueOf(order.getPrice()));



    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView textViewOrder, textViewOrderNumber, textViewPrice, textViewPriceNumber;
        Button buttonEditOrder;

        public OrderViewHolder(View itemView) {
            super(itemView);

            textViewOrder = itemView.findViewById(R.id.textViewOrder);
            textViewOrderNumber = itemView.findViewById(R.id.textViewOrderNumber);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewPriceNumber = itemView.findViewById(R.id.textViewPriceNumber);
            buttonEditOrder = itemView.findViewById(R.id.buttonEditOrder);
        }
    }

}
