package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */

//public class HealthCircumferenceObject {

public class HealthCircumferenceObject implements Serializable {
    public static final List<HealthCircumferenceObjectItem> ITEMS = new ArrayList<HealthCircumferenceObjectItem>();
    public static void addItem(HealthCircumferenceObjectItem item) {
        ITEMS.add(item);
    }

    public static class HealthCircumferenceObjectItem extends HealthCircumferenceObject implements Serializable {

        public final int pic;
        public final String value,unit,datetime;

        public HealthCircumferenceObjectItem(int pic, String value, String unit, String datetime) {
            this.pic = pic;
            this.value = value;
            this.unit = unit;
            this.datetime = datetime;
        }
    }
}
