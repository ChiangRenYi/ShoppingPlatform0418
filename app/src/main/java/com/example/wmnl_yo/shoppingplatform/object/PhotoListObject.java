package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;

/**
 * Created by WMNL-Jimmy on 2017/9/2.
 */

public class PhotoListObject implements Serializable {

    private String date;
    private String personName;
    private String position;
    private String message;

    public PhotoListObject(String date, String personName, String position, String message) {
        this.date = date;
        this.personName = personName;
        this.position = position;
        this.message = message;
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
