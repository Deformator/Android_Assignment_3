package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.R;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.ProductModel;

import java.util.List;

/**
 * Created by andriidamm on 2017-12-15.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<ProductModel> productList;

    public ProductListAdapter(Context mCtx, List<ProductModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.product_cell, parent, false);

        return new ProductListAdapter.ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ProductListAdapter.ProductViewHolder holder, int position) {
        ProductModel product = productList.get(position);

        holder.textViewProductName.setText(String.valueOf(product.getProductName()));
        holder.textViewCategory.setText(String.valueOf(product.getCategory()));
        holder.textViewPrice.setText(String.valueOf(String.valueOf(product.getPrice())));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProductName, textViewCategory, textViewPrice;
        EditText editTextQuantity;
        ImageView imageViewProduct;

        public ProductViewHolder(View itemView) {
            super(itemView);

            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);


            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            editTextQuantity = itemView.findViewById(R.id.editTextQuantity);

        }
    }
}
