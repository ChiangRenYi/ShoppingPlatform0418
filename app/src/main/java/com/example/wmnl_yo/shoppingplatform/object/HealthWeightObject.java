package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */

public class HealthWeightObject implements Serializable {
    public static final List<HealthWeightObjectItem> ITEMS = new ArrayList<HealthWeightObjectItem>();
    public static void addItem(HealthWeightObjectItem item) {
        ITEMS.add(item);
    }

    public static class HealthWeightObjectItem extends HealthWeightObject implements Serializable {

        public final int pic;
        public final String value,unit,datetime;

        public HealthWeightObjectItem(int pic, String value, String unit, String datetime) {
            this.pic = pic;
            this.value = value;
            this.unit = unit;
            this.datetime = datetime;
        }
    }
}
