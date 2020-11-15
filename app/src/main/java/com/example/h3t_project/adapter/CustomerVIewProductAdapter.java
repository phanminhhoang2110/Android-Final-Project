package com.example.h3t_project.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.activity.ActivityCustomerViewProduct;
import com.example.h3t_project.activity.ViewProductActivity;
import com.example.h3t_project.model.Product;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerVIewProductAdapter extends RecyclerView.Adapter<CustomerVIewProductAdapter.ViewHolder> {

    List<Product> products;
    Context context;

    public CustomerVIewProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerVIewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_view_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerVIewProductAdapter.ViewHolder holder, final int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");

        holder.item_price_customer_view_product.setText((decimalFormat.format(products.get(position).getSell_price())) + " đ");
        holder.item_name_customer_view_product.setText(products.get(position).getName());
        holder.item_image_customer_view_product.setImageResource(products.get(position).getImage_id());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewProductActivity.class);
                intent.putExtra("productId", products.get(position).getId());
                intent.putExtra("categoryId", products.get(position).getCategory_id());
                context.startActivity(intent);
            }
        });
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
