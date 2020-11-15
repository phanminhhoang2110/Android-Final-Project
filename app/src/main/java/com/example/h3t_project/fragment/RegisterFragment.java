package com.example.h3t_project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.h3t_project.DAO.UserDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.activity.HomePageActivity;
import com.example.h3t_project.activity.LoginActivity;
import com.example.h3t_project.model.User;

public class RegisterFragment extends Fragment {
    EditText txtFullname, txtPhone, txtEmail, txtPassword, txtUsername;
    TextView txtInform;
    RadioButton rdMale, rdFemale;
    Button btnRegister;
    static int gender;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        txtFullname = view.findViewById(R.id.textFullname);
        txtPhone = view.findViewById(R.id.textPhone);
        txtEmail = view.findViewById(R.id.textEmail);
        txtPassword = view.findViewById(R.id.textPasswordRegister);
        txtUsername = view.findViewById(R.id.textUsernameRegister);
        btnRegister = view.findViewById(R.id.btnRegister);
        txtInform = view.findViewById(R.id.textWrongInform);
        rdMale = view.findViewById(R.id.rdMale);
        rdFemale = view.findViewById(R.id.rdFemale);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDAO userDAO = new UserDAO();
                String fullname = txtFullname.getText().toString();
                String phone = txtPhone.getText().toString();
                String email = txtEmail.getText().toString();
                String username  = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                if(!userDAO.isDuplicate(username)){
                    int insertUser = userDAO.insert(username, password, fullname, phone, email, gender);
                    if(insertUser != 1){
                        userMoveToLoginPage();
                    }
                    else {
                        txtInform.setText("Vui lòng không để trống bất kì thông tin nào!");
                    }
                }
                else {
                    txtInform.setText("Username đã tồn tại");
                }

            }
        });
        rdMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = 1;
            }
        });
        rdFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = 2;
            }
        });
        return view;

    }

    private void userMoveToLoginPage() {

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
