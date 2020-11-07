package com.example.h3t_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.MenuItemPersonal;

import java.util.ArrayList;

public class MenuPersonalRecyclerAdapter extends RecyclerView.Adapter<MenuPersonalRecyclerAdapter.ViewHolder> {

  public MenuPersonalRecyclerAdapter(ArrayList<MenuItemPersonal> itemMenus) {
    this.itemMenus = itemMenus;
  }

  ArrayList<MenuItemPersonal> itemMenus;

  @NonNull
  @Override
  public MenuPersonalRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_personal, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MenuPersonalRecyclerAdapter.ViewHolder holder, int position) {
    holder.textView.setText(itemMenus.get(position).getItemName());
    holder.imageView.setImageResource(itemMenus.get(position).getIconMenu());
  }

  @Override
  public int getItemCount() {
    return itemMenus.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.titleMenu);
      imageView = itemView.findViewById(R.id.iconMenu);
    }
  }
}
