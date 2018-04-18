package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */

public class HealthHeightObject implements Serializable {
    public static final List<HealthHeightObjectItem> ITEMS = new ArrayList<HealthHeightObjectItem>();
    public static void addItem(HealthHeightObjectItem item) {
        ITEMS.add(item);
    }

    public static class HealthHeightObjectItem extends HealthHeightObject implements Serializable {

        public final int pic;
        public final String value,unit,datetime;

        public HealthHeightObjectItem(int pic, String value, String unit, String datetime) {
            this.pic = pic;
            this.value = value;
            this.unit = unit;
            this.datetime = datetime;
        }
    }
}
