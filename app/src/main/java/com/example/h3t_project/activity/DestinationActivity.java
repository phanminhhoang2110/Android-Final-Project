package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.DestinationDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.DestinationAdapter;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.model.Destination;

import java.util.ArrayList;
import java.util.List;

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
  
  private void setupDestination(){
    DestinationDAO destinationDAO = new DestinationDAO();
    ArrayList<Destination> destinations = (ArrayList<Destination>) destinationDAO.getDestinationByUser(1);
    DestinationAdapter adapter = new DestinationAdapter(destinations);
    recyclerView.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
  }
}
