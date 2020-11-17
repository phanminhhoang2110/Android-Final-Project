package com.example.h3t_project.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
  int id = -1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_destination);
    Intent intent = getIntent();
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    if (intent == null) {
      getSupportActionBar().setTitle(VietnameseWord.EDIT_DESTINATION_ACTIVITY);
    } else {
      getSupportActionBar().setTitle(VietnameseWord.ADD_NEW_DESTINATION_ACTIVITY);
    }
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    spinner = findViewById(R.id.citySpinner);
    addressEditText = findViewById(R.id.addressEditText);
    wardEditText = findViewById(R.id.wardEditText);
    districtEditText = findViewById(R.id.districtEditText);
    set63City();
    if (intent != null) {
      setupEditDestination(intent);
    }
  }

  private void setupEditDestination(Intent intent) {
    id = intent.getIntExtra("id", 0);
    String address = intent.getStringExtra("address");
    String ward = intent.getStringExtra("ward");
    String province = intent.getStringExtra("province");
    String district = intent.getStringExtra("district");
    addressEditText.setText(address);
    wardEditText.setText(ward);
    districtEditText.setText(district);

  }

  private void set63City() {
    Resources res = getResources();
    myString = res.getStringArray(R.array.arr_thanhpho);
    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myString);
    spinner.setAdapter(adapter);
  }

  private void showAlertDialog(String title, String mess, int icon, String positiveButton, int back) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(title);
    builder.setMessage(mess);
    builder.setIcon(icon);

    if (back == 1) {
      builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          Intent intent = new Intent(getApplicationContext(), PersonalActivity.class);
          startActivity(intent);
        }
      });
    } else {
      builder.setPositiveButton(positiveButton, null);
    }

    Dialog dialog = builder.create();
    dialog.setCanceledOnTouchOutside(false);
    dialog.show();
  }

  public void onClickSaveBtn(View view) {
    String address = addressEditText.getText().toString();
    String ward = wardEditText.getText().toString();
    String district = districtEditText.getText().toString();
    if (id == -1) {
      if (address.length() != 0 && ward.length() != 0 && district.length() != 0) {
        Destination destination = new Destination(address, spinner.getSelectedItem().toString(), district, ward);
        DestinationDAO destinationDAO = new DestinationDAO();
        boolean flag = destinationDAO.addNewDestination(1, destination);
        if (flag) {
          showAlertDialog("Đã xong", "Thêm địa chỉ mới thành công!", android.R.drawable.star_on, "Ok", 1);
        }
      } else {
        showAlertDialog("Có trường bị bỏ trống !", "Không được để trống các trường!", android.R.drawable.ic_dialog_info, "Ok", 0);
      }
    }else{
      if (address.length() != 0 && ward.length() != 0 && district.length() != 0) {
        Destination destination = new Destination(id, address, spinner.getSelectedItem().toString(), district, ward);
        DestinationDAO destinationDAO = new DestinationDAO();
        boolean flag = destinationDAO.updateDestination(destination);
        if (flag) {
          showAlertDialog("Đã xong", "Sửa địa chỉ thành công!", android.R.drawable.star_on, "Ok", 1);
        }
      } else {
        showAlertDialog("Có trường bị bỏ trống !", "Không được để trống các trường!", android.R.drawable.ic_dialog_info, "Ok", 0);
      }
    }
  }

}
