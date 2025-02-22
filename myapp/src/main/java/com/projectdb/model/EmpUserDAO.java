package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmpUserDAO {
     //--- FETCH RECORDS ---//
    
     public static ObservableList<EmpUser> getAllRecords(String sqlQuery) throws ClassNotFoundException, SQLException
     {
         try {
            ResultSet rsSet = DBUtil.DBexecute(App.connectDB,sqlQuery);
            ObservableList<EmpUser> euList = getEmpUserObjects(rsSet);
            rsSet.close();
            return euList;
 
         }catch (SQLException e)
         {
             System.out.println("Error while fetching the records from DB" + e);
             e.printStackTrace();
             throw e;
         }
 
     }
 
     private static ObservableList<EmpUser> getEmpUserObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
     {     
         try {
             ObservableList<EmpUser> euList = FXCollections.observableArrayList();
             while (rsSet.next())
             {
                 EmpUser tempEmpUser = new EmpUser();
                 tempEmpUser.setEmpUserId(rsSet.getInt("user_id"));
                 tempEmpUser.setEmpUserFname(rsSet.getString("first_name"));
                 tempEmpUser.setEmpUserLname(rsSet.getString("last_name"));
                 tempEmpUser.setEmpUserRegDate(rsSet.getString("create_date"));
                 tempEmpUser.setEmpUserActivity(rsSet.getInt("active"));
                 tempEmpUser.setEmpUserPhone(rsSet.getString("phone"));
                 tempEmpUser.setEmpUserEmail(rsSet.getString("email"));
 
                 euList.add(tempEmpUser);
                 tempEmpUser = null;
             }
             return euList;
         }catch (SQLException e)
         {
             System.out.println("Error while fetching the records from DB" + e);
             e.printStackTrace();
             throw e;
         }
     }

}
