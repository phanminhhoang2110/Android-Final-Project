package com.example.h3t_project.sessionhelper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.h3t_project.model.User;

public class SessionManagement {
  SharedPreferences sharedPreferences;
  SharedPreferences.Editor editor;
  private final String SHARED_PREF_NAME = "session";
  private final String SESSION_USER = "session_user";
  private final String SESSION_ROLE = "session_role";

  public SessionManagement(Context context) {
    sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    editor = sharedPreferences.edit();
  }

  public void saveSession(User user) {
    //save session of user whenever is logged in
    int id = user.getId();
    int roleId = user.getRoleId().getRoleId();
    editor.putInt(SESSION_USER, id);
    editor.putInt(SESSION_ROLE, roleId);
    editor.apply();
  }

  public int getSessionUserId() {
    //return user whose session is saved
    return sharedPreferences.getInt(SESSION_USER, -1);
  }

  public int getSessionRoleId() {
    //return user whose session is saved
    return sharedPreferences.getInt(SESSION_ROLE, -1);
  }

  public void removeSession() {
    editor.clear();
    editor.commit();
  }
}
