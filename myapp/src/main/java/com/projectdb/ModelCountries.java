package com.projectdb;

public class ModelCountries {
    int countriesID;
    String countriesName;

    public ModelCountries(int countriesID, String countriesName) {
        this.countriesID = countriesID;
        this.countriesName = countriesName;
    }

    public int getCountriesID() {
        return this.countriesID;
    }

    public void setCountriesID(int countriesID) {
        this.countriesID = countriesID;
    }

    public String getCountriesName() {
        return this.countriesName;
    }

    public void setCountriesName(String countriesName) {
        this.countriesName = countriesName;
    }

}
