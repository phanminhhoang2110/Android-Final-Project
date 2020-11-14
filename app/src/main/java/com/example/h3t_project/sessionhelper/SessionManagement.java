package com.example.h3t_project.sessionhelper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.h3t_project.DAO.RoleDAO;
import com.example.h3t_project.model.Role;
import com.example.h3t_project.model.User;

import java.util.List;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String SHARED_PREF_NAME = "session";
    private String SESSION_USER = "session_user";
    private String SESSION_ROLE = "session_role";

    public  SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user){
        //save session of user whenever is logged in
        int id= user.getId();
        int roleId = user.getRoleId().getRoleId();
        editor.putInt(SESSION_USER, id).commit();
        editor.putInt(SESSION_ROLE, roleId).commit();
    }

    public int getSessionUserId(){
        //return user whose session is saved
        return sharedPreferences.getInt(SESSION_USER, -1);
    }

    public int getSessionRoleId(){
        //return user whose session is saved
        return sharedPreferences.getInt(SESSION_ROLE, -1);
    }

    public  void removeSession(){
        editor.clear();
        editor.commit();
    }
}
