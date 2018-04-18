package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL-Jimmy on 2017/10/27.
 */

public class AnnouncementObject implements Serializable {
    public static final List<AnnouncementObjectItem> ITEMS = new ArrayList<AnnouncementObjectItem>();
    public static void addItem(AnnouncementObjectItem item) {
        ITEMS.add(item);
    }
    public static class AnnouncementObjectItem implements Serializable {

        public final String anTime,anTitle,anContent;

        public AnnouncementObjectItem(String anTime, String anTitle, String anContent) {
            this.anTime = anTime;
            this.anTitle = anTitle;
            this.anContent = anContent;

        }
    }

}
