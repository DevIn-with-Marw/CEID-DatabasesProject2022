package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RentalinjFilmDAO {
    //--- FETCH RECORDS ---//
    
    public static ObservableList<RentalinjFilm> getAllRecords(String sqlQuery) throws ClassNotFoundException, SQLException
    {
        try {
           ResultSet rsSet = DBUtil.DBexecute(App.getConnection(),sqlQuery);
           ObservableList<RentalinjFilm> joinedList = getRentalinjFilmObjects(rsSet);
           rsSet.close();
           return joinedList;

        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<RentalinjFilm> getRentalinjFilmObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
    {     
        try {
            ObservableList<RentalinjFilm> joinedList = FXCollections.observableArrayList();
            while (rsSet.next())
            {
                RentalinjFilm tempRentalinjFilm = new RentalinjFilm();
                tempRentalinjFilm.getRental().setRentalFIlmId(rsSet.getInt("film_id"));
                tempRentalinjFilm.getRental().setRentalId(rsSet.getInt("rental_id"));
                tempRentalinjFilm.getRental().setRentalDate(rsSet.getString("rental_date"));
                //rest rental fields are not necessary: episodes are null and customer is @user_now.
                tempRentalinjFilm.getFilm().setFilmTitle(rsSet.getString("title"));
                tempRentalinjFilm.getFilm().setFilmLength(rsSet.getInt("film_length"));
                //rest film fields are not necessary
                tempRentalinjFilm.setRF_Price(rsSet.getFloat("amount"));

                joinedList.add(tempRentalinjFilm);
            }
            return joinedList;
        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    
}
