package com.example.h3t_project.adapter;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SellerProductAdapter extends RecyclerView.Adapter<SellerProductAdapter.ViewHolder> {

    List<Product> products;

    public SellerProductAdapter(List<Product> products){
        this.products = products;
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
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");

        holder.txtNameProduct.setText(products.get(position).getName());
        holder.imageProduct.setImageResource(products.get(position).getImage_id());

        String string = "Giá gốc : "+ (decimalFormat.format(products.get(position).getOrigin_price())) + " đ";
        StrikethroughSpan STRIKE_THROUGH_SPAN = new StrikethroughSpan();
        holder.txtOriginPriceProduct.setText(string, TextView.BufferType.SPANNABLE);
        Spannable spannable = (Spannable) holder.txtOriginPriceProduct.getText();
        spannable.setSpan(STRIKE_THROUGH_SPAN, 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.txtPriceProduct.setText("Giá : "+(decimalFormat.format(products.get(position).getSell_price())) + " đ");
        holder.txtQuantity.setText("Số lượng : "+products.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView txtNameProduct, txtOriginPriceProduct, txtPriceProduct, txtQuantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameProduct =itemView.findViewById(R.id.txtNameProduct);
            txtOriginPriceProduct =itemView.findViewById(R.id.txtOriginPrice);
            txtPriceProduct =itemView.findViewById(R.id.txtPriceProduct);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            imageProduct =itemView.findViewById(R.id.imageProduct);
        }
    }
}
