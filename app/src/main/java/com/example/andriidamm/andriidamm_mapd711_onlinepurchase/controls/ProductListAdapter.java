package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.R;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Product;

import java.util.List;

/**
 * This is the adapter class for showing the list of products to customer.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<Product> productList;

    public ProductListAdapter(Context mCtx, List<Product> productList) {
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
        Product product = productList.get(position);

        holder.textViewProductName.setText(product.getProductName());
        holder.textViewCategory.setText(product.getCategory());
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        Resources res = mCtx.getResources();
        String mDrawableName = product.getImage();
        int resID = res.getIdentifier(mDrawableName , "drawable", mCtx.getPackageName());
        Drawable drawable = ContextCompat.getDrawable(mCtx, resID);
        holder.imageViewProduct.setImageDrawable(drawable );

        holder.spinnerQuantity.setSelection(product.getQuantity());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProductName, textViewCategory, textViewPrice;
        ImageView imageViewProduct;
        Spinner spinnerQuantity;

        ProductViewHolder(final View itemView) {
            super(itemView);

            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);

            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

            spinnerQuantity = itemView.findViewById(R.id.spinnerTextQuantity);
            Integer[] quantities = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(mCtx, android.R.layout.simple_spinner_item,
                    quantities);
            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

            spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    // Store the modified quantity value in the products list.
                     adapterView.getItemAtPosition(position);
                    productList.get(ProductViewHolder.this.getLayoutPosition())
                            .setQuantity((int) adapterView.getItemAtPosition(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spinnerQuantity.setAdapter(adapter);
        }
    }
}
