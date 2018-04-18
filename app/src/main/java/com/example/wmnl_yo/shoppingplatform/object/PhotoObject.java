package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;

/**
 * Created by WMNL-Jimmy on 2017/9/2.
 */

public class PhotoObject implements Serializable {

    private String profilePicture;
    private String date;
    private String message;

    public PhotoObject(String profilePicture, String date, String message) {
        this.profilePicture = profilePicture;
        this.date = date;
        this.message = message;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
