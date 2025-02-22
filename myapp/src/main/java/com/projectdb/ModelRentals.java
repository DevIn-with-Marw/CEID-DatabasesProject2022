package com.projectdb;

public class ModelRentals {
    int rentalID, filmID, episodeID, customerID;
    String rentalDate;
    String rentalEmail;
    String rentalTitle;

    public ModelRentals(int rentalID, String rentalDate, int filmID, int episodeID, int customerID, String rentalEmail, String rentalTitle){
        this.rentalID = rentalID;
        this.rentalDate = rentalDate;
        this.filmID = filmID;
        this.episodeID = episodeID;
        this.customerID = customerID;
        this.rentalEmail = rentalEmail;
        this.rentalTitle = rentalTitle;
    }

    public int getRentalID() {
        return this.rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public int getFilmID() {
        return this.filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getEpisodeID() {
        return this.episodeID;
    }

    public void setEpisodeID(int episodeID) {
        this.episodeID = episodeID;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getRentalDate() {
        return this.rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getRentalEmail() {
        return this.rentalEmail;
    }

    public void setRentalEmail(String rentalEmail) {
        this.rentalEmail = rentalEmail;
    }

    public String getRentalTitle() {
        return this.rentalTitle;
    }

    public void setRentalTitle(String rentalTitle) {
        this.rentalTitle = rentalTitle;
    }
    


}
