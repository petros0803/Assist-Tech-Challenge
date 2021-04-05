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
    public Rooms(String number, int beds, Facilities f, int price) {
        this.number = number;
        this.beds = beds;
        this.f = f;
        this.price = price;
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
}

