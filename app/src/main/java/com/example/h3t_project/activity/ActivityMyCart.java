package com.example.h3t_project.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.DAO.DestinationDAO;
import com.example.h3t_project.DAO.OrderDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.model.Destination;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityMyCart extends AppCompatActivity {
  Toolbar toolbar;
  Button orderBtn;
  int destinationId = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_cart);

    toolbar = findViewById(R.id.actionbar);

    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    orderBtn = findViewById(R.id.btnBuyProdct);

    DestinationDAO destinationDAO = new DestinationDAO();
    final ArrayList<Destination> destinations = (ArrayList<Destination>) destinationDAO.getDestinationByUser(1);
    List<String> listDestination = new ArrayList<>();
    for (int i = 0; i < destinations.size(); i++) {
      listDestination.add(destinations.get(i).toString());
    }

    final Spinner spinner = findViewById(R.id.spinner2);

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listDestination);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(arrayAdapter);

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        destinationId = destinations.get(position).getId();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    orderBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SessionManagement sessionManagement = new SessionManagement(ActivityMyCart.this);
        int customerId = sessionManagement.getSessionUserId();
        final String nameForCart = "mycart" + customerId;
        List<Integer> listProductId = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences(nameForCart, Context.MODE_PRIVATE);
        Map<String, ?> allListInCart = preferences.getAll();
        for(Map.Entry<String, ?> entry : allListInCart.entrySet()){
          listProductId.add(Integer.parseInt(entry.getValue().toString()));
        }
        OrderDAO orderDAO = new OrderDAO();
        boolean result = orderDAO.newOrder(customerId, destinationId,listProductId);
        if(result==true){
          Intent intent = new Intent(v.getContext(),ThanksActivity.class);
          startActivity(intent);
        }else{
          Toast.makeText(ActivityMyCart.this,"Có lỗi xảy ra xin vui lòng thử lại!", Toast.LENGTH_SHORT).show();
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
