package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */

public class SelectMemberObject implements Serializable {
    public static final List<SelectMemberObjectItem> ITEMS = new ArrayList<SelectMemberObjectItem>();
    public static void addItem(SelectMemberObjectItem item) {
        ITEMS.add(item);
    }

    public static class SelectMemberObjectItem extends SelectMemberObject implements Serializable {

        public final int pic;
        public final String name,AU_id,ER_id ;

        public SelectMemberObjectItem(int pic, String name, String AU_id, String ER_id) {
            this.pic = pic;
            this.name = name;
            this.AU_id = AU_id;
            this.ER_id = ER_id;
        }
    }
}
