package com.example.myhotel.data;

import com.example.myhotel.api.models.Facilities;
import com.example.myhotel.api.models.Rooms;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Singleton {

    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }


    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<Rooms> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Rooms> rooms) {
        this.rooms = rooms;
    }


    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String role;
    private String token;
    private String userID;
    private ArrayList<Rooms> rooms;


}
