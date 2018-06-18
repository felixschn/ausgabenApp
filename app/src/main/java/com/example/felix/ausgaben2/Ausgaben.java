package com.example.felix.ausgaben2;

import java.util.Date;

public class Ausgaben {

    private String activtiyString;
    private double price;
    private Date date;


    public Ausgaben(){}

    public Ausgaben(String activtiyString, double price, Date date){
        this.activtiyString= activtiyString;
        this.price = price;
        this.date=date;
    }

    public String getActivtiyString() {
        return activtiyString;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public void setActivtiyString(String activtiyString) {
        this.activtiyString = activtiyString;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
