package com.projectdb.model;

import javafx.beans.property.*;

public class RentalinjFilm {
    private Rental rental;
    private Film film;
    private FloatProperty price;

    public RentalinjFilm()
    {
        this.rental = new Rental();
        this.film = new Film();
        this.price = new SimpleFloatProperty();
    }

    public Film getFilm()
    {
        return film;
    }
    public Rental getRental()
    {
        return rental;
    }

    public float getRF_Price()
    {
        return price.get();
    }
    public void setRF_Price(Float price)
    {
        this.price.set(price);
    }
    public FloatProperty getRF_priceProperty()
    {
        return price;
    }
    
}
