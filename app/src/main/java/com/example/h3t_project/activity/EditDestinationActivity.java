package com.example.h3t_project.activity;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.DAO.DestinationDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.model.Destination;

public class EditDestinationActivity extends AppCompatActivity {

  String[] myString;
  Spinner spinner;
  ArrayAdapter<String> adapter;
  EditText addressEditText;
  EditText wardEditText;
  EditText districtEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_destination);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(VietnameseWord.EDIT_DESTINATION_ACTIVITY);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    spinner = findViewById(R.id.citySpinner);
    addressEditText = findViewById(R.id.addressEditText);
    wardEditText = findViewById(R.id.wardEditText);
    districtEditText = findViewById(R.id.districtEditText);
    set63City();
  }

  private void set63City() {
    Resources res = getResources();
    myString = res.getStringArray(R.array.arr_thanhpho);
    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myString);
    spinner.setAdapter(adapter);
  }

  private void showAlertDialog(String title, String mess, int icon, String positiveButton) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(title);
    builder.setMessage(mess);
    builder.setIcon(icon);

    builder.setPositiveButton(positiveButton, null);


    Dialog dialog = builder.create();
    dialog.setCanceledOnTouchOutside(false);
    dialog.show();
  }

  public void onClickSaveBtn(View view) {
    String address = addressEditText.getText().toString();
    String ward = wardEditText.getText().toString();
    String district = districtEditText.getText().toString();
    if(address.length() != 0 && ward.length() != 0 && district.length() != 0){
      Destination destination = new Destination(address,spinner.getSelectedItem().toString(),district,ward);
      DestinationDAO destinationDAO = new DestinationDAO();
      boolean flag = destinationDAO.addNewDestination(1,destination);
      if(flag){
        showAlertDialog("Thêm địa chỉ mới thành công !","Đã xong",android.R.drawable.star_on, "Ok");
      }
    }else {
      showAlertDialog("Có trường bị bỏ trống !","Không được để trống các trường !",android.R.drawable.ic_dialog_info, "Ok");
    }
  }

}
