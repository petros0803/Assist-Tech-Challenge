package com.example.myhotel.api.models;

import com.google.gson.annotations.SerializedName;

public class RemoveReservation {
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public RemoveReservation(String id)
    {
        this.id=id;
    }
}
