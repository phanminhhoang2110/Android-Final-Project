package com.example.h3t_project.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.Product;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CustomerVIewProductAdapter extends RecyclerView.Adapter<CustomerVIewProductAdapter.ViewHolder> {

    List<Product> products;

    public CustomerVIewProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public CustomerVIewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_view_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerVIewProductAdapter.ViewHolder holder, int position) {


        holder.item_price_customer_view_product.setText(String.valueOf(products.get(position).getSell_price()) + " đ");
        holder.item_name_customer_view_product.setText(products.get(position).getName());
        holder.item_image_customer_view_product.setImageResource(products.get(position).getImage_id());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_price_customer_view_product;
        TextView item_name_customer_view_product;
        ImageView item_image_customer_view_product;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image_customer_view_product = itemView .findViewById(R.id.item_image_customer_view_product);
            item_name_customer_view_product = itemView.findViewById(R.id.item_name_customer_view_product);
            item_price_customer_view_product = itemView.findViewById(R.id.item_price_customer_view_product);
        }
    }
}
