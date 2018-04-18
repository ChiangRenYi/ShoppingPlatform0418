package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */

public class SelectBuildingObject implements Serializable {
    public static final List<SelectBuildingObjectItem> ITEMS = new ArrayList<SelectBuildingObjectItem>();
    public static void addItem(SelectBuildingObjectItem item) {
        ITEMS.add(item);
    }

    public static class SelectBuildingObjectItem extends SelectBuildingObject implements Serializable {

        public final int pic;
        public final String name,AU_id,ER_id ;

        public SelectBuildingObjectItem(int pic, String name,String AU_id, String ER_id) {
            this.pic = pic;
            this.name = name;
            this.AU_id = AU_id;
            this.ER_id = ER_id;
        }
    }
}
