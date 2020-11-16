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
import com.example.h3t_project.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

  Context context;
  List<Product> products;
  int totalPrice;

  public MyCartAdapter(Context context, List<Product> products, int totalPrice) {
    this.context = context;
    this.products = products;
    this.totalPrice = totalPrice;
  }

  @NonNull
  @Override
  public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_cart, parent, false);
    return new MyCartAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, final int position) {
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");

    holder.imageView.setImageResource(products.get(position).getImage_id());
    holder.viewName.setText(products.get(position).getName());
    holder.viewPrice.setText(decimalFormat.format(products.get(position).getSell_price()) + " đ");
//            holder.viewTotalMoney.setText(""+decimalFormat.format(totalPrice)+" đ");
    holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mycart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(String.valueOf(products.get(position).getId()));
        editor.commit();
        products.remove(position);
        notifyItemRemoved(position);
      }
    });
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView viewName;
    TextView viewPrice;
    Button buttonDelete;
    TextView viewTotalMoney;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.item_image);
      viewName = itemView.findViewById(R.id.item_name);
      viewPrice = itemView.findViewById(R.id.item_price);
      buttonDelete = itemView.findViewById(R.id.btn_delete_in_cart);
      viewTotalMoney = itemView.findViewById(R.id.total_money_price);
    }
  }
}
