package com.example.h3t_project.DAO;

import android.util.Log;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.Destination;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DestinationDAO extends DatabaseManager {
  PreparedStatement preparedStatement = null;
  ResultSet rs = null ;
  public List<Destination> getDestinationByUser(int userId){
    List<Destination> destinations = null;
    String query = "SELECT [destination_id]\n" +
      "      , tbl_destinations.[address], tbl_destinations.ward, tbl_destinations.district, tbl_destinations.[province]\n" +
      "       FROM [H3TSTORE].[dbo].[tbl_destionation_user] inner join tbl_destinations \n" +
      "       ON tbl_destionation_user.destination_id = tbl_destinations.id\n" +
      "       where user_id = ?";
    try{
      connection = connect();
      destinations = new ArrayList<>();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1,userId);
      rs = preparedStatement.executeQuery();
      while(rs.next()){
        Destination destination = new Destination();
        destination.setId(rs.getInt("destination_id"));
        destination.setAddress(rs.getString("address"));
        destination.setWard(rs.getString("ward"));
        destination.setDistrict(rs.getString("district"));
        destination.setProvince(rs.getString("province"));
        destinations.add(destination);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return destinations;
  }

  public boolean addNewDestination(int userId,Destination destinationObj){
    PreparedStatement destinationPreparedStatement;
    PreparedStatement destinationUserPreparedStatement;
    boolean destinationDone = false;
    boolean destination_userDone = false;
    String destination = "INSERT INTO [dbo].[tbl_destinations] VALUES ( ? , ? , ? , ? )";
    String destination_user = "INSERT INTO [dbo].[tbl_destionation_user] VALUES ( ? , ?)";
    try{
      connection = connect();
      connection.setAutoCommit(false);
      destinationPreparedStatement = connection.prepareStatement(destination,Statement.RETURN_GENERATED_KEYS);
      destinationPreparedStatement.setString(1,destinationObj.getAddress());
      destinationPreparedStatement.setString(2,destinationObj.getProvince());
      destinationPreparedStatement.setString(3,destinationObj.getDistrict());
      destinationPreparedStatement.setString(4,destinationObj.getWard());
      destinationDone = destinationPreparedStatement.execute();
      int idLatest = 0;
      try (ResultSet generatedKeys = destinationPreparedStatement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          idLatest = (int) generatedKeys.getLong(1);
        }
      }
      destinationUserPreparedStatement = connection.prepareStatement(destination_user);
      destinationUserPreparedStatement.setInt(1,idLatest);
      destinationUserPreparedStatement.setInt(2, userId);
      destination_userDone = destinationUserPreparedStatement.execute();
      connection.commit();

    }catch (Exception e){
      try {
        connection.rollback();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    if(destination_userDone && destinationDone){
      return true;
    }
    return false;
  }

}
