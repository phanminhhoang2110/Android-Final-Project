package com.example.h3t_project.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.DetailProductItem;
import com.example.h3t_project.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MenuDetailProductAdapter extends RecyclerView.Adapter<MenuDetailProductAdapter.ViewHolder> {
  List<DetailProductItem> detailProductItems = new ArrayList<>();

  public MenuDetailProductAdapter(List<DetailProductItem> detailProductItems) {
    this.detailProductItems = detailProductItems;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_detail_product, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    if(position %2 == 1)
    {
      holder.itemView.setBackgroundColor(Color.parseColor("#F6F6F6"));
    }
    holder.nameTextView.setText(detailProductItems.get(position).getName());
    holder.contentTextView.setText(detailProductItems.get(position).getContent());
  }

  @Override
  public int getItemCount() {
    return detailProductItems.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    TextView contentTextView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      this.nameTextView = itemView.findViewById(R.id.detailNameProduct);
      this.contentTextView = itemView.findViewById(R.id.detailContentProduct);
    }
  }
}
