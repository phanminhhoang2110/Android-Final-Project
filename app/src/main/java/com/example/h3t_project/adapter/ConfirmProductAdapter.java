package com.example.h3t_project.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.common.ResourceFunction;
import com.example.h3t_project.model.ItemCartDetail;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ConfirmProductAdapter extends RecyclerView.Adapter<ConfirmProductAdapter.ViewHolder> {

  ArrayList<ItemCartDetail> itemCartDetails;

  public ConfirmProductAdapter(ArrayList<ItemCartDetail> itemCartDetails) {
    this.itemCartDetails = itemCartDetails;
  }

  @NonNull
  @Override
  public ConfirmProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyed_product_item, parent, false);
    return new ConfirmProductAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ConfirmProductAdapter.ViewHolder holder, int position) {
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
    holder.imageProduct.setImageResource(ResourceFunction.getResId(itemCartDetails.get(position).getImage(), R.drawable.class));
    holder.nameProduct.setText(itemCartDetails.get(position).getName());
    holder.priceProduct.setText(decimalFormat.format(itemCartDetails.get(position).getSellPrice())+ " đ");
  }

  @Override
  public int getItemCount() {
    return itemCartDetails.size();
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
