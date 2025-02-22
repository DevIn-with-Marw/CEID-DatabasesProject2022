package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminUserDAO {
    //--- FETCH RECORDS ---//
    
    public static ObservableList<AdminUser> getAllRecords(String sqlQuery) throws ClassNotFoundException, SQLException
    {
        try {
           ResultSet rsSet = DBUtil.DBexecute(App.connectDB,sqlQuery);
           ObservableList<AdminUser> auList = getAdminUserObjects(rsSet);
           rsSet.close();
           return auList;

        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<AdminUser> getAdminUserObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
    {     
        try {
            ObservableList<AdminUser> auList = FXCollections.observableArrayList();
            while (rsSet.next())
            {
                AdminUser tempAdminUser = new AdminUser();
                tempAdminUser.setAdminUserId(rsSet.getInt("user_id"));
                tempAdminUser.setAdminUserFname(rsSet.getString("first_name"));
                tempAdminUser.setAdminUserLname(rsSet.getString("last_name"));
                tempAdminUser.setAdminUserEmail(rsSet.getString("email"));
                tempAdminUser.setAdminUserRegDate(rsSet.getString("create_date"));
                tempAdminUser.setAdminUserActivity(rsSet.getInt("active"));

                auList.add(tempAdminUser);
                tempAdminUser = null;
            }
            return auList;
        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

   
    
}
