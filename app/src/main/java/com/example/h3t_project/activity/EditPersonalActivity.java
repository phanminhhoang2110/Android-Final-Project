package com.example.h3t_project.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.DAO.UserDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.model.User;
import com.example.h3t_project.sessionhelper.SessionManagement;

public class EditPersonalActivity extends AppCompatActivity {
  EditText editUsername, editPassword, editFullname, editEmail, editPhone;
  TextView textInform, txtName;
  SessionManagement sessionManagement;
  int userId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_personal);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    Toolbar toolbar = findViewById(R.id.toolbarSellerEditPersonalInfo);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(VietnameseWord.EDIT_PERSONAL_ACTIVITY);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    editUsername = findViewById(R.id.editUsername);
    editPassword = findViewById(R.id.editPassword);
    editFullname = findViewById(R.id.editFullname);
    editEmail = findViewById(R.id.editEmail);
    editPhone = findViewById(R.id.editPhone);
    textInform = findViewById(R.id.textInformEdit);
    txtName = findViewById(R.id.textViewName);

    sessionManagement = new SessionManagement(EditPersonalActivity.this);
    userId = sessionManagement.getSessionUserId();
    getUserById(userId);
  }

  public void getUserById(int userId) {
    UserDAO userDAO = new UserDAO();
    User user = userDAO.getUserById(userId);
    getInfo(user);
  }

  public void getInfo(User user) {
    editUsername.setText(user.getUsername());
    editPassword.setText(user.getPassword());
    editPhone.setText(user.getPhone());
    editEmail.setText(user.getEmail());
    editFullname.setText(user.getFullname());
    txtName.setText(user.getFullname());
  }

  public void changeInfo(View view) {
    UserDAO userDAO = new UserDAO();
    int editUser = userDAO.update(userId, editFullname.getText().toString(), editPhone.getText().toString(),
      editEmail.getText().toString(), editUsername.getText().toString(), editPassword.getText().toString());
    if (editUser != 1) {
      textInform.setText("Đổi thông tin thành công");
      textInform.setTextColor(Color.parseColor("#CC189EFF"));
    } else {
      textInform.setText("Đổi thông tin thất bại");
      textInform.setTextColor(Color.parseColor("#F52929"));
    }
    txtName.setText(editFullname.getText().toString());
  }

}
