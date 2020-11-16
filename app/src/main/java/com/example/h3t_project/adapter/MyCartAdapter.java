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

import com.example.h3t_project.DAO.CustomerViewProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.model.Product;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

  Context context;
  List<Product> products;
  int totalPrice;
  TextView viewTotalMoney;

  public MyCartAdapter(Context context, List<Product> products,TextView viewTotalMoney) {
    this.context = context;
    this.products = products;
    this.viewTotalMoney = viewTotalMoney;
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
    SessionManagement sessionManagement = new SessionManagement(context);
    int roleId = sessionManagement.getSessionUserId();
    final String nameForCart = "mycart" + roleId;
    holder.imageView.setImageResource(products.get(position).getImage_id());
    holder.viewName.setText(products.get(position).getName());
    holder.viewPrice.setText(decimalFormat.format(products.get(position).getSell_price()) + " đ");
    holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nameForCart, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(String.valueOf(products.get(position).getId()));
        editor.commit();
        products.remove(position);
        notifyItemRemoved(position);
        setupPrice(viewTotalMoney);
      }
    });
  }

  public void setupPrice(TextView viewTotalMoney){
    CustomerViewProductDAO dao = new CustomerViewProductDAO();
    List<Product> temp = new ArrayList<>();
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
    SessionManagement sessionManagement = new SessionManagement(context);
    int roleId = sessionManagement.getSessionUserId();
    String nameForCart = "mycart" + roleId;
    SharedPreferences preferences = this.context.getSharedPreferences(nameForCart, Context.MODE_PRIVATE);
    Map<String, ?> entries = preferences.getAll();
    Set<String> keys = entries.keySet();

    for (String key : keys) {
      Product product = new Product();
      temp = dao.getProductById(Integer.parseInt(key));
      product.setId(temp.get(0).getId());
      product.setName(temp.get(0).getName());
      product.setSell_price(temp.get(0).getSell_price());
      product.setImage_id(getResId(temp.get(0).getLink_image(), R.drawable.class));
      products.add(product);
      totalPrice += temp.get(0).getSell_price();
    }
    viewTotalMoney.setText(decimalFormat.format(totalPrice) + " đ");
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
    return products.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView viewName;
    TextView viewPrice;
    Button buttonDelete;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.item_image);
      viewName = itemView.findViewById(R.id.item_name);
      viewPrice = itemView.findViewById(R.id.item_price);
      buttonDelete = itemView.findViewById(R.id.btn_delete_in_cart);
    }
  }
}