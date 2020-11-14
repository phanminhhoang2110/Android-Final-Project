package com.example.h3t_project.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.h3t_project.DAO.UserDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.model.User;
import com.example.h3t_project.sessionhelper.SessionManagement;

public class EditPersonalActivity extends AppCompatActivity {
    EditText editUsername, editPassword, editFullname, editEmail, editPhone;
    TextView textInform;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal);
        //input up
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

        SessionManagement sessionManagement = new SessionManagement(EditPersonalActivity.this);
        int userId = sessionManagement.getSessionUserId();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userId);
        insertInfo(user);

    }

    public void insertInfo(User user){
        editUsername.setText(user.getUsername());
        editPassword.setText(user.getPassword());
        editPhone.setText(user.getPhone());
        editEmail.setText(user.getEmail());
        editFullname.setText(user.getFullname());
    }

    public void changeInfo(View view){
        SessionManagement sessionManagement = new SessionManagement(EditPersonalActivity.this);
        int userId = sessionManagement.getSessionUserId();
        UserDAO userDAO = new UserDAO();
        int editUser = userDAO.update(userId, editUsername.getText().toString(), editPhone.getText().toString(),
                editEmail.getText().toString(), editUsername.getText().toString(), editPassword.getText().toString());
        if(editUser != 1){
            textInform.setText("Đổi thông tin thành công");
            textInform.setTextColor(Color.parseColor("#CC189EFF"));
        }
        else {
            textInform.setText("Đổi thông tin thất bại");
            textInform.setTextColor(Color.parseColor("#F52929"));
        }
    }

}
