package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RentalinjEpisodeinjSeasonDAO {
    //--- FETCH RECORDS ---//
    
    public static ObservableList<RentalinjEpisodeinjSeason> getAllRecords(String sqlQuery) throws ClassNotFoundException, SQLException
    {
        try {
           ResultSet rsSet = DBUtil.DBexecute(App.getConnection(),sqlQuery);
           ObservableList<RentalinjEpisodeinjSeason> joinedList = getRentalinjEpisodeinjSeasonObjects(rsSet);
           rsSet.close();
           return joinedList;

        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<RentalinjEpisodeinjSeason> getRentalinjEpisodeinjSeasonObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
    {     
        try {
            ObservableList<RentalinjEpisodeinjSeason> joinedList = FXCollections.observableArrayList();
            while (rsSet.next())
            {
                RentalinjEpisodeinjSeason tempRentalinjEpisodeinjSeason = new RentalinjEpisodeinjSeason();
                tempRentalinjEpisodeinjSeason.setRES_Price(rsSet.getFloat("amount"));
                tempRentalinjEpisodeinjSeason.getRental().setRentalEpisodelId(rsSet.getInt("episode_id"));
                tempRentalinjEpisodeinjSeason.getRental().setRentalId(rsSet.getInt("rental_id"));
                tempRentalinjEpisodeinjSeason.getRental().setRentalDate(rsSet.getString("rental_date"));
                //rest rental fields are not necessary: films are null and customer is @user_now.
                tempRentalinjEpisodeinjSeason.getSeason().setSeasonId(rsSet.getInt("season_id"));
                tempRentalinjEpisodeinjSeason.getSeason().setSeasonNo(rsSet.getInt("season_num"));
                tempRentalinjEpisodeinjSeason.getSeason().setSeasonSeriesId(rsSet.getInt("series_id"));
                //rest season fields after show info button is pressed
                tempRentalinjEpisodeinjSeason.getEpisode().setEpisodeNum(rsSet.getInt("episode_num"));
                tempRentalinjEpisodeinjSeason.getEpisode().setEpisodeLength(rsSet.getInt("episode_length"));
                tempRentalinjEpisodeinjSeason.getEpisode().setEpisodeTitle(rsSet.getString("episode.title"));
                tempRentalinjEpisodeinjSeason.getEpisode().setEpisodeRating(rsSet.getString("rating"));
                //
                tempRentalinjEpisodeinjSeason.setRES_SeriesTitle(rsSet.getString("series.title"));
                tempRentalinjEpisodeinjSeason.setRES_Price(rsSet.getFloat("amount"));
                
                joinedList.add(tempRentalinjEpisodeinjSeason);
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
