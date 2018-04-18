package com.example.wmnl_yo.shoppingplatform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL-YO on 2017/3/30.
 */

public class KinshipContent {

    public static final List<KinshipItem> ITEMS = new ArrayList<KinshipItem>();

    public static void addItem(KinshipItem item) {
        ITEMS.add(item);
    }

    public static class KinshipItem {
        public final String title;

        public KinshipItem(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
