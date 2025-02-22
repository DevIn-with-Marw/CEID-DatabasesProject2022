package com.projectdb;

public class ModelSeries {
    int seriesID;
    String seriesTitle;
    String seriesDesc;
    int seriesYear;
    int seriesSubs;
    int seriesLang;
    String seriesRating;

    public ModelSeries(int seriesID, String seriesTitle, String seriesDesc, int seriesYear, int seriesSubs, int seriesLang, String seriesRating) {
        this.seriesID = seriesID;
        this.seriesTitle = seriesTitle;
        this.seriesDesc = seriesDesc;
        this.seriesYear = seriesYear;
        this.seriesSubs = seriesSubs;
        this.seriesLang = seriesLang;
        this.seriesRating = seriesRating;
    }

    public int getSeriesID() {
        return this.seriesID;
    }

    public void setSeriesID(int seriesID) {
        this.seriesID = seriesID;
    }

    public String getSeriesTitle() {
        return this.seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getSeriesDesc() {
        return this.seriesDesc;
    }

    public void setSeriesDesc(String seriesDesc) {
        this.seriesDesc = seriesDesc;
    }

    public int getSeriesYear() {
        return this.seriesYear;
    }

    public void setSeriesYear(int seriesYear) {
        this.seriesYear = seriesYear;
    }

    public int getSeriesSubs() {
        return this.seriesSubs;
    }

    public void setSeriesSubs(int seriesSubs) {
        this.seriesSubs = seriesSubs;
    }

    public int getSeriesLang() {
        return this.seriesLang;
    }

    public void setSeriesLang(int seriesLang) {
        this.seriesLang = seriesLang;
    }

    public String getSeriesRating() {
        return this.seriesRating;
    }

    public void setSeriesRating(String seriesRating) {
        this.seriesRating = seriesRating;
    }

}
