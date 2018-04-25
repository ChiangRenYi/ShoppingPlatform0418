package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandy on 2018/1/17.
 */

public class ShoppingCarObject implements Serializable {

    public static final List<ShoppingCarObjectItem> ITEMS = new ArrayList<ShoppingCarObjectItem>();
    public static void addItem(ShoppingCarObjectItem item) {
        ITEMS.add(item);
    }

    public static class ShoppingCarObjectItem implements Serializable{

        public final String goodsCount;
        public final String goodskind;
        public final String goods;
        public  String goodsnumber;
        public final String goodsprice;
        public final String goodswarehouse;
        public final String goodschildname;
        public final String goodsbuild;

        public ShoppingCarObjectItem(String goodsCount,String goodskind, String goods, String goodsnumber, String goodsprice,String goodswarehouse,String goodschildname,String goodsbuild) {
            this.goodsCount = goodsCount;
            this.goodskind =goodskind;
            this.goods = goods;
            this.goodsnumber = goodsnumber;
            this.goodsprice = goodsprice;
            this.goodswarehouse = goodswarehouse;
            this.goodschildname = goodschildname;
            this.goodsbuild = goodsbuild;
        }
    }

}
