package com.projectdb;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelAddresses1 {
    int addressID;
    String addressName, addressDistrict;
    int addressCityID;
    String addressPostal, addressPhone, addressCityName;
    //
    StringProperty addressname_Property;


    public ModelAddresses1(int addressID, String addressName, String addressDistrict, int addressCityID, String addressPostal, String addressPhone, String addressCityName) {
        this.addressID = addressID;
        this.addressName = addressName;
        //
        this.addressname_Property = new SimpleStringProperty();
        this.addressname_Property.set(addressName);
        //
        this.addressDistrict = addressDistrict;
        this.addressCityID = addressCityID;
        this.addressPostal = addressPostal;
        this.addressPhone = addressPhone;
        this.addressCityName = addressCityName;
    }
    
    //
    public ModelAddresses1()
    { 
        this.addressname_Property = new SimpleStringProperty();
    }
    //
    public int getAddressID() {
        return this.addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressName() {
        return this.addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
        //
        this.addressname_Property.set(addressName);
        //
    }
    //
    public StringProperty getAddname_Property()
    {
        return addressname_Property;
    }
    //
    public String getAddressDistrict() {
        return this.addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public int getAddressCityID() {
        return this.addressCityID;
    }

    public void setAddressCityID(int addressCityID) {
        this.addressCityID = addressCityID;
    }

    public String getAddressPostal() {
        return this.addressPostal;
    }

    public void setAddressPostal(String addressPostal) {
        this.addressPostal = addressPostal;
    }

    public String getAddressPhone() {
        return this.addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getAddressCityName() {
        return this.addressCityName;
    }

    public void setAddressCityName(String addressCityName) {
        this.addressCityName = addressCityName;
    }

}
