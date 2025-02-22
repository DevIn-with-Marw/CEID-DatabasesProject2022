package com.projectdb;

public class ModelFilmsCategories {
    int filmsCatFilmID;
    int filmsCatCatID;
    String filmsCatFilmTitle;
    String filmsCatCategoryName;

    public ModelFilmsCategories(int filmsCatFilmID, int filmsCatCatID, String filmsCatFilmTitle, String filmsCatCategoryName) {
        this.filmsCatFilmID = filmsCatFilmID;
        this.filmsCatCatID = filmsCatCatID;
        this.filmsCatFilmTitle = filmsCatFilmTitle;
        this.filmsCatCategoryName = filmsCatCategoryName;
    }


    public int getFilmsCatFilmID() {
        return this.filmsCatFilmID;
    }

    public void setFilmsCatFilmID(int filmsCatFilmID) {
        this.filmsCatFilmID = filmsCatFilmID;
    }

    public int getFilmsCatCatID() {
        return this.filmsCatCatID;
    }

    public void setFilmsCatCatID(int filmsCatCatID) {
        this.filmsCatCatID = filmsCatCatID;
    }

    public String getFilmsCatFilmTitle() {
        return this.filmsCatFilmTitle;
    }

    public void setFilmsCatFilmTitle(String filmsCatFilmTitle) {
        this.filmsCatFilmTitle = filmsCatFilmTitle;
    }

    public String getFilmsCatCategoryName() {
        return this.filmsCatCategoryName;
    }

    public void setFilmsCatCategoryName(String filmsCatCategoryName) {
        this.filmsCatCategoryName = filmsCatCategoryName;
    }

}
