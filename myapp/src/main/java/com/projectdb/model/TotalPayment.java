package com.projectdb.model;

import javafx.beans.property.*;


public class TotalPayment {
    
    private StringProperty month;
    private FloatProperty filmpayments;
    private FloatProperty episodepayments;


    public TotalPayment()
    {
        this.month = new SimpleStringProperty();
        this.filmpayments = new SimpleFloatProperty();
        this.episodepayments = new SimpleFloatProperty();
    }

    public Float getTotalPayments_episodepayments()
    {
        return episodepayments.get();
    }
    public void setTotalPayments_episodepayments(Float payment)
    {
        this.episodepayments.set(payment);
    }
    public FloatProperty getTPepisodepayment_Property()
    {
        return episodepayments;
    }

    public Float getTotalPayments_filmpayments()
    {
        return filmpayments.get();
    }
    public void setTotalPayments_filmpayments(Float payment)
    {
        this.filmpayments.set(payment);
    }
    public FloatProperty getTPfilmpayment_Property()
    {
        return filmpayments;
    }
    
    public String getTotalPayments_month()
    {
        return month.get();
    }
    public void setTotalPayments_month(String month)
    {
        if (month.matches("20(0[5-9]|1[0-9]|2[0-2])-(0[1-9]|1[0-2])"))
        {
            this.month.set(month);
        }
        else {this.month.set("????-??");}
    }
    public StringProperty getTPmonth_Property()
    {
        return month;
    }
}

