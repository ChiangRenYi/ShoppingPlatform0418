package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL on 2018/3/6.
 */

public class SatisfacationChildObject implements Serializable{
    public static final List<SatisfacationChildObjectItem> ITEMS = new ArrayList<SatisfacationChildObjectItem>();
    public static void addItem(SatisfacationChildObjectItem item) {
        ITEMS.add(item);
    }

    public static class SatisfacationChildObjectItem implements Serializable {

        public final String rChildName,rChild_ID;

        public SatisfacationChildObjectItem(String rChildName, String rChild_ID) {
            this.rChildName = rChildName;
            this.rChild_ID = rChild_ID;
        }
    }
}
