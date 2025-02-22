package com.projectdb.model;

import javafx.beans.property.*;

public class Season {

    private IntegerProperty idProperty;
    private IntegerProperty seriesidProperty;
    private IntegerProperty seasonnumProperty;
    private IntegerProperty episodenumProperty;
    private IntegerProperty releaseyearProperty;
    private StringProperty actorsProperty;

    public Season()
    {
        this.idProperty = new SimpleIntegerProperty();
        this.seriesidProperty = new SimpleIntegerProperty();
        this.seasonnumProperty = new SimpleIntegerProperty();
        this.episodenumProperty = new SimpleIntegerProperty();
        this.releaseyearProperty = new SimpleIntegerProperty();
        this.actorsProperty = new SimpleStringProperty();
    }

    public int getSeasonId()
    {
        return idProperty.get();
    }
    public void setSeasonId(int id)
    {
        this.idProperty.set(id);
    }
    public IntegerProperty getSeas_idProperty()
    {
        return idProperty;
    }

    public int getSeasonSeriesId()
    {
        return seriesidProperty.get();
    }
    
    public void setSeasonSeriesId(int id)
    {
        this.seriesidProperty.set(id);
    }
    
    public IntegerProperty getSeas_seriesidProperty()
    {
        return seriesidProperty;
    }
    
    public int getSeasonNNo()
    {
        return seasonnumProperty.get();
    }
    public void setSeasonNo(int num)
    {
        this.seasonnumProperty.set(num);
    }
    public IntegerProperty getSeas_noProperty()
    {
        return seasonnumProperty;
    }
    
    public int getSeasonEpisodeNum()
    {
        return episodenumProperty.get();
    }
    public void setSeasonEpisodeNum(int epno)
    {
        this.episodenumProperty.set(epno);
    }
    public IntegerProperty getSeas_episodenumProperty()
    {
        return episodenumProperty;
    }
    
    public int getSeasonReleaseYear()
    {
        return releaseyearProperty.get();
    }
    public void setSeasonReleaseYear(int ry)
    {
        this.releaseyearProperty.set(ry);
    }
    public IntegerProperty getSeas_releaseyearProperty()
    {
        return releaseyearProperty;
    }

    public String getSeasonActors()
    {
        return actorsProperty.get();
    }
    public void setSeasonActors(String actor)
    {
        this.actorsProperty.set(actor);
    }
    public StringProperty getSeas_actorsProperty()
    {
        return actorsProperty;
    }

    
    
    
}
