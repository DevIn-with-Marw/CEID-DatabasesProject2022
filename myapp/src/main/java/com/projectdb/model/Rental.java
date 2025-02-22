package com.projectdb.model;

import javafx.beans.property.*;

public class Rental {
    private IntegerProperty idProperty;
    private StringProperty datetimeProperty;
    private IntegerProperty filmidProperty;
    private IntegerProperty episodeidProperty;
    private IntegerProperty customeridProperty;

    public Rental()
    {
        this.idProperty = new SimpleIntegerProperty();
        this.datetimeProperty = new SimpleStringProperty();
        this.filmidProperty = new SimpleIntegerProperty();
        this.episodeidProperty = new SimpleIntegerProperty();
        this.customeridProperty = new SimpleIntegerProperty();
    }

    public int getRentalId()
    {
        return idProperty.get();
    }
    public void setRentalId(int id)
    {
        this.idProperty.set(id);
    }
    public IntegerProperty getR_idProperty()
    {
        return idProperty;
    }

    public String getRentalDate()
    {
        return datetimeProperty.get();
    }
    public void setRentalDate(String datetime)
    {
        this.datetimeProperty.set(datetime);
    }
    public StringProperty getR_datetimeProperty()
    {
        return datetimeProperty;
    }

    public int getRentalFilmId()
    {
        return filmidProperty.get();
    }
    public void setRentalFIlmId(int id)
    {
        this.filmidProperty.set(id);
    }
    public IntegerProperty getR_filmidProperty()
    {
        return filmidProperty;
    }

    public int getRentalEpisodeId()
    {
        return episodeidProperty.get();
    }
    public void setRentalEpisodelId(int id)
    {
        this.episodeidProperty.set(id);
    }
    public IntegerProperty getR_episodeidProperty()
    {
        return episodeidProperty;
    }

    public int getRentalCustomerId()
    {
        return customeridProperty.get();
    }
    public void setRentalCustomerId(int id)
    {
        this.customeridProperty.set(id);
    }
    public IntegerProperty getR_customeridProperty()
    {
        return customeridProperty;
    }
}
