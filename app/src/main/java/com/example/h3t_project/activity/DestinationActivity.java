package com.example.h3t_project.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.DestinationDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.DestinationAdapter;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.model.Destination;

import java.util.ArrayList;

public class DestinationActivity extends AppCompatActivity {

  RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_destination);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(VietnameseWord.DESTINATION_ACTIVITY);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    recyclerView = findViewById(R.id.destinationRecyclerView);

    setupDestination();

  }

  private void setupDestination() {
    DestinationDAO destinationDAO = new DestinationDAO();
    ArrayList<Destination> destinations = (ArrayList<Destination>) destinationDAO.getDestinationByUser(1);
    if(destinations == null || destinations.size()==0){
      alertError();
    }
    DestinationAdapter adapter = new DestinationAdapter(destinations);
    recyclerView.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
  }

  private void alertError() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Có lỗi xảy ra");
    builder.setMessage("Mạng không ổn định!\nXin vui lòng thử lại!");
    builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
          finish();
      }
    });
    builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        setupDestination();
      }
    });
  }

  public void onClickAddDestinationBtn(View view) {
    Intent intent = new Intent(this, EditDestinationActivity.class);
    startActivity(intent);
  }
}
