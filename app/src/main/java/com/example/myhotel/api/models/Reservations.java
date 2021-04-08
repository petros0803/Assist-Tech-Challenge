package com.example.myhotel.api.models;

import com.google.gson.annotations.SerializedName;

public class Reservations {
    @SerializedName("id")
    private String reservationID;
    @SerializedName("userId")
    private String userID;
    @SerializedName("roomId")
    private String roomID;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("expirationDate")
    private String expirationDate;

    public Reservations(String reservationID, String userID, String roomID, String startDate, String expirationDate) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.roomID = roomID;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
