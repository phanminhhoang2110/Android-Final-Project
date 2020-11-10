package com.example.h3t_project.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.DAO.CouponDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.model.CouponItem;

public class CouponActivity extends AppCompatActivity {


  Button addCounpon;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coupon);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    setTitle("Thêm mã giảm giá");
    addCounpon = findViewById(R.id.addCouponbtn);
    addCounpon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        CouponItem coupon = getCouponFromFragment();
        boolean result = false;
        if(coupon!= null){
          CouponDAO couponDAO = new CouponDAO();
          result = couponDAO.addCoupon(coupon);
        }
        if(result == true){
          showSuccessDialog();
        }
      }
    });
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
    builder.setTitle("Lỗi");
    builder.setMessage("Không được để trường code hoặc trường giá trị trống");
    builder.setIcon(android.R.drawable.ic_dialog_info);
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
    builder.setTitle("Thêm thành công");
    builder.setMessage("Đã thêm coupon thành công");
    builder.setIcon(android.R.drawable.star_big_on);
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    Dialog dialog = builder.create();
    dialog.setCanceledOnTouchOutside(false);
    dialog.show();
  }

}
