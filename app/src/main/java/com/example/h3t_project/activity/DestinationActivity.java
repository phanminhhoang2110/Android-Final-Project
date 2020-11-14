package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;

public class DestinationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_destination);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(VietnameseWord.DESTINATION_ACTIVITY);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

  }
}
