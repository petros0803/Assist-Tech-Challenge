package com.example.myhotel.api.models;

import com.google.gson.annotations.SerializedName;

public class MakeReservation {
    @SerializedName("userId")
    private String userID;
    @SerializedName("roomId")
    private String roomID;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("expirationDate")
    private String endDate;

    public MakeReservation(String userID, String roomID, String startDate, String endDate) {
        this.userID = userID;
        this.roomID = roomID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
