package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandy on 2018/1/17.
 */

public class ShoppingMallObject implements Serializable {

    public static final List<ShoppingMallObjectItem> ITEMS = new ArrayList<ShoppingMallObjectItem>();
    public static void addItem(ShoppingMallObjectItem item) {
        ITEMS.add(item);
    }

    public static class ShoppingMallObjectItem implements Serializable{

        public final String Shoppingmall_photo;
        public final String Shoppingmall_id;
        public final String Shoppingmall_kind;
        public final String Shoppingmall_name;
        public final String Shoppingmall_price;
        public final String Shoppingmall_amount;
        public final String Shoppingmall_factory;
        public final String Shoppingmall_introduction;

        public ShoppingMallObjectItem(String Shoppingmall_photo, String Shoppingmall_id, String Shoppingmall_kind, String Shoppingmall_name, String Shoppingmall_price, String Shoppingmall_amount, String Shoppingmall_factory, String Shoppingmall_introduction) {
            this.Shoppingmall_photo = Shoppingmall_photo;
            this.Shoppingmall_id = Shoppingmall_id;
            this.Shoppingmall_kind = Shoppingmall_kind;
            this.Shoppingmall_name = Shoppingmall_name;
            this.Shoppingmall_price = Shoppingmall_price;
            this.Shoppingmall_amount = Shoppingmall_amount;
            this.Shoppingmall_factory = Shoppingmall_factory;
            this.Shoppingmall_introduction = Shoppingmall_introduction;
        }
    }

}
