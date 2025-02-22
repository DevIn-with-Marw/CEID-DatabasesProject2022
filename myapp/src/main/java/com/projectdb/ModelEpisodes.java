package com.projectdb;

public class ModelEpisodes {
    int episodesID;
    String episodesTitle;
    int episodesSeasonID;
    int episodesNum;
    int episodesLength;
    String episodesRating;
    int episodesSeriesID;
    String episodesSeriesTitle;

    public ModelEpisodes(int episodesID, String episodesTitle, int episodesSeasonID, int episodesNum, int episodesLength, String episodesRating, int episodesSeriesID, String episodesSeriesTitle) {
        this.episodesID = episodesID;
        this.episodesTitle = episodesTitle;
        this.episodesSeasonID = episodesSeasonID;
        this.episodesNum = episodesNum;
        this.episodesLength = episodesLength;
        this.episodesRating = episodesRating;
        this.episodesSeriesID = episodesSeriesID;
        this.episodesSeriesTitle = episodesSeriesTitle;
    }

    public int getEpisodesID() {
        return this.episodesID;
    }

    public void setEpisodesID(int episodesID) {
        this.episodesID = episodesID;
    }

    public String getEpisodesTitle() {
        return this.episodesTitle;
    }

    public void setEpisodesTitle(String episodesTitle) {
        this.episodesTitle = episodesTitle;
    }

    public int getEpisodesSeasonID() {
        return this.episodesSeasonID;
    }

    public void setEpisodesSeasonID(int episodesSeasonID) {
        this.episodesSeasonID = episodesSeasonID;
    }

    public int getEpisodesNum() {
        return this.episodesNum;
    }

    public void setEpisodesNum(int episodesNum) {
        this.episodesNum = episodesNum;
    }

    public int getEpisodesLength() {
        return this.episodesLength;
    }

    public void setEpisodesLength(int episodesLength) {
        this.episodesLength = episodesLength;
    }

    public String getEpisodesRating() {
        return this.episodesRating;
    }

    public void setEpisodesRating(String episodesRating) {
        this.episodesRating = episodesRating;
    }

    public int getEpisodesSeriesID() {
        return this.episodesSeriesID;
    }

    public void setEpisodesSeriesID(int episodesSeriesID) {
        this.episodesSeriesID = episodesSeriesID;
    }

    public String getEpisodesSeriesTitle() {
        return this.episodesSeriesTitle;
    }

    public void setEpisodesSeriesTitle(String episodesSeriesTitle) {
        this.episodesSeriesTitle = episodesSeriesTitle;
    }

}
