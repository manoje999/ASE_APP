package com.manojprabhakar.tutorial1;

/**
 * Created by ManojPrabhakar on 2/3/2016.
 */
public class Market {


    private String name;
    private String marketID;

    public Market(){

    }

    public Market(String name, String marketID){
        this.name = name;
        this.marketID = marketID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    public String getMarketID() {
        return marketID;
    }






}
