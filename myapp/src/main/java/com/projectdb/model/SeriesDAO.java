package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
    
public class SeriesDAO
{
    //--- FETCH RECORDS ---//
    
    public static ObservableList<Series> getAllRecords() throws ClassNotFoundException, SQLException
    {
        String sqlQuery = "SELECT * FROM series inner join lang on series.language_id = lang.lang_id;";

        try {
           ResultSet rsSet = DBUtil.DBexecute(App.getConnection(), sqlQuery);
           ObservableList<Series> seriesList = getSeriesObjects(rsSet);
           rsSet.close();
           return seriesList;

        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Series> getSeriesObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
    {     
        try {
            ObservableList<Series> seriesList = FXCollections.observableArrayList();
            while (rsSet.next())
            {
                Series tempSeries = new Series();
                tempSeries.setSeriesId(rsSet.getInt("series_id"));
                tempSeries.setSeriesTitle(rsSet.getString("title"));
                tempSeries.setSeriesDescription(rsSet.getString("series_description"));
                tempSeries.setSeriesReleaseYear(rsSet.getInt("release_year"));
                tempSeries.setSeriesLanguage(rsSet.getString("lang_name"));
                tempSeries.setSeriesOriginalLanguage(rsSet.getString("original_language_id"));
                tempSeries.setSeriesRating(rsSet.getString("rating"));

                String query1 = "select catname from category inner join series_category on category.category_id = series_category.category_id inner join series on series.series_id = series_category.series_id where series.series_id ="+tempSeries.getSeriesId()+";";
                ResultSet category = DBUtil.DBexecute(App.connectDB, query1);
                String tempcat = "";

                try {
                    while (category.next()) 
                    {
                        if (tempSeries.getSeriesCategory()==null) tempSeries.setSeriesCategory(category.getString("catname")+", ");
                        else {
                            tempcat = tempSeries.getSeriesCategory().concat(category.getString("catname")+", ");
                            tempSeries.setSeriesCategory(tempcat);
                        }

                    }
                    tempSeries.setSeriesCategory(tempSeries.getSeriesCategory().substring(0, tempSeries.getSeriesCategory().length() - 2));
                }catch (Exception e)
                {
                    tempSeries.setSeriesCategory("");
                }
                category.close();
                
                String query2 = "select lang_name from series inner join lang on series.original_language_id = lang.lang_id where series.series_id ="+tempSeries.getSeriesId()+";";
                ResultSet origlangs = DBUtil.DBexecute(App.connectDB, query2);
                if (origlangs.next()) tempSeries.setSeriesOriginalLanguage(origlangs.getString("lang_name"));
                
                origlangs.close();
                seriesList.add(tempSeries);
            }
            rsSet.close();
            return seriesList;
        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

}
    
    
