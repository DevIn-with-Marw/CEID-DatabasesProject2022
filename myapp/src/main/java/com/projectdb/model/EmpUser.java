package com.projectdb.model;

import javafx.beans.property.*;

public class EmpUser {
    private IntegerProperty idProperty;
    private StringProperty fnameProperty;
    private StringProperty lnameProperty;
    private StringProperty emailProperty;
    private StringProperty phoneProperty;
    private IntegerProperty activeProperty;
    private StringProperty regdateProperty;

    public EmpUser()
    {
        this.idProperty = new SimpleIntegerProperty();
        this.emailProperty = new SimpleStringProperty();
        this.phoneProperty = new SimpleStringProperty();
        this.fnameProperty = new SimpleStringProperty();
        this.lnameProperty = new SimpleStringProperty();
        this.activeProperty = new SimpleIntegerProperty();
        this.regdateProperty = new SimpleStringProperty();
    }

    public int getEmpUserId()
    {
        return idProperty.get();
    }
    public void setEmpUserId(int id)
    {
        this.idProperty.set(id);
    }
    public IntegerProperty getEUid_Property()
    {
        return idProperty;
    }

    public String getEmpUserPhone()
    {
        return phoneProperty.get();
    }
    public void setEmpUserPhone(String number)
    {
        this.phoneProperty.set(number);
    }
    public StringProperty getEUphone_Property()
    {
        return phoneProperty;
    }

    public String getEmpUserEmail()
    {
        return emailProperty.get();

    }
    public void setEmpUserEmail(String email)
    {
        this.emailProperty.set(email);
    }
    public StringProperty getEUemail_Property()
    {
        return emailProperty;
    }

    public String getEmpUserFname()
    {
        return fnameProperty.get();
    }
    public void setEmpUserFname(String name)
    {
        this.fnameProperty.set(name);
    }
    public StringProperty getEUfname_Property()
    {
        return fnameProperty;
    }

    public String getEmpUserLname()
    {
        return lnameProperty.get();
    }
    public void setEmpUserLname(String surname)
    {
        this.lnameProperty.set(surname);
    }
    public StringProperty getEUlname_Property()
    {
        return lnameProperty;        
    }

    public int getEmpUserActivity()
    {
        return activeProperty.get();
    }
    public void setEmpUserActivity(int active)
    {
        if (active == 0 || active == 1)
        {this.activeProperty.set(active);}
    }
    public IntegerProperty getEUactive_Property()
    {
        return activeProperty;
    }

    public String getEmpUserRegDate()
    {
        return regdateProperty.get();
    }
    public void setEmpUserRegDate(String regdate)
    {
        this.regdateProperty.set(regdate);
    }
    public StringProperty getEUregdate_Property()
    {
        return regdateProperty;
    }
    
}
