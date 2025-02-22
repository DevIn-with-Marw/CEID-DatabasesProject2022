package com.projectdb;

public class ModelSeriesActors {
   int seriesActorsActorID;
   String seriesACtorsActorName;
   int seriesActorsSeasonID, seriesActorsSeasonNum, seriesActorsSeriesID;
   String seriesActorsSeriesTitle;

    public ModelSeriesActors(int seriesActorsActorID, String seriesACtorsActorName, int seriesActorsSeasonID, int seriesActorsSeasonNum, int seriesActorsSeriesID, String seriesActorsSeriesTitle) {
        this.seriesActorsActorID = seriesActorsActorID;
        this.seriesACtorsActorName = seriesACtorsActorName;
        this.seriesActorsSeasonID = seriesActorsSeasonID;
        this.seriesActorsSeasonNum = seriesActorsSeasonNum;
        this.seriesActorsSeriesID = seriesActorsSeriesID;
        this.seriesActorsSeriesTitle = seriesActorsSeriesTitle;
    }

    public int getSeriesActorsActorID() {
        return this.seriesActorsActorID;
    }

    public void setSeriesActorsActorID(int seriesActorsActorID) {
        this.seriesActorsActorID = seriesActorsActorID;
    }

    public String getSeriesACtorsActorName() {
        return this.seriesACtorsActorName;
    }

    public void setSeriesACtorsActorName(String seriesACtorsActorName) {
        this.seriesACtorsActorName = seriesACtorsActorName;
    }

    public int getSeriesActorsSeasonID() {
        return this.seriesActorsSeasonID;
    }

    public void setSeriesActorsSeasonID(int seriesActorsSeasonID) {
        this.seriesActorsSeasonID = seriesActorsSeasonID;
    }

    public int getSeriesActorsSeasonNum() {
        return this.seriesActorsSeasonNum;
    }

    public void setSeriesActorsSeasonNum(int seriesActorsSeasonNum) {
        this.seriesActorsSeasonNum = seriesActorsSeasonNum;
    }

    public int getSeriesActorsSeriesID() {
        return this.seriesActorsSeriesID;
    }

    public void setSeriesActorsSeriesID(int seriesActorsSeriesID) {
        this.seriesActorsSeriesID = seriesActorsSeriesID;
    }

    public String getSeriesActorsSeriesTitle() {
        return this.seriesActorsSeriesTitle;
    }

    public void setSeriesActorsSeriesTitle(String seriesActorsSeriesTitle) {
        this.seriesActorsSeriesTitle = seriesActorsSeriesTitle;
    }

}
