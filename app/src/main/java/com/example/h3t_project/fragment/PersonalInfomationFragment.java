package com.example.h3t_project.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.h3t_project.DAO.UserDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.activity.EditPersonalActivity;
import com.example.h3t_project.activity.HomePageActivity;
import com.example.h3t_project.activity.LoginActivity;
import com.example.h3t_project.activity.PersonalActivity;
import com.example.h3t_project.model.User;
import com.example.h3t_project.sessionhelper.SessionManagement;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalInfomationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInfomationFragment extends Fragment {
  TextView txtLogin;
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public PersonalInfomationFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment PersonalInfomationFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static PersonalInfomationFragment newInstance(String param1, String param2) {
    PersonalInfomationFragment fragment = new PersonalInfomationFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view =  inflater.inflate(R.layout.fragment_personal_infomation, container, false);
    txtLogin = view.findViewById(R.id.txtLoginRegister);
    SessionManagement sessionManagement = new SessionManagement(getActivity());
    int userId = sessionManagement.getSessionUserId();
    if(userId != -1){
      UserDAO userDAO = new UserDAO();
      User user = userDAO.getUserById(userId);
      String fullname = user.getFullname();
      txtLogin.setText(fullname);
    } else {
      txtLogin.setText("Đăng nhập/Đăng ký");
      txtLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          userMoveToLoginPage();
        }
      });
    }
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(getActivity(), EditPersonalActivity.class);
          startActivity(intent);
        }
      });
  }
  private void userMoveToLoginPage() {
    Intent intent = new Intent(getActivity(), LoginActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }
}
