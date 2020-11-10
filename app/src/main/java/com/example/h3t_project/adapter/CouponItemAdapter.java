package com.example.h3t_project.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.CouponDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.activity.CouponActivity;
import com.example.h3t_project.model.CouponItem;

import java.util.ArrayList;

public class CouponItemAdapter extends RecyclerView.Adapter<CouponItemAdapter.ViewHolder> {
  ArrayList<CouponItem> couponItems;

  public CouponItemAdapter(ArrayList<CouponItem> couponItems) {
    this.couponItems = couponItems;
  }

  @NonNull
  @Override
  public CouponItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false);
    return new CouponItemAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CouponItemAdapter.ViewHolder holder, final int position) {
    holder.codeCoupon.setText(couponItems.get(position).getCodeCoupon());
    holder.valueCoupon.setText(String.valueOf(couponItems.get(position).getValueCoupon()));
    holder.trashbtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        CouponDAO couponDAO = new CouponDAO();
        int rowAffect = couponDAO.deleteCoupon(couponItems.get(position).getCodeCoupon());
//        CouponActivity couponActivity = new CouponActivity();
//        couponActivity.showCoupon();
      }
    });
  }

  @Override
  public int getItemCount() {
    return couponItems.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView codeCoupon;
    TextView valueCoupon;
    ImageView trashbtn;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      codeCoupon = itemView.findViewById(R.id.codeCouponTextView);
      valueCoupon = itemView.findViewById(R.id.valueCouponTextView);
      trashbtn = itemView.findViewById(R.id.trashCouponImageView);
    }
  }
}
