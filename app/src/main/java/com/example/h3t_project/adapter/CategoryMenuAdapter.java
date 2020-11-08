package com.example.h3t_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.CategoryItem;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMenuAdapter extends RecyclerView.Adapter<CategoryMenuAdapter.ViewHolder> {
  ArrayList<CategoryItem> categoryItems = new ArrayList<>();

  public CategoryMenuAdapter(ArrayList<CategoryItem> categoryItems) {
    this.categoryItems = categoryItems;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_category_homepage, parent, false);
    return new CategoryMenuAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.textView.setText(categoryItems.get(position).getName());
      holder.imageView.setImageResource(categoryItems.get(position).getImage());
  }

  @Override
  public int getItemCount() {
    return categoryItems.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    ImageView imageView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      this.textView = itemView.findViewById(R.id.nameCategory);
      this.imageView = itemView.findViewById(R.id.imageCategory);
    }
  }
}
