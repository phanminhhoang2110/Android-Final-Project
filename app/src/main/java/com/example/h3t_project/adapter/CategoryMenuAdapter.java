package com.example.h3t_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.activity.ActivityCustomerViewProduct;
import com.example.h3t_project.model.CategoryItem;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMenuAdapter extends RecyclerView.Adapter<CategoryMenuAdapter.ViewHolder> {
  ArrayList<CategoryItem> categoryItems = new ArrayList<>();
  Context context;

  public CategoryMenuAdapter(ArrayList<CategoryItem> categoryItems,Context context) {
    this.categoryItems = categoryItems;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_category_homepage, parent, false);
    return new CategoryMenuAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
      holder.textView.setText(categoryItems.get(position).getName());
      holder.imageView.setImageResource(getResId(categoryItems.get(position).getImage(),R.drawable.class));
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click_animation));
          Intent intent = new Intent(context, ActivityCustomerViewProduct.class);
          intent.putExtra("categoryId", categoryItems.get(position).getId());
          intent.putExtra("categoryName", categoryItems.get(position).getName());
          context.startActivity(intent);
        }
      });
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
