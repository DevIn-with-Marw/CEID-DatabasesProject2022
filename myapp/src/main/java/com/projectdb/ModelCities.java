package com.projectdb;

public class ModelCities {
    int citiesID;
    String citiesName;
    int citiesCountryID;
    String countryName;

    public ModelCities(int citiesID, String citiesName, int citiesCountryID, String countryName) {
        this.citiesID = citiesID;
        this.citiesName = citiesName;
        this.citiesCountryID = citiesCountryID;
        this.countryName = countryName;
    }

    public int getCitiesID() {
        return this.citiesID;
    }

    public void setCitiesID(int citiesID) {
        this.citiesID = citiesID;
    }

    public String getCitiesName() {
        return this.citiesName;
    }

    public void setCitiesName(String citiesName) {
        this.citiesName = citiesName;
    }

    public int getCitiesCountryID() {
        return this.citiesCountryID;
    }

    public void setCitiesCountryID(int citiesCountryID) {
        this.citiesCountryID = citiesCountryID;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
