package com.example.h3t_project.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.h3t_project.R;

public class EditPersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}