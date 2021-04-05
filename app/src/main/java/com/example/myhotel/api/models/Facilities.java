package com.example.myhotel.api.models;

import com.google.gson.annotations.SerializedName;

public class Facilities {
    @SerializedName("wifi")
    private boolean wifi;
    @SerializedName("ac")
    private boolean ac;
    @SerializedName("tv")
    private boolean tv;
    @SerializedName("nsr")
    private boolean nsr;

    public Facilities(boolean wifi, boolean ac, boolean tv, boolean nsr) {
        this.wifi = wifi;
        this.ac = ac;
        this.tv = tv;
        this.nsr = nsr;
    }

    public Facilities() {

    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isNsr() {
        return nsr;
    }

    public void setNsr(boolean nsr) {
        this.nsr = nsr;
    }
}
