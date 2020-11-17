package com.example.h3t_project.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;

public class SettingActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting);
    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle(VietnameseWord.SETTING_ACTIVITY);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
