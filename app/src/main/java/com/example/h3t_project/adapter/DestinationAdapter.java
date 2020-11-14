package com.example.h3t_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.R;
import com.example.h3t_project.model.Destination;

import java.util.ArrayList;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {
  ArrayList<Destination> destinations;

  public DestinationAdapter(ArrayList<Destination> destinations) {
    this.destinations = destinations;
  }


  @NonNull
  @Override
  public DestinationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_item, parent, false);
    return new DestinationAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DestinationAdapter.ViewHolder holder, int position) {
      holder.textView.setText(destinations.get(position).toString());
  }

  @Override
  public int getItemCount() {
    return destinations.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView textView;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.destinationDetail);
    }
  }
}
