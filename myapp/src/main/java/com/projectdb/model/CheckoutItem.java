package com.projectdb.model;

import java.sql.ResultSet;

import com.projectdb.App;
import com.projectdb.util.DBUtil;

import javafx.beans.property.*;

public class CheckoutItem {

    private StringProperty typeProperty;
    private StringProperty titleProperty;
    private FloatProperty priceProperty;

    private Film film;
    private Episode episode;

    //match with a film or episode in some way

    public CheckoutItem()
    {
        this.film = new Film();
        this.episode = new Episode();
        this.typeProperty = new SimpleStringProperty();
        this.titleProperty = new SimpleStringProperty();
        this.priceProperty = new SimpleFloatProperty();
        
    }


    public Episode getEpisode()
    {
        return episode;
    }
    public void setEpisode(Episode ep)
    {
        this.episode = ep;
    }

    public Film getFilm()
    {
        return film;
    }
    public void setFilm(Film f)
    {
        this.film = f;
    }

    public Float getCheckoutItemPrice()
    {
        return priceProperty.get();
    }
    public void setCheckoutItemPrice() throws Exception
    {

        ResultSet topaySet = DBUtil.DBpaydecisioncall(App.getConnection(), this.getCheckoutItemType());

        if (topaySet.next()) 
        {
            this.priceProperty.set(topaySet.getFloat("to_pay"));
        }

    }
    public FloatProperty getChIt_priceProperty()
    {
        return priceProperty;
    }

    public String getCheckoutItemTitle()
    {
        return titleProperty.get();
    }
    public void setCheckoutItemTitle(String title)
    {
        this.titleProperty.set(title);
    }
    public StringProperty getChIt_titleProperty()
    {
        return titleProperty;
    }
    
    public String getCheckoutItemType()
    {
        return typeProperty.get();
    }
    public void setCheckoutItemType(String type)
    {
        this.typeProperty.set(type);
    }
    public StringProperty getChIt_typeProperty()
    {
        return typeProperty;
    }


}
