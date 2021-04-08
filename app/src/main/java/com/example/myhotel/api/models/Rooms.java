package com.example.myhotel.api.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rooms {
    @SerializedName("number")
    private String number;
    @SerializedName("beds")
    private int beds;
    @SerializedName("facilities")
    Facilities f = new Facilities();
    @SerializedName("reserved")
    private boolean reserved;
    @SerializedName("price")
    private int price;
    @SerializedName("id")
    private String id;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("expirationDate")
    private String expirationDate;



    public Rooms(String number, int beds, Facilities f,boolean reserved, int price,String startDate,String expirationDate) {
        this.number = number;
        this.beds = beds;
        this.f = f;
        this.reserved=reserved;
        this.price = price;
        this.startDate=startDate;
        this.expirationDate=expirationDate;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public int getBeds() {
        return beds;
    }
    public void setBeds(int beds) {
        this.beds = beds;
    }
    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Facilities getF() {
        return f;
    }

    public void setF(Facilities f) {
        this.f = f;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStardDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}

