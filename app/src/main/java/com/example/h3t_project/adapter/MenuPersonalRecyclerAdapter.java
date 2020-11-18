package com.example.h3t_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.activity.BuyedProductActivity;
import com.example.h3t_project.activity.CustomerListOrderActivity;
import com.example.h3t_project.activity.DestinationActivity;
import com.example.h3t_project.activity.HomePageActivity;
import com.example.h3t_project.activity.LoginActivity;
import com.example.h3t_project.model.MenuItemPersonal;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.util.ArrayList;

public class MenuPersonalRecyclerAdapter extends RecyclerView.Adapter<MenuPersonalRecyclerAdapter.ViewHolder> {

  Context context;
  ArrayList<MenuItemPersonal> itemMenus;
  View view;
  Intent intent;

  public MenuPersonalRecyclerAdapter(ArrayList<MenuItemPersonal> itemMenus, Context context) {
    this.itemMenus = itemMenus;
    this.context = context;
  }

  @NonNull
  @Override
  public MenuPersonalRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_personal, parent, false);
    this.view = view;
    return new ViewHolder(view);
  }


  @Override
  public void onBindViewHolder(@NonNull MenuPersonalRecyclerAdapter.ViewHolder holder, final int position) {
    SessionManagement sessionManagement = new SessionManagement(context);
    final int userId = sessionManagement.getSessionUserId();
    holder.textView.setText(itemMenus.get(position).getItemName());
    holder.imageView.setImageResource(itemMenus.get(position).getIconMenu());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        switch (position) {
          case 0:
            if (userId != -1) {
              intent = new Intent(context, CustomerListOrderActivity.class);
              context.startActivity(intent);
            } else {
              checkoutToLogin();
            }
            break;
          case 1:
            if (userId != -1) {
              intent = new Intent(context, DestinationActivity.class);
              context.startActivity(intent);
            } else {
              checkoutToLogin();
            }
            break;
          case 2:
            if (userId != -1) {
              intent = new Intent(context, BuyedProductActivity.class);
              context.startActivity(intent);
            } else {
              checkoutToLogin();
            }
            break;
        }
      }
    });
  }

  public void checkoutToLogin() {
    intent = new Intent(context, LoginActivity.class);
    context.startActivity(intent);
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
