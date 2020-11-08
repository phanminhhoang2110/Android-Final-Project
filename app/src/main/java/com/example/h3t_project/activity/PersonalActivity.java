package com.example.h3t_project.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.MenuPersonalRecyclerAdapter;
import com.example.h3t_project.model.MenuItemPersonal;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class PersonalActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_personal);
    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle("Cá Nhân");
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setupMenuPersonal();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_toolbar_personal,menu);
    return true;
  }

  private void setupMenuPersonal() {
    RecyclerView recyclerView = findViewById(R.id.recyclerPersonal);
    ArrayList<MenuItemPersonal> itemMenus = new ArrayList<>();
    for (int i = 1; i <= 3; i++) {
      itemMenus.add(new MenuItemPersonal(getResId("menu_personal_" + i, R.string.class), getResId("ic_menu_personal_" + i, R.drawable.class)));
    }
    MenuPersonalRecyclerAdapter adapter = new MenuPersonalRecyclerAdapter(itemMenus);
    recyclerView.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
  }


  public static int getResId(String resName, Class<?> c) {
    try {
      Field idField = c.getDeclaredField(resName);
      return idField.getInt(idField);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}
