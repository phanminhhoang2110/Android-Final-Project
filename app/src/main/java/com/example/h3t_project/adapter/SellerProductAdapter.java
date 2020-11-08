package com.example.h3t_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.SellerProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SellerProductAdapter extends RecyclerView.Adapter<SellerProductAdapter.ViewHolder> {

    ArrayList<SellerProduct> sellerProducts;

    public SellerProductAdapter(ArrayList<SellerProduct> sellerProducts){
        this.sellerProducts = sellerProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_seller_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNameProduct.setText(sellerProducts.get(position).getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceProduct.setText("Giá : "+ decimalFormat.format(sellerProducts.get(position).getPrice())+ " vnd");
        holder.txtQuantity.setText("Số lượng : "+ sellerProducts.get(position).getQuantity());
        holder.imageProduct.setImageResource(sellerProducts.get(position).getProductImg());
    }

    @Override
    public int getItemCount() {
        return sellerProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView txtNameProduct, txtPriceProduct, txtQuantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameProduct =itemView.findViewById(R.id.txtNameProduct);
            txtPriceProduct =itemView.findViewById(R.id.txtPriceProduct);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            imageProduct =itemView.findViewById(R.id.imageProduct);
        }
    }
}
