package com.example.h3t_project.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.h3t_project.R;
import com.example.h3t_project.activity.ActivityDetailOrder;
import com.example.h3t_project.activity.ViewProductActivity;
import com.example.h3t_project.fragment.LoginFragment;
import com.example.h3t_project.fragment.RegisterFragment;
import com.example.h3t_project.model.Order;

import java.util.List;

public class SellerListOrderAdapter extends RecyclerView.Adapter<SellerListOrderAdapter.DataViewHolder> {
    List<Order> orders;
    Context context;

    public SellerListOrderAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerListOrderAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_listorder_seller, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerListOrderAdapter.DataViewHolder holder, final int position) {
        holder.item_product_name.setText(orders.get(position).getProductName());
        holder.item_quantity_product.setText(orders.get(position).getQuantity());
        holder.item_image_product.setImageResource(orders.get(position).getProduct().getImage_id());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ActivityDetailOrder.class);
//                intent.putExtra("orderId", orders.get(position).getOrderId());
//                intent.putExtra("statusId", orders.get(position).getStatusId());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
    public static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView item_product_name;
        TextView item_quantity_product;
        ImageView item_image_product;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            item_product_name = itemView.findViewById(R.id.item_product_name);
            item_quantity_product = itemView.findViewById(R.id.item_quantity_product);
            item_image_product = itemView.findViewById(R.id.item_image_product);
        }
    }
}



