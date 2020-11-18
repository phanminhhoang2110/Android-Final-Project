package com.example.h3t_project.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.h3t_project.DAO.CartDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.sessionhelper.SessionManagement;

public class ThanksActivity extends AppCompatActivity {


  Button buttonHome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_thanks);
    buttonHome = findViewById(R.id.btnHome);

    Intent intent = getIntent();
    final int customerId = intent.getIntExtra("customerId", -1);
    final CartDAO cartDAO = new CartDAO();
    final boolean deleteResult = cartDAO.deleteCart(customerId);

    buttonHome.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(deleteResult==false){
          cartDAO.deleteCart(customerId);
        }
        Intent intent = new Intent(v.getContext(), HomePageActivity.class);
        startActivity(intent);
      }
    });
  }
}
