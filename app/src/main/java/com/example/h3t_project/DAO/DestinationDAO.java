package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.Destination;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
