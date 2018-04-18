package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;

/**
 * Created by Yang on 2017/5/8.
 */

public class OpinionObject implements Serializable {
    private int profilePicture;
    private String date;
    private String personName;
    private String position;
    private String message;

    public OpinionObject(int profilePicture, String date, String personName, String position, String message) {
        this.profilePicture = profilePicture;
        this.date = date;
        this.personName = personName;
        this.position = position;
        this.message = message;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
