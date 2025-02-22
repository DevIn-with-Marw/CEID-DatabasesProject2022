package com.projectdb;

public class ModelSeriesCategories {
    int seriesCatFilmID;
    int seriesCatCatID;
    String seriesCatCategoryName;
    String seriesCatSeriesTitle;

    public ModelSeriesCategories(int seriesCatFilmID, int seriesCatCatID, String seriesCatCategoryName, String seriesCatSeriesTitle) {
        this.seriesCatFilmID = seriesCatFilmID;
        this.seriesCatCatID = seriesCatCatID;
        this.seriesCatCategoryName = seriesCatCategoryName;
        this.seriesCatSeriesTitle = seriesCatSeriesTitle;
    }

    public int getSeriesCatFilmID() {
        return this.seriesCatFilmID;
    }

    public void setSeriesCatFilmID(int seriesCatFilmID) {
        this.seriesCatFilmID = seriesCatFilmID;
    }

    public int getSeriesCatCatID() {
        return this.seriesCatCatID;
    }

    public void setSeriesCatCatID(int seriesCatCatID) {
        this.seriesCatCatID = seriesCatCatID;
    }

    public String getSeriesCatCategoryName() {
        return this.seriesCatCategoryName;
    }

    public void setSeriesCatCategoryName(String seriesCatCategoryName) {
        this.seriesCatCategoryName = seriesCatCategoryName;
    }

    public String getSeriesCatSeriesTitle() {
        return this.seriesCatSeriesTitle;
    }

    public void setSeriesCatSeriesTitle(String seriesCatSeriesTitle) {
        this.seriesCatSeriesTitle = seriesCatSeriesTitle;
    }


}
