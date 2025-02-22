package com.projectdb.model;

import javafx.beans.property.*;

public class Episode {

    private IntegerProperty idProperty;
    private StringProperty titleProperty;
    private IntegerProperty seasonidProperty;
    private IntegerProperty episodenumProperty;
    private IntegerProperty episodelengthProperty;
    private StringProperty ratingProperty;

    public Episode()
    {
        this.idProperty = new SimpleIntegerProperty();
        this.titleProperty = new SimpleStringProperty();
        this.seasonidProperty = new SimpleIntegerProperty();
        this.episodenumProperty = new SimpleIntegerProperty();
        this.episodelengthProperty = new SimpleIntegerProperty();
        this.ratingProperty = new SimpleStringProperty();
    }

    //---- SETTERS/GETTERS ----//

    //id (value + property)//
    public int getEpisodeId()
    {
        return idProperty.get();
    }
    public void setEpisodeId(int id)
    {
        this.idProperty.set(id);
    }
    public IntegerProperty getEp_idProperty()
    {
        return idProperty;
    }

    //title (value + property)//
    public String getEpisodeTitle()
    {
        return titleProperty.get();
    }
    public void setEpisodeTitle(String title)
    {
        this.titleProperty.set(title);
    }
    public StringProperty getEp_titleProperty()
    {
        return titleProperty;
    }

    //season-id (value + property)//
    public int getEpisodeSeasonId()
    {
        return seasonidProperty.get();
    }
    public void setEpisodeSeasonId(int seasid)
    {
        this.seasonidProperty.set(seasid);
    }
    public IntegerProperty getEp_seasidProperty()
    {
        return seasonidProperty;
    }

    //episode number (value + property)//
    public int getEpisodeNum()
    {
        return episodenumProperty.get();
    }
    public void setEpisodeNum(int num)
    {
        this.episodenumProperty.set(num);
    }
    public IntegerProperty getEp_numProperty()
    {
        return episodenumProperty;
    }

    //episode length (value + property)//
    public int getEpisodeLength()
    {
        return episodelengthProperty.get();
    }
    public void setEpisodeLength(int length)
    {
        this.episodelengthProperty.set(length);
    }
    public IntegerProperty getEp_lengthProperty()
    {
        return episodelengthProperty;
    }

    //episode rating (value + property)//
    public String getEpisodeRating()
    {
        return ratingProperty.get();
    }
    public void setEpisodeRating(String rate)
    {
        this.ratingProperty.set(rate);
    }
    public StringProperty getEp_ratingProperty()
    {
        return ratingProperty;
    }

    
    
}
