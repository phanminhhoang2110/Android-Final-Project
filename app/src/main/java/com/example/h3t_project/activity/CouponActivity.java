package com.example.h3t_project.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.CouponDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.CouponItemAdapter;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.model.CouponItem;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity {


  Button addCounpon;
  RecyclerView listCouponRecyclerView;
  CouponDAO couponDAO = new CouponDAO();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coupon);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    setTitle(VietnameseWord.couponActivity);
    addCounpon = findViewById(R.id.addCouponbtn);
    addCounpon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        CouponItem coupon = getCouponFromFragment();
        boolean result = false;
        if (coupon != null) {
          result = couponDAO.addCoupon(coupon);
        }
        if (result == true) {
          showSuccessDialog();
        }
      }
    });
    showCoupon();
  }

  private CouponItem getCouponFromFragment() {
    EditText codeCouponEdt = findViewById(R.id.codeCoupon);
    EditText valueCouponEdt = findViewById(R.id.valueCoupon);
    String codeCoupon = codeCouponEdt.getText().toString();
    String valueCouponRaw = valueCouponEdt.getText().toString();
    if (codeCoupon.length() == 0 || valueCouponRaw.length() == 0) {
      showErrorDialog();
      return null;
    }
    int valueCoupon = Integer.parseInt(valueCouponRaw);
    CouponItem couponItem = new CouponItem();
    couponItem.setCodeCoupon(codeCoupon);
    couponItem.setValueCoupon(valueCoupon);
    return couponItem;
  }

  private void showErrorDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(VietnameseWord.error);
    builder.setMessage(VietnameseWord.errorCouponEmpty);
    builder.setIcon(android.R.drawable.ic_dialog_info);
    builder.setPositiveButton(VietnameseWord.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    Dialog dialog = builder.create();
    dialog.setCanceledOnTouchOutside(false);
    dialog.show();
  }

  private void showSuccessDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(VietnameseWord.addSuccess);
    builder.setMessage(VietnameseWord.addCouponSuccess);
    builder.setIcon(android.R.drawable.star_big_on);
    builder.setPositiveButton(VietnameseWord.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    Dialog dialog = builder.create();
    dialog.setCanceledOnTouchOutside(false);
    dialog.show();
  }

  private void showCoupon() {
    listCouponRecyclerView = findViewById(R.id.listCouponRecyclerView);
    ArrayList<CouponItem> couponItems = (ArrayList<CouponItem>) couponDAO.getCoupons();
    CouponItemAdapter couponItemAdapter = new CouponItemAdapter(couponItems);
    listCouponRecyclerView.setAdapter(couponItemAdapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    listCouponRecyclerView.setLayoutManager(layoutManager);
  }
}
