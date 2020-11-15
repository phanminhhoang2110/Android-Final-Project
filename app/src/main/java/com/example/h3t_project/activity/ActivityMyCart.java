package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.h3t_project.DAO.DestinationDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.model.Destination;

import java.util.ArrayList;
import java.util.List;

public class ActivityMyCart extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        toolbar = findViewById(R.id.actionbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DestinationDAO destinationDAO = new DestinationDAO();
        ArrayList<Destination> destinations = (ArrayList<Destination>) destinationDAO.getDestinationByUser(1);
        List<String> listDestination = new ArrayList<>();
        for (int i = 0; i < destinations.size() ; i++) {
            listDestination.add(destinations.get(i).toString());
        }

        Spinner spinner = findViewById(R.id.spinner2);

        ArrayAdapter<String> arrayAdapter = new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listDestination);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(this, HomePageActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}