package com.projectdb;

public class ModelFilms {
    int filmId, filmSubs, filmLang, filmLength;
    String filmTitle, filmDesc;
    int filmYear;
    String filmRating, filmSp;

    public ModelFilms(int filmId, String filmTitle, String filmDesc, int filmYear, int filmSubs, int filmLang, int filmLength, String filmRating, String filmSp) {
        this.filmId = filmId;
        this.filmTitle = filmTitle;
        this.filmDesc = filmDesc;
        this.filmYear = filmYear;
        this.filmSubs = filmSubs;
        this.filmLang = filmLang;
        this.filmLength = filmLength;
        this.filmRating = filmRating;
        this.filmSp = filmSp;
    }

    public int getFilmId() {
        return this.filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getFilmSubs() {
        return this.filmSubs;
    }

    public void setFilmSubs(int filmSubs) {
        this.filmSubs = filmSubs;
    }

    public int getFilmLang() {
        return this.filmLang;
    }

    public void setFilmLang(int filmLang) {
        this.filmLang = filmLang;
    }

    public int getFilmLength() {
        return this.filmLength;
    }

    public void setFilmLength(int filmLength) {
        this.filmLength = filmLength;
    }

    public String getFilmTitle() {
        return this.filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getFilmDesc() {
        return this.filmDesc;
    }

    public void setFilmDesc(String filmDesc) {
        this.filmDesc = filmDesc;
    }

    public int getFilmYear() {
        return this.filmYear;
    }

    public void setFilmYear(int filmYear) {
        this.filmYear = filmYear;
    }

    public String getFilmRating() {
        return this.filmRating;
    }

    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }

    public String getFilmSp() {
        return this.filmSp;
    }

    public void setFilmSp(String filmSp) {
        this.filmSp = filmSp;
    }

}

   