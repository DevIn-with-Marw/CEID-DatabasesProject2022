package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EpisodeDAO {
    //--- FETCH RECORDS: episodes of a season ---//
    
    public static ObservableList<Episode> getAllRecords(Season selection) throws ClassNotFoundException, SQLException
    {
        String sqlQuery = "SELECT * FROM episode WHERE season_id ="+selection.getSeasonId();

        try {
           ResultSet rsSet = DBUtil.DBexecute(App.getConnection(), sqlQuery);
           ObservableList<Episode> episodesList = getEpisodeObjects(rsSet);
           rsSet.close();
           return episodesList;

        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Episode> getEpisodeObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
    {     
        try {
            ObservableList<Episode> episodesList = FXCollections.observableArrayList();
            while (rsSet.next())
            {
                Episode tempEpisode = new Episode();
                tempEpisode.setEpisodeId(rsSet.getInt("episode_id"));
                tempEpisode.setEpisodeTitle(rsSet.getString("title"));
                tempEpisode.setEpisodeSeasonId(rsSet.getInt("season_id"));
                tempEpisode.setEpisodeNum(rsSet.getInt("episode_num"));
                tempEpisode.setEpisodeLength(rsSet.getInt("episode_length"));
                tempEpisode.setEpisodeRating(rsSet.getString("rating"));


                episodesList.add(tempEpisode);
            }
            return episodesList;
        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
