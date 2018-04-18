package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL-Jimmy on 2018/3/28.
 */

public class OrderDetailObject implements Serializable {
    public static final List<OrderDetailObjectItem> ITEMS = new ArrayList<OrderDetailObjectItem>();
    public static void addItem(OrderDetailObjectItem item) {
        ITEMS.add(item);
    }

    public static class OrderDetailObjectItem implements Serializable {

        public final String productType,description;


        public OrderDetailObjectItem(String productType, String description) {
            this.productType = productType;
            this.description = description;
        }
    }
}
