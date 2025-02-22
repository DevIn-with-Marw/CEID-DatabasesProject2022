package com.projectdb.model;

import javafx.beans.property.*;


public class Film {
    
    //fields as properties (non-primitive type)
    private IntegerProperty idProperty;
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private IntegerProperty releaseyearProperty;
    private StringProperty langnameProperty;
    private StringProperty original_langnameProperty;
    private IntegerProperty lengthProperty;
    private StringProperty ratingProperty;
    private StringProperty featuresProperty;
    private StringProperty categoryProperty;
    private StringProperty actorsProperty;

    //constructor
    public Film()
    {
        this.idProperty = new SimpleIntegerProperty();
        this.titleProperty = new SimpleStringProperty();
        this.descriptionProperty = new SimpleStringProperty();
        this.releaseyearProperty = new SimpleIntegerProperty();
        this.langnameProperty = new SimpleStringProperty();
        this.original_langnameProperty = new SimpleStringProperty();
        this.lengthProperty = new SimpleIntegerProperty();
        this.ratingProperty = new SimpleStringProperty();
        this.featuresProperty = new SimpleStringProperty();
        this.categoryProperty = new SimpleStringProperty();
        this.actorsProperty = new SimpleStringProperty();
    }

    //--- SETTERS AND GETTERS ---//
    //Film Id (Value + Property)//
    public int getFilmId()
    {
        return idProperty.get();
    }
    public void setFilmId(int id)
    {
        this.idProperty.set(id);
    }
    public IntegerProperty getFidProperty()
    {
        return idProperty;
    }

    //Film Title (Value + Property)//
    public String getFilmTitle()
    {
        return titleProperty.get();
    }
    public void setFilmTitle(String title)
    {
        this.titleProperty.set(title);
    }
    public StringProperty getFTitleProperty()
    {
        return titleProperty;
    }

     //Film Description (Value + Property)//
    public String getFilmDescription()
    {
        return descriptionProperty.get();
    }
    public void setFilmDescription(String description)
    {
        this.descriptionProperty.set(description);
    }
    public StringProperty getFDescriptionProperty()
    {
        return descriptionProperty;
    }

     //Film Release Year (Value + Property)//
    public int getFilmRealeaseYear()
    {
        return releaseyearProperty.get();
    }
    public void setFilmReleaseYear(int ry)
    {
        this.releaseyearProperty.set(ry);
    }
    public IntegerProperty getFReleaseYearProperty()
    {
        return releaseyearProperty;
    }

    //Film Language Id (Value + Property)//
    public String getFilmLanguage()
    {
        return langnameProperty.get();
    }
    public void setFilmLanguage(String lang)
    {
        this.langnameProperty.set(lang);
    }
    public StringProperty getFLanguageProperty()
    {
        return langnameProperty;
    }

    //Film Original Language Id (Value + Property)//
    public String getFilmOriginalLanguage()
    {
        return original_langnameProperty.get();
    }
    public void setFilmOriginalLanguage(String lang)
    {
        this.original_langnameProperty.set(lang);
    }
    public StringProperty getFOriginalLanguageProperty()
    {
        return original_langnameProperty;
    }

    //Film Length (Value + Property)//
    public int getFilmLength()
    {
        return lengthProperty.get();
    }
    public void setFilmLength(int minutes)
    {
        this.lengthProperty.set(minutes);
    }
    public IntegerProperty getFLengthProperty()
    {
        return lengthProperty;
    }

    //Film Rating (Value + Property)//
    public String getFilmRating()
    {
        return ratingProperty.get();
    }
    public void setFilmRating(String rate)
    {
        this.ratingProperty.set(rate);
    }
    public StringProperty getFRatingProperty()
    {
        return ratingProperty;
    }

    //Film Special Features (Value + Property)//
    public String getFilmSpecialfeatures()
    {
        return featuresProperty.get();
    }
    public void setFilmSpecialfeatures(String spfeat)
    {
        this.featuresProperty.set(spfeat);
    }
    public StringProperty getFSpecialfeaturesProperty()
    {
        return featuresProperty;
    }

    public String getFilmCategory()
    {
        return categoryProperty.get();
    }
    public void setFilmCategory(String catname)
    {
        this.categoryProperty.set(catname);
    }
    public StringProperty getFCategoryProperty()
    {
        return categoryProperty;
    }

    public String getFilmActors()
    {
        return actorsProperty.get();
    }
    public void setFilmActors(String actor)
    {
        this.actorsProperty.set(actor);
    }
    public StringProperty getFActorsProperty()
    {
        return actorsProperty;
    }

}
