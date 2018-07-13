package com.example.wmnl_yo.shoppingplatform.object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */

public class BuildingObject {

    public static final List<BuildingObjectItem> ITEMS = new ArrayList<BuildingObjectItem>();
    public static void addItem(BuildingObjectItem item) {
        ITEMS.add(item);
    }


    public static class BuildingObjectItem {
        public final String home;
        public final String phone;
        public final String time;
        public final String address;
        public final String id;
        public final String url;
        public BuildingObjectItem(String id,String home, String phone, String time, String address, String url) {
            this.id = id;
            this.home = home;
            this.phone = phone;
            this.time = time;
            this.address = address;
            this.url = url;
        }
    }
}
