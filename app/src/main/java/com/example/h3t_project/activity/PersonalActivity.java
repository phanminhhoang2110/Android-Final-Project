package com.example.h3t_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.MenuPersonalRecyclerAdapter;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.model.MenuItemPersonal;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class PersonalActivity extends AppCompatActivity {
  Button btnLogout;

  public static int getResId(String resName, Class<?> c) {
    try {
      Field idField = c.getDeclaredField(resName);
      return idField.getInt(idField);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_personal);
    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle(VietnameseWord.PERSONAL_ACTIVITY);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setupMenuPersonal();
    btnLogout = findViewById(R.id.logout);
    SessionManagement sessionManagement = new SessionManagement(PersonalActivity.this);
    int userId = sessionManagement.getSessionUserId();
    if (userId == -1) {
      btnLogout.setVisibility(View.INVISIBLE);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_toolbar_personal, menu);
    return true;
  }

  private void setupMenuPersonal() {
    RecyclerView recyclerView = findViewById(R.id.recyclerPersonal);
    ArrayList<MenuItemPersonal> itemMenus = new ArrayList<>();
    for (int i = 1; i <= 3; i++) {
      itemMenus.add(new MenuItemPersonal(getResId("menu_personal_" + i, R.string.class), getResId("ic_menu_personal_" + i, R.drawable.class)));
    }
    MenuPersonalRecyclerAdapter adapter = new MenuPersonalRecyclerAdapter(itemMenus, this);
    recyclerView.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.setting:
        Intent intent = new Intent(PersonalActivity.this, SettingActivity.class);
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);

    }
  }

  public void logout(View view) {
    SessionManagement sessionManagement = new SessionManagement(PersonalActivity.this);
    sessionManagement.removeSession();
    userMoveToHomePage();
  }

  private void userMoveToHomePage() {
    Intent intent = new Intent(PersonalActivity.this, HomePageActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }
}
