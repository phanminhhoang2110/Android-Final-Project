package com.example.h3t_project.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.R;

public class SentMailActivity extends AppCompatActivity {
  Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sent_mail);

    toolbar = findViewById(R.id.actionbar2);

    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
