package com.projectdb;

public class ModelFilmsActors {
    int filmsActorsActorID, filmsActorsFilmID;
    String filmsActorsFilmTitle , filmsActorsActorName;

    public ModelFilmsActors(int filmsActorsActorID, int filmsActorsFilmID, String filmsActorsFilmTitle, String filmsActorsActorName) {
        this.filmsActorsActorID = filmsActorsActorID;
        this.filmsActorsFilmID = filmsActorsFilmID;
        this.filmsActorsFilmTitle = filmsActorsFilmTitle;
        this.filmsActorsActorName = filmsActorsActorName;
    }

    public int getFilmsActorsActorID() {
        return this.filmsActorsActorID;
    }

    public void setFilmsActorsActorID(int filmsActorsActorID) {
        this.filmsActorsActorID = filmsActorsActorID;
    }

    public int getFilmsActorsFilmID() {
        return this.filmsActorsFilmID;
    }

    public void setFilmsActorsFilmID(int filmsActorsFilmID) {
        this.filmsActorsFilmID = filmsActorsFilmID;
    }

    public String getFilmsActorsFilmTitle() {
        return this.filmsActorsFilmTitle;
    }

    public void setFilmsActorsFilmTitle(String filmsActorsFilmTitle) {
        this.filmsActorsFilmTitle = filmsActorsFilmTitle;
    }

    public String getFilmsActorsActorName() {
        return this.filmsActorsActorName;
    }

    public void setFilmsActorsActorName(String filmsActorsActorName) {
        this.filmsActorsActorName = filmsActorsActorName;
    }

}
