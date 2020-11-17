package com.example.h3t_project.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.DAO.UserDAO;
import com.example.h3t_project.R;

public class ForgotPasswordActivity extends AppCompatActivity {
  Toolbar toolbar;
  EditText txtForgot;
  TextView txtError;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_password);

    toolbar = findViewById(R.id.actionbar);

    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    txtForgot = findViewById(R.id.txtForgot);
    txtError = findViewById(R.id.txtError);
  }

  private void showAlertDialog(final Context context) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Đổi mật khẩu thành công");
    builder.setMessage("Mật khẩu của bạn đã được tự động đổi thành 12345!\nVui lòng đăng nhập lại!");
    builder.setIcon(android.R.drawable.star_big_on);

    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
      }
    });


    Dialog dialog = builder.create();
    dialog.setCanceledOnTouchOutside(false);
    dialog.show();
  }

  public void onClickForgetPassword(View view) {
    String username = txtForgot.getText().toString();
    UserDAO userDAO = new UserDAO();
    if (userDAO.changePassword(username)) {
      showAlertDialog(this);
    } else {
      txtError.setText("Chúng tôi không tìm thấy bạn trong danh sách khách hàng!\nVui lòng thử lại hoặc đăng kí!");
    }
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }
    return super.onOptionsItemSelected(item);
  }
}
