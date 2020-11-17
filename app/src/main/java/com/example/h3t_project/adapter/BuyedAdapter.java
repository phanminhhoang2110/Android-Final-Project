package com.example.h3t_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.Product;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BuyedAdapter extends RecyclerView.Adapter<BuyedAdapter.ViewHolder> {

  List<Product> products;

  public BuyedAdapter(ArrayList<Product> products) {
    this.products = products;
  }

  public static int getResId(String resName, Class<?> c) {
    try {
      Field idField = c.getDeclaredField(resName);
      return idField.getInt(idField);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyed_product_item, parent, false);
    return new BuyedAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
    holder.imageProduct.setImageResource(getResId(products.get(position).getLink_image(),R.drawable.class));
    holder.priceProduct.setText(decimalFormat.format(products.get(position).getSell_price()) + " đ");
    holder.nameProduct.setText(products.get(position).getName());
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageProduct;
    TextView nameProduct;
    TextView priceProduct;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      imageProduct = itemView.findViewById(R.id.imageBuyedProduct);
      nameProduct = itemView.findViewById(R.id.nameBuyedProduct);
      priceProduct = itemView.findViewById(R.id.priceBuyedProduct);
    }
  }
}
