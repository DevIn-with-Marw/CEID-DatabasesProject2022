package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
    
public class FilmDAO
{
    //--- FETCH RECORDS ---//
    
    public static ObservableList<Film> getAllRecords(String sqlQuery) throws ClassNotFoundException, SQLException
    {
        try {
           ResultSet rsSet = DBUtil.DBexecute(App.getConnection(),sqlQuery);
           ObservableList<Film> filmList = getFilmObjects(rsSet);
           rsSet.close();
           return filmList;

        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Film> getFilmObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
    {     
        try {
            ObservableList<Film> filmList = FXCollections.observableArrayList();
            while (rsSet.next())
            {
                Film tempFilm = new Film();
                tempFilm.setFilmId(rsSet.getInt("film_id"));
                tempFilm.setFilmTitle(rsSet.getString("title"));
                tempFilm.setFilmDescription(rsSet.getString("film_description"));
                tempFilm.setFilmReleaseYear(rsSet.getInt("release_year"));
                tempFilm.setFilmLanguage(rsSet.getString("lang_name"));
                tempFilm.setFilmLength(rsSet.getInt("film_length"));
                tempFilm.setFilmRating(rsSet.getString("rating"));
                tempFilm.setFilmSpecialfeatures(rsSet.getString("special_features"));

                String query1 = "select catname from category inner join film_category on category.category_id = film_category.category_id inner join film on film.film_id = film_category.film_id where film.film_id ="+tempFilm.getFilmId()+";";
                ResultSet category = DBUtil.DBexecute(App.connectDB, query1);
                String tempcat = "";

                try {
                    while (category.next()) 
                    {
                        if (tempFilm.getFilmCategory()==null) tempFilm.setFilmCategory(category.getString("catname")+", ");
                        else {
                            tempcat = tempFilm.getFilmCategory().concat(category.getString("catname")+", ");
                            tempFilm.setFilmCategory(tempcat);
                        }

                    }
                    tempFilm.setFilmCategory(tempFilm.getFilmCategory().substring(0, tempFilm.getFilmCategory().length() - 2));
                }catch (Exception e)
                {
                    tempFilm.setFilmCategory("");
                }

                category.close();                

                String query2 = "select lang_name from film inner join lang on film.original_language_id = lang.lang_id where film.film_id ="+tempFilm.getFilmId()+";";
                ResultSet origlangs = DBUtil.DBexecute(App.connectDB, query2);
                if (origlangs.next()) tempFilm.setFilmOriginalLanguage(origlangs.getString("lang_name"));

                origlangs.close();

                String query3 = "select first_name, last_name from actor inner join film_actor on actor.actor_id = film_actor.actor_id inner join film on film.film_id = film_actor.film_id where film.film_id="+tempFilm.getFilmId()+";";
                ResultSet actors = DBUtil.DBexecute(App.connectDB, query3);
                String temp="";
                
                try {
                    while (actors.next()) 
                    {
                        if (tempFilm.getFilmActors()==null)
                            tempFilm.setFilmActors(actors.getString("first_name")+" "+actors.getString("last_name")+", ");
                        else {
                            temp = tempFilm.getFilmActors().concat(actors.getString("first_name")+" "+actors.getString("last_name")+", ");
                            tempFilm.setFilmActors(temp);
                        }

                    }
                    tempFilm.setFilmActors(tempFilm.getFilmActors().substring(0, tempFilm.getFilmActors().length() - 2));
        
                }catch (Exception e)
                {
                    tempFilm.setFilmActors("");
                }
                actors.close();

                filmList.add(tempFilm);
            }
            rsSet.close();
            return filmList;
        }catch (SQLException e)
        {
            System.out.println("Error while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

}
    
    
