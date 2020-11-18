package com.example.h3t_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.Order;

import java.text.DecimalFormat;
import java.util.List;

public class ListOrderSellerAdapter extends RecyclerView.Adapter<ListOrderSellerAdapter.ViewHolder> {
  List<Order> orders;
  Context context;

  public ListOrderSellerAdapter(List<Order> orders, Context context) {
    this.context = context;
    this.orders = orders;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seller_list_order, parent, false);
    return new ListOrderSellerAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
    DecimalFormat decimalFormat = new DecimalFormat("###,####,###");
    String aa = orders.get(position).getProductName();
    holder.item_product_name.setText(orders.get(position).getProductName());
    holder.item_quantity_product.setText(decimalFormat.format(orders.get(position).getQuantity()));
    holder.item_image_product.setImageResource(orders.get(position).getProduct().getImage_id());
    holder.viewDetail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          int orderId = orders.get(position).getOrderId();

      }
    });
  }

  @Override
  public int getItemCount() {
    return orders.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView item_product_name;
    TextView item_quantity_product;
    ImageView item_image_product;
    Button viewDetail;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      item_product_name = itemView.findViewById(R.id.product_seller_list_order);
      item_quantity_product = itemView.findViewById(R.id.quantity_seller_list_order);
      item_image_product = itemView.findViewById(R.id.image_seller_list_order);
      viewDetail = itemView.findViewById(R.id.viewDetail);
    }
  }
}
