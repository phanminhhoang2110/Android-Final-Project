package com.example.h3t_project.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.ItemCartDetail;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

  Context context;
  ArrayList<ItemCartDetail> products;
  TextView viewTotalMoney;
  ResetAdapter resetAdapter;

  public MyCartAdapter(Context context, ArrayList<ItemCartDetail> products, TextView viewTotalMoney, ResetAdapter resetAdapter) {
    this.context = context;
    this.products = products;
    this.viewTotalMoney = viewTotalMoney;
    this.resetAdapter = resetAdapter;
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
  public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_cart, parent, false);
    return new MyCartAdapter.ViewHolder(view);
  }
  int quantity;
  @Override
  public void onBindViewHolder(@NonNull final MyCartAdapter.ViewHolder holder, final int position) {
    final DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
    holder.imageView.setImageResource(getResId(products.get(position).getImage(), R.drawable.class));
    holder.viewName.setText(products.get(position).getName());
    holder.viewPrice.setText(decimalFormat.format(products.get(position).getSellPrice()) + " đ");
    holder.qualityCart.setText(String.valueOf(products.get(position).getQuantity()));
    holder.setResetAdapter(this.resetAdapter);
    holder.addCartBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        quantity = products.get(position).getQuantity();
        quantity++;
        products.get(position).setQuantity(quantity);
        holder.qualityCart.setText(String.valueOf(products.get(position).getQuantity()));
        holder.resetAdapter.reset(quantity,position);
      }
    });
    holder.minusCartBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        quantity = products.get(position).getQuantity();
        if(quantity>=2) {
          quantity--;
        }
        products.get(position).setQuantity(quantity);
        holder.qualityCart.setText(String.valueOf(products.get(position).getQuantity()));
        holder.resetAdapter.reset(quantity,position);
      }
    });
    holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        products.remove(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
        viewTotalMoney.setText(decimalFormat.format(setupPrice()) + " đ");
      }
    });
  }

  public int setupPrice() {
    int totalPrice = 0;
    for (ItemCartDetail product : products) {
      totalPrice += product.getSellPrice() * product.getQuantity();
    }
    return totalPrice;
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  public interface ResetAdapter {
    public void reset(int quantity, int indexProduct);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView viewName;
    TextView viewPrice;
    Button buttonDelete;
    TextView qualityCart;
    Button addCartBtn;
    Button minusCartBtn;
    ResetAdapter resetAdapter;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.item_image);
      viewName = itemView.findViewById(R.id.item_name);
      viewPrice = itemView.findViewById(R.id.item_price);
      buttonDelete = itemView.findViewById(R.id.btn_delete_in_cart);
      qualityCart = itemView.findViewById(R.id.qualityCart);
      addCartBtn = itemView.findViewById(R.id.addCartBtn);
      minusCartBtn = itemView.findViewById(R.id.minusCartbtn);
    }

    public void setResetAdapter(ResetAdapter resetAdapter) {
      this.resetAdapter = resetAdapter;
    }
  }
}
