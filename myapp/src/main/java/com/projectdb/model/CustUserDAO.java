package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustUserDAO {
     //--- FETCH RECORDS ---//
    
     public static ObservableList<CustUser> getAllRecords(String sqlQuery) throws ClassNotFoundException, SQLException
     {
         try {
            ResultSet rsSet = DBUtil.DBexecute(App.connectDB,sqlQuery);
            ObservableList<CustUser> cuList = getCustUserObjects(rsSet);
            rsSet.close();
            return cuList;
 
         }catch (SQLException e)
         {
             System.out.println("Error while fetching the records from DB" + e);
             e.printStackTrace();
             throw e;
         }
 
     }
 
     private static ObservableList<CustUser> getCustUserObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
     {     
         try {
             ObservableList<CustUser> cuList = FXCollections.observableArrayList();
             while (rsSet.next())
             {
                 CustUser tempCustUser = new CustUser();
                 tempCustUser.setCustUserId(rsSet.getInt("user_id"));
                 tempCustUser.setCustUserFname(rsSet.getString("first_name"));
                 tempCustUser.setCustUserLname(rsSet.getString("last_name"));
                 tempCustUser.setCustUserType(rsSet.getString("cust_type"));
                 tempCustUser.setCustUserEmail(rsSet.getString("email"));
                 tempCustUser.getCustUserAddress().setAddressName(rsSet.getString("address_name"));
                 tempCustUser.setCustUserRegDate(rsSet.getString("create_date"));
                 tempCustUser.setCustUserActivity(rsSet.getInt("active"));
 
                 cuList.add(tempCustUser);
             }
             return cuList;
         }catch (SQLException e)
         {
             System.out.println("Error while fetching the records from DB" + e);
             e.printStackTrace();
             throw e;
         }
     }

    
}
