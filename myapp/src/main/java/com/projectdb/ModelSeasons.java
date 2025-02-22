package com.projectdb;

public class ModelSeasons {
    int seasonsID, seasonsSeriesID, seasonsNum, seasonsEpNum;
    int seasonYear;
    String seasonTitle;

    public ModelSeasons(int seasonsID, int seasonsSeriesID, int seasonsNum, int seasonsEpNum, int seasonYear, String seasonTitle) {
        this.seasonsID = seasonsID;
        this.seasonsSeriesID = seasonsSeriesID;
        this.seasonsNum = seasonsNum;
        this.seasonsEpNum = seasonsEpNum;
        this.seasonYear = seasonYear;
        this.seasonTitle = seasonTitle;
    }

    public int getSeasonsID() {
        return this.seasonsID;
    }

    public String getSeasonTitle() {
        return this.seasonTitle;
    }

    public void setSeasonsID(int seasonsID) {
        this.seasonsID = seasonsID;
    }

    public int getSeasonsSeriesID() {
        return this.seasonsSeriesID;
    }

    public void setSeasonsSeriesID(int seasonsSeriesID) {
        this.seasonsSeriesID = seasonsSeriesID;
    }

    public int getSeasonsNum() {
        return this.seasonsNum;
    }

    public void setSeasonsNum(int seasonsNum) {
        this.seasonsNum = seasonsNum;
    }

    public int getSeasonsEpNum() {
        return this.seasonsEpNum;
    }

    public void setSeasonsEpNum(int seasonsEpNum) {
        this.seasonsEpNum = seasonsEpNum;
    }

    public int getSeasonYear() {
        return this.seasonYear;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }

}
