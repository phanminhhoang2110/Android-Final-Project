package com.example.h3t_project.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;

public class EditPersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal);

        Toolbar toolbar = findViewById(R.id.toolbarSellerEditPersonalInfo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(VietnameseWord.EDIT_PERSONAL_ACTIVITY);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
