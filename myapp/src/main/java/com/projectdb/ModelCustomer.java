package com.projectdb;

public class ModelCustomer {
    public int customerID , addressID;
    public String customerType, customerFName, customerLName, custEmail, customerYear;
    public int custActive, pending;

    public ModelCustomer(int customerID, int addressID, String customerType, String customerFName, String customerLName, String custEmail, String customerYear, int custActive, int pending) {
        this.customerID = customerID;
        this.addressID = addressID;
        this.customerType = customerType;
        this.customerFName = customerFName;
        this.customerLName = customerLName;
        this.custEmail = custEmail;
        this.customerYear = customerYear;
        this.custActive = custActive;
        this.pending = pending;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public int getPending() {
        return this.pending;
    }

    public void setPending( int pending ) {
        this.pending = pending;
    }

    public int getCustActive() {
        return this.custActive;
    }

    public void setCustActive(int custActive) {
        this.custActive = custActive;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAddressID() {
        return this.addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerFName() {
        return this.customerFName;
    }

    public void setCustomerFName(String customerFName) {
        this.customerFName = customerFName;
    }

    public String getCustomerLName() {
        return this.customerLName;
    }

    public void setCustomerLName(String customerLName) {
        this.customerLName = customerLName;
    }

    public String getCustEmail() {
        return this.custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustomerYear() {
        return this.customerYear;
    }

    public void setCustomerYear(String customerYear) {
        this.customerYear = customerYear;
    }

}
