package com.manojprabhakar.marketprices;

/**
 * Created by ManojPrabhakar on 4/27/2016.
 */
public class fields {


    private String Price;
    private String LastUpdated;

    public fields(){

    }

    public fields(String Price, String LastUpdated){
        this.Price = Price;
        this.LastUpdated = LastUpdated;
    }
    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public void setLastUpdated(String LastUpdated) {
        this.LastUpdated = LastUpdated;
    }

    public String getLastUpdated() {
        return LastUpdated;
    }
}


