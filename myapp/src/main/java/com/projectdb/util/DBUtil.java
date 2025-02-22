package com.projectdb.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.projectdb.CustomerHomeController;

public class DBUtil {
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String connString = "jdbc:mariadb://localhost:3306/projectdb";

    public static Connection DBconnect() throws ClassNotFoundException, SQLException
    {
        Connection connection=null;
        try {
            Class.forName(JDBC_DRIVER);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("MariaDB Driver Not Found");
            e.printStackTrace();
            throw e;
        }

        //System.out.println("JDBC Driver Has Been Registered!");

        try{
            connection = DriverManager.getConnection(connString, "root", "maria");
        }
        catch(SQLException e)
        {
            System.out.println("Connection failed! Check output console" +e);
            throw e;
        }

        return connection;
    }

    public static void DBdisconnect(Connection connection) throws SQLException
    {
        try 
        {
            if (connection != null && !connection.isClosed())
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //for insert, delete, udate operations
    public static void DBexecuteQuery(Connection connection, String query) throws SQLException, ClassNotFoundException
    {
        Statement stm = null;

        try {
            stm = connection.createStatement();
            stm.executeUpdate(query);

        }
        catch (SQLException e)
        {
            System.out.println("Error occured during query excecution."+e);
            throw e;
        }
        finally
        {
            if (stm !=null) stm.close();
        }

    }

    //for fetching items from the db (returns result set)
    public static ResultSet DBexecute(Connection connection, String query) throws ClassNotFoundException, SQLException
    {
        Statement stm = null;
        ResultSet resSet = null;

        try {
            stm = connection.createStatement();
            resSet = stm.executeQuery(query);
            return resSet;
        }
        catch (SQLException e)
        {
            System.out.println("Error occured in DBexecute" + e);
            throw e;
        }
        finally
        {
            if (stm !=null) stm.close();
        }
    }
    
    public static ResultSet DBpaydecisioncall(Connection connection, String producttype) throws SQLException, ClassNotFoundException
    {
        String call = "call pay4product(?,?)";
        ResultSet rs;
        try (CallableStatement stm = connection.prepareCall(call)){
                stm.setString(1, producttype);
                stm.setInt(2, CustomerHomeController.usernowid);
                rs = stm.executeQuery();
                stm.close();
                return rs;
               
        } catch (Exception e)
        {
            System.out.println("Exception occured while calling pay4product: "+e);
            throw e;
        }
    }
}


