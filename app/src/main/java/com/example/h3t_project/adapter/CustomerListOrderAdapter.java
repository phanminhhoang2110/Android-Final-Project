package com.example.h3t_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.Order;

import java.text.DecimalFormat;
import java.util.List;

public class CustomerListOrderAdapter extends RecyclerView.Adapter<CustomerListOrderAdapter.ViewHolder> {

    List<Order> orders;
    Context context;

    public CustomerListOrderAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerListOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_list_order, parent, false);
        return new CustomerListOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListOrderAdapter.ViewHolder holder, int position) {
        holder.imageViewCutomerListOrder.setImageResource(orders.get(position).getImageId());
        holder.productNameCustomerListOrder.setText(orders.get(position).getProductName());
//        holder.productQuantityCustomerListOrder.setText(orders.get(position).getQuantity());

//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
//        int sumOneProduct = orders.get(position).getSellPrice()*orders.get(position).getQuantity();
//        holder.priceCustomerOrder.setText(decimalFormat.format(sumOneProduct) + "đ");
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCutomerListOrder;
        TextView productNameCustomerListOrder;
        TextView productQuantityCustomerListOrder;
        TextView priceCustomerOrder;
        TextView totalPriceCustomerOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCutomerListOrder = itemView.findViewById(R.id.image_cutomer_list_order);
            productNameCustomerListOrder = itemView.findViewById(R.id.product_name_customer_list_order);
            productQuantityCustomerListOrder = itemView.findViewById(R.id.quantity_customer_list_order);
            priceCustomerOrder = itemView.findViewById(R.id.price_customer_list_order);
            totalPriceCustomerOrder = itemView.findViewById(R.id.total_price_order_customer);
        }
    }
}
