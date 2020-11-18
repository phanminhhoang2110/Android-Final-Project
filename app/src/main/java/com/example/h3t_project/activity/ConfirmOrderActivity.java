package com.example.h3t_project.activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.CartDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.ConfirmProductAdapter;
import com.example.h3t_project.fragment.PriceFragment;
import com.example.h3t_project.inteface.OnSendCouponData;
import com.example.h3t_project.model.CouponItem;
import com.example.h3t_project.model.ItemCartDetail;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.util.ArrayList;

public class ConfirmOrderActivity extends AppCompatActivity implements OnSendCouponData {
  Toolbar toolbar;
  RecyclerView productsInCart;
  ArrayList<ItemCartDetail> itemCartDetails;
  int userId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirm_order);
    toolbar = findViewById(R.id.toolbar);
    productsInCart = findViewById(R.id.productsInCartRecyclerView);

    setSupportActionBar(toolbar);

    getSupportActionBar().setTitle("Xác nhận đơn hàng");

    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    SessionManagement sessionManagement = new SessionManagement(this);
    userId = sessionManagement.getSessionUserId();

    setupListInCart(userId);
  }

  public void setupListInCart(int userId) {
    CartDAO cartDAO = new CartDAO();
    itemCartDetails = cartDAO.getAllCartDetail(userId);
    ConfirmProductAdapter adapter = new ConfirmProductAdapter(itemCartDetails);
    productsInCart.setAdapter(adapter);
    LinearLayoutManager manager = new LinearLayoutManager(this);
    productsInCart.setLayoutManager(manager);
  }

  @Override
  public void onSendCouponData(CouponItem couponItem) {
    PriceFragment priceFragment = (PriceFragment) getSupportFragmentManager().findFragmentById(R.id.priceFragment);
    priceFragment.receiveCoupon(couponItem);
  }
}
