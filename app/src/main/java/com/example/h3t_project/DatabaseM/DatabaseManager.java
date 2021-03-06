package com.example.h3t_project.DatabaseM;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
  private static final String LOG = "DEBUG";
  private static final String ip = "34.92.72.160";   //34.92.72.160
  private static final String port = "1433";
  private static final String Classes = "net.sourceforge.jtds.jdbc.Driver";
  private static final String database = "H3TSTORE";
  private static final String username = "sa";
  private static final String password = "PhatFU@2020";
  public Connection connection;

  public static Connection connect() {
    Connection conn = null;
    String ConnURL = null;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    try {
      Class.forName(Classes);
      ConnURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";"
        + "databaseName=" + database + ";user=" + username + ";password=" + password + ";";
      conn = DriverManager.getConnection(ConnURL);
    } catch (SQLException e) {
      Log.d(LOG, e.getMessage());
      Log.i("Message : ", "SQL sai");
    } catch (ClassNotFoundException e) {
      Log.d(LOG, e.getMessage());
      Log.i("Message : ", "Class sai");
    }
    return conn;
  }

}
