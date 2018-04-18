package com.example.wmnl_yo.shoppingplatform.object;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */



public class OpinionmessageObject implements Serializable {
    public static final List<OpinionmessageObjectItem> ITEMS = new ArrayList<OpinionmessageObjectItem>();
    public static void addItem(OpinionmessageObjectItem item) {
        ITEMS.add(item);
    }

    public static class OpinionmessageObjectItem extends OpinionmessageObject implements Serializable {

        public final String name,value,datetime;

        public OpinionmessageObjectItem(String name, String value, String datetime) {
            this.name = name;
            this.value = value;
            this.datetime = datetime;
        }
    }
}


