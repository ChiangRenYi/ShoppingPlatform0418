package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */

public class SelectStudentObject implements Serializable {
    public static final List<SelectStudentObjectItem> ITEMS = new ArrayList<SelectStudentObjectItem>();
    public static void addItem(SelectStudentObjectItem item) {
        ITEMS.add(item);
    }

    public static class SelectStudentObjectItem extends SelectStudentObject implements Serializable {

        public final int pic;
        public final String name,SD_id ;

        public SelectStudentObjectItem(int pic, String name, String SD_id) {
            this.pic = pic;
            this.name = name;
            this.SD_id = SD_id;
        }
    }
}
