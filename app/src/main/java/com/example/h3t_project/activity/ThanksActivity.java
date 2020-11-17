package com.example.h3t_project.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.h3t_project.R;
import com.example.h3t_project.sessionhelper.SessionManagement;

public class ThanksActivity extends AppCompatActivity {


  Button buttonHome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_thanks);
    buttonHome = findViewById(R.id.btnHome);

    SessionManagement sessionManagement = new SessionManagement(this);
    int customerId = sessionManagement.getSessionUserId();
    final String nameForCart = "mycart" + customerId;
    SharedPreferences preferences = getSharedPreferences(nameForCart, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.clear().commit();

    buttonHome.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), HomePageActivity.class);
        startActivity(intent);
      }
    });

  }
}
