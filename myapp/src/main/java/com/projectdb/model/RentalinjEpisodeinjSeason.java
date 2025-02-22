package com.projectdb.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RentalinjEpisodeinjSeason {
    private Rental rental;
    private Episode episode;
    private Season season;
    private StringProperty seriestitle;
    private FloatProperty price;

    public RentalinjEpisodeinjSeason()
    {
        this.rental = new Rental();
        this.episode = new Episode();
        this.season = new Season();
        this.seriestitle = new SimpleStringProperty();
        this.price = new SimpleFloatProperty();
    }

    public Rental getRental()
    {
        return rental;
    }
    public Episode getEpisode()
    {
        return episode;
    }
    public Season getSeason()
    {
        return season;
    }
    
    public String getRES_SeriesTitle()
    {
        return seriestitle.get();
    }
    public void setRES_SeriesTitle(String title)
    {
        this.seriestitle.set(title);
    }
    public StringProperty getRES_SeriesTitleProperty()
    {
        return seriestitle;
    }

    public float getRES_Price()
    {
        return price.get();
    }
    public void setRES_Price(float price)
    {
        this.price.set(price);
    }
    public FloatProperty getRES_priceProperty()
    {
        return price;
    }

    
    
}
