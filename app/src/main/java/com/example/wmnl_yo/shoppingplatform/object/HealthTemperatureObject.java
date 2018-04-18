package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */

public class HealthTemperatureObject implements Serializable {
    public static final List<HealthTemperatureObjectItem> ITEMS = new ArrayList<HealthTemperatureObjectItem>();
    public static void addItem(HealthTemperatureObjectItem item) {
        ITEMS.add(item);
    }

    public static class HealthTemperatureObjectItem extends HealthTemperatureObject implements Serializable {

        public final int pic;
        public final String value,unit,datetime;

        public HealthTemperatureObjectItem(int pic, String value, String unit, String datetime) {
            this.pic = pic;
            this.value = value;
            this.unit = unit;
            this.datetime = datetime;
        }
    }
}
