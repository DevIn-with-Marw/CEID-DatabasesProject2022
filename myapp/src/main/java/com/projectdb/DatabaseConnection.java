package com.projectdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "project2022_final";
        String databaseUser = "root";
        String databasePassword = "maria";
        
        try {
            databaseLink = DriverManager.getConnection("jdbc:mariadb://localhost:3306/" + databaseName + "?user=" + databaseUser + "&password=" + databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
