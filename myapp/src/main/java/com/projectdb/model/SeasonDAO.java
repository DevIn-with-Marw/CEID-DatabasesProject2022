package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SeasonDAO {

    //--- FETCH RECORDS: seasons of a series ---//
    
    public static ObservableList<Season> getAllRecords(Series selection) throws ClassNotFoundException, SQLException
    {
        String sqlQuery = "SELECT * FROM season WHERE series_id ="+selection.getSeriesId();

        try {
           ResultSet rsSet = DBUtil.DBexecute(App.getConnection(), sqlQuery);
           ObservableList<Season> seasonsList = getSeasonObjects(rsSet);
           rsSet.close();
           return seasonsList;

        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Season> getSeasonObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
    {     
        try {
            ObservableList<Season> seasonsList = FXCollections.observableArrayList();
            while (rsSet.next())
            {
                Season tempSeason = new Season();
                tempSeason.setSeasonId(rsSet.getInt("season_id"));
                tempSeason.setSeasonSeriesId(rsSet.getInt("series_id"));
                tempSeason.setSeasonNo(rsSet.getInt("season_num"));
                tempSeason.setSeasonEpisodeNum(rsSet.getInt("num_of_episodes"));
                tempSeason.setSeasonReleaseYear(rsSet.getInt("release_year"));

                String query3 = "select first_name, last_name from actor inner join season_actor on actor.actor_id = season_actor.actor_id inner join season on season.season_id = season_actor.in_season where season.season_id="+tempSeason.getSeasonId()+";";
                ResultSet actors = DBUtil.DBexecute(App.connectDB, query3);
                String temp="";
                
                try {
                    while (actors.next()) 
                    {
                        if (tempSeason.getSeasonActors()==null)
                            tempSeason.setSeasonActors(actors.getString("first_name")+" "+actors.getString("last_name")+", ");
                        else {
                            temp = tempSeason.getSeasonActors().concat(actors.getString("first_name")+" "+actors.getString("last_name")+", ");
                            tempSeason.setSeasonActors(temp);
                        }

                    }
                    tempSeason.setSeasonActors(tempSeason.getSeasonActors().substring(0, tempSeason.getSeasonActors().length() - 2));
        
                }catch (Exception e)
                {
                    tempSeason.setSeasonActors("");
                }
                actors.close();


                seasonsList.add(tempSeason);
            }
            return seasonsList;
        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
    
}
