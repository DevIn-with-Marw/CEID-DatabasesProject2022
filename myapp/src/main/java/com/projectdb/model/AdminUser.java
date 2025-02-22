package com.projectdb.model;

import javafx.beans.property.*;

public class AdminUser {
    private IntegerProperty idProperty;
    private StringProperty fnameProperty;
    private StringProperty lnameProperty;
    private StringProperty emailProperty;
    private IntegerProperty activeProperty;
    private StringProperty regdateProperty;
    
    public AdminUser()
    {
        this.idProperty = new SimpleIntegerProperty();
        this.fnameProperty = new SimpleStringProperty();
        this.lnameProperty = new SimpleStringProperty();
        this.emailProperty = new SimpleStringProperty();
        this.activeProperty = new SimpleIntegerProperty();
        this.regdateProperty = new SimpleStringProperty();
    }

    public int getAdminUserId()
    {
        return idProperty.get();
    }
    public void setAdminUserId(int id)
    {
        this.idProperty.set(id);
    }
    public IntegerProperty getAUid_Property()
    {
        return idProperty;
    }

    public String getAdminUserFname()
    {
        return fnameProperty.get();
    }
    public void setAdminUserFname(String name)
    {
        this.fnameProperty.set(name);
    }
    public StringProperty getAUfname_Property()
    {
        return fnameProperty;
    }

    public String getAdminUserLname()
    {
        return lnameProperty.get();
    }
    public void setAdminUserLname(String surname)
    {
        this.lnameProperty.set(surname);
    }
    public StringProperty getAUlname_Property()
    {
        return lnameProperty;        
    }

    public String getAdminUserEmail()
    {
        return emailProperty.get();

    }
    public void setAdminUserEmail(String email)
    {
        this.emailProperty.set(email);
    }
    public StringProperty getAUemail_Property()
    {
        return emailProperty;
    }

    public int getAdminUserActivity()
    {
        return activeProperty.get();
    }
    public void setAdminUserActivity(int active)
    {
        if (active == 0 || active == 1)
        {this.activeProperty.set(active);}
    }
    public IntegerProperty getAUactive_Property()
    {
        return activeProperty;
    }

    public String getAdminUserRegDate()
    {
        return regdateProperty.get();
    }
    public void setAdminUserRegDate(String regdate)
    {
        this.regdateProperty.set(regdate);
    }
    public StringProperty getAUregdate_Property()
    {
        return regdateProperty;
    }

}
