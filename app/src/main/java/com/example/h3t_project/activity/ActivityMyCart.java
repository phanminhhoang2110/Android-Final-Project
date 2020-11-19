package com.example.h3t_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.DAO.DestinationDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.model.Destination;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.util.ArrayList;

public class ActivityMyCart extends AppCompatActivity {
  Toolbar toolbar;
  Button orderBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_cart);

    toolbar = findViewById(R.id.actionbar);

    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    orderBtn = findViewById(R.id.btnBuyProdct);
    SessionManagement sessionManagement = new SessionManagement(this);
    final int userId = sessionManagement.getSessionUserId();
    DestinationDAO destinationDAO = new DestinationDAO();
    final ArrayList<Destination> destinations = (ArrayList<Destination>) destinationDAO.getDestinationByUser(userId);

    orderBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (userId != -1 && destinations.size() != 0) {
          Intent intent = new Intent(getApplicationContext(), ConfirmOrderActivity.class);
          startActivity(intent);
        } else if (destinations.size() == 0) {
          Toast.makeText(v.getContext(), "Vui lòng cập nhập sổ địa chỉ trước khi mua hàng!", Toast.LENGTH_SHORT).show();
        }
      }
    });


  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
