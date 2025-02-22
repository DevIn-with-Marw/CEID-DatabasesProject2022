package com.projectdb.model;
import com.projectdb.ModelAddresses1;

import javafx.beans.property.*;

public class CustUser {
    private IntegerProperty idProperty;
    private StringProperty typeProperty;
    private ModelAddresses1 address;
    private StringProperty fnameProperty;
    private StringProperty lnameProperty;
    private StringProperty emailProperty;
    private IntegerProperty activeProperty;
    private StringProperty regdateProperty;

    public CustUser()
    {
        this.idProperty = new SimpleIntegerProperty();
        this.typeProperty = new SimpleStringProperty();
        this.address = new ModelAddresses1();
        this.fnameProperty = new SimpleStringProperty();
        this.lnameProperty = new SimpleStringProperty();
        this.emailProperty = new SimpleStringProperty();
        this.activeProperty = new SimpleIntegerProperty();
        this.regdateProperty = new SimpleStringProperty();
    }

    public int getCustUserId()
    {
        return idProperty.get();
    }
    public void setCustUserId(int id)
    {
        this.idProperty.set(id);
    }
    public IntegerProperty getCUid_Property()
    {
        return idProperty;
    }

    public String getCustUserType()
    {
        return typeProperty.get();
    }
    public void setCustUserType(String type)
    {
        if (type.equals("FILMS ONLY")||type.equals("SERIES ONLY")||type.equals("FILMS AND SERIES"))
        {this.typeProperty.set(type);}
    }
    public StringProperty getCUtype_Property()
    {
        return typeProperty;
    }

    public String getCustUserFname()
    {
        return fnameProperty.get();
    }
    public void setCustUserFname(String name)
    {
        this.fnameProperty.set(name);
    }
    public StringProperty getCUfname_Property()
    {
        return fnameProperty;
    }

    public String getCustUserLname()
    {
        return lnameProperty.get();
    }
    public void setCustUserLname(String surname)
    {
        this.lnameProperty.set(surname);
    }
    public StringProperty getCUlname_Property()
    {
        return lnameProperty;        
    }

    public ModelAddresses1 getCustUserAddress()
    {
        return address;
    }

    public String getCustUserEmail()
    {
        return emailProperty.get();

    }
    public void setCustUserEmail(String email)
    {
        this.emailProperty.set(email);
    }
    public StringProperty getCUemail_Property()
    {
        return emailProperty;
    }

    public int getCustUserActivity()
    {
        return activeProperty.get();
    }
    public void setCustUserActivity(int active)
    {
        if (active == 0 || active == 1)
        {this.activeProperty.set(active);}
    }
    public IntegerProperty getCUactive_Property()
    {
        return activeProperty;
    }

    public String getCustUserRegDate()
    {
        return regdateProperty.get();
    }
    public void setCustUserRegDate(String regdate)
    {
        this.regdateProperty.set(regdate);
    }
    public StringProperty getCUregdate_Property()
    {
        return regdateProperty;
    }
    
}
