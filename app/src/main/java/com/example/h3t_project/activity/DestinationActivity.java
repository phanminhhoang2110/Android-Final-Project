package com.example.h3t_project.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.DestinationDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.DestinationAdapter;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.model.Destination;
import com.example.h3t_project.sessionhelper.SessionManagement;

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
    SessionManagement sessionManagement = new SessionManagement(this);
    int customerId = sessionManagement.getSessionUserId();
    ArrayList<Destination> destinations = (ArrayList<Destination>) destinationDAO.getDestinationByUser(customerId);
    if (destinations == null || destinations.size() == 0) {
      alertError();
    }
    DestinationAdapter adapter = new DestinationAdapter(this, destinations);
    recyclerView.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
  }

  private void alertError() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("C?? l???i x???y ra");
    builder.setMessage("M???ng kh??ng ???n ?????nh!\nXin vui l??ng th??? l???i!");
    builder.setPositiveButton("Kh??ng", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        finish();
      }
    });
    builder.setNegativeButton("C??", new DialogInterface.OnClickListener() {
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
