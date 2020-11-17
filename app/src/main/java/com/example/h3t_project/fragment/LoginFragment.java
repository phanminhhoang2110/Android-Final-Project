package com.example.h3t_project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.h3t_project.DAO.UserDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.activity.ForgotPasswordActivity;
import com.example.h3t_project.activity.HomePageActivity;
import com.example.h3t_project.activity.SellerHomeActivity;
import com.example.h3t_project.activity.SellerProductActivity;
import com.example.h3t_project.model.User;
import com.example.h3t_project.sessionhelper.SessionManagement;


public class LoginFragment extends Fragment {
  EditText textUsername, textPassword;
  TextView textForgotPassword, textInform;
  Button btnLogin;

  public LoginFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_login, container, false);
    textUsername = view.findViewById(R.id.textUsername);
    textPassword = view.findViewById(R.id.textPassword);
    textForgotPassword = view.findViewById(R.id.textForgotPassword);
    textInform = view.findViewById(R.id.textInform);

    btnLogin = (Button) view.findViewById(R.id.btnLogin);
    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        UserDAO userDAO = new UserDAO();
        String username = textUsername.getText().toString();
        String password = textPassword.getText().toString();
        User user = userDAO.login(username, password);
        if (user != null) {
          SessionManagement sessionManagement = new SessionManagement(getActivity());
          sessionManagement.saveSession(user);
          if (user.getRoleId().getRoleId() == 1) {
            userMoveToHomePage();
          } else {
            adminMoveToHomePage();
          }

        } else {
          textInform.setText("Username hoặc mật khẩu sai!");
        }
      }
    });
    textForgotPassword.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getActivity(), ForgotPasswordActivity.class);
        startActivity(intent);
      }
    });
    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
//        SessionManagement sessionManagement = new SessionManagement(getActivity());
//        sessionManagement.removeSession();
    checkSession();
  }

  private void checkSession() {
    SessionManagement sessionManagement = new SessionManagement(getActivity());
    int roleId = sessionManagement.getSessionRoleId();
    int userId = sessionManagement.getSessionUserId();
    if (userId != -1) {
      if (roleId == 1) {
        userMoveToHomePage();
      }
      if (roleId == 2) {
        adminMoveToHomePage();
      }
    }
  }

  private void userMoveToHomePage() {

    Intent intent = new Intent(getActivity(), HomePageActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }

  private void adminMoveToHomePage() {

    Intent intent = new Intent(getActivity(), SellerHomeActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }

}
