package com.example.wmnl_yo.shoppingplatform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL-YO on 2017/4/18.
 */

public class PhotoContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<PhotoItem> ITEMS = new ArrayList<PhotoItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */

    public static void addItem(PhotoItem item) {
        ITEMS.add(item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class PhotoItem {
        public final String content;

        public PhotoItem(String content) {
            this.content = content;
        }

//        @Override
//        public int toInt() {
//            return content;
//        }
    }
}
