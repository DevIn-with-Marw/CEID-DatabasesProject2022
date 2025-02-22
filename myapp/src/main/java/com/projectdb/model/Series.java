package com.projectdb.model;

import javafx.beans.property.*;

public class Series {
    
    //fields as properties (non-primitive type)
    private IntegerProperty idProperty;
    private StringProperty titleProperty;
    private StringProperty descriptionProperty;
    private IntegerProperty releaseyearProperty;
    private StringProperty langProperty;
    private StringProperty original_langProperty;
    private StringProperty ratingProperty;
    private StringProperty categoryProperty;

    //constructor
    public Series()
    {
        this.idProperty = new SimpleIntegerProperty();
        this.titleProperty = new SimpleStringProperty();
        this.descriptionProperty = new SimpleStringProperty();
        this.releaseyearProperty = new SimpleIntegerProperty();
        this.langProperty = new SimpleStringProperty();
        this.original_langProperty = new SimpleStringProperty();
        this.ratingProperty = new SimpleStringProperty();
        this.categoryProperty = new SimpleStringProperty();
    }

    //--- SETTERS AND GETTERS ---//
    //Series Id (Value + Property)//
    public int getSeriesId()
    {
        return idProperty.get();
    }
    public void setSeriesId(int id)
    {
        this.idProperty.set(id);
    }
    public IntegerProperty getSidProperty()
    {
        return idProperty;
    }

    //Series Title (Value + Property)//
    public String getSeriesTitle()
    {
        return titleProperty.get();
    }
    public void setSeriesTitle(String title)
    {
        this.titleProperty.set(title);
    }
    public StringProperty getSTitleProperty()
    {
        return titleProperty;
    }

     //Series Description (Value + Property)//
    public String getSeriesDescription()
    {
        return descriptionProperty.get();
    }
    public void setSeriesDescription(String description)
    {
        this.descriptionProperty.set(description);
    }
    public StringProperty getSDescriptionProperty()
    {
        return descriptionProperty;
    }

     //Series Release Year (Value + Property)//
    public int getSeriesRealeaseYear()
    {
        return releaseyearProperty.get();
    }
    public void setSeriesReleaseYear(int ry)
    {
        this.releaseyearProperty.set(ry);
    }
    public IntegerProperty getSReleaseYearProperty()
    {
        return releaseyearProperty;
    }

    //Series Language Id (Value + Property)//
    public String getSeriesLanguage()
    {
        return langProperty.get();
    }
    public void setSeriesLanguage(String lang)
    {
        this.langProperty.set(lang);
    }
    public StringProperty getSLanguageProperty()
    {
        return langProperty;
    }

    //Series Original Language Id (Value + Property)//
    public String getSeriesOriginalLanguage()
    {
        return original_langProperty.get();
    }
    public void setSeriesOriginalLanguage(String lang)
    {
        this.original_langProperty.set(lang);
    }
    public StringProperty getSOriginalLanguageProperty()
    {
        return original_langProperty;
    }

    //Series Rating (Value + Property)//
    public String getSeriesRating()
    {
        return ratingProperty.get();
    }
    public void setSeriesRating(String rate)
    {
        this.ratingProperty.set(rate);
    }
    public StringProperty getSRatingProperty()
    {
        return ratingProperty;
    }

    public String getSeriesCategory()
    {
        return categoryProperty.get();
    }
    public void setSeriesCategory(String catname)
    {
        this.categoryProperty.set(catname);
    }
    public StringProperty getSCategoryProperty()
    {
        return categoryProperty;
    }

}
