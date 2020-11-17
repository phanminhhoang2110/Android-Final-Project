package com.example.h3t_project.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.DestinationDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.activity.EditDestinationActivity;
import com.example.h3t_project.model.Destination;

import java.io.Serializable;
import java.util.ArrayList;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {
  Context context;
  ArrayList<Destination> destinations;

  public DestinationAdapter(Context context, ArrayList<Destination> destinations) {
    this.destinations = destinations;
    this.context = context;
  }


  @NonNull
  @Override
  public DestinationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_item, parent, false);
    return new DestinationAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DestinationAdapter.ViewHolder holder, final int position) {
    holder.textView.setText(destinations.get(position).toString());
    holder.imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click_animation));
        showConfirmDialog(destinations.get(position).getId(),1,position);
      }
    });
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click_animation));
        Intent intent = new Intent(context, EditDestinationActivity.class);
        intent.putExtra("address", destinations.get(position).getAddress());
        intent.putExtra("ward",destinations.get(position).getWard());
        intent.putExtra("id",destinations.get(position).getId());
        intent.putExtra("province", destinations.get(position).getProvince());
        intent.putExtra("district",destinations.get(position).getDistrict());
        context.startActivity(intent);
      }
    });
  }

  private void showConfirmDialog(final int itemPosition, final int userId, final int positionInList) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("Xóa địa chỉ");
    builder.setMessage("Bạn có chắc muốn xóa địa chỉ này ?");
    builder.setIcon(android.R.drawable.ic_dialog_info);


    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        DestinationDAO destinationDAO = new DestinationDAO();
        boolean deleteResult = destinationDAO.deleteDestination(itemPosition, userId);
        if (deleteResult == true){
          destinations.remove(positionInList);
          notifyDataSetChanged();
          Toast.makeText(context,"Xóa thành công!", Toast.LENGTH_SHORT).show();
        }else{
          Toast.makeText(context,"Xóa thất bại! Xin vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }
      }
    });

    builder.setNegativeButton("Không", null);

    Dialog dialog = builder.create();
    dialog.setCanceledOnTouchOutside(false);
    dialog.show();
  }

  @Override
  public int getItemCount() {
    if(destinations == null){
      return 0;
    }
    return destinations.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.destinationDetail);
      imageView = itemView.findViewById(R.id.deleteDestinationBtn);
    }
  }
}
