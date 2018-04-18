package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yang on 2017/5/1.
 */

public class OrderObject implements Serializable {
    public static final List<OrderObjectItem> ITEMS = new ArrayList<OrderObjectItem>();
    public static void addItem(OrderObjectItem item) {
        ITEMS.add(item);
    }

    public static class OrderObjectItem implements Serializable {

        public final String businessNumber,productType,productName,paymentMethod,shippingMethod,orderState,shippingState,description,orderDate,total;
        public final int productPrice,productAmount,subtotal;

        public OrderObjectItem(String businessNumber,String productType,String productName, String paymentMethod, String shippingMethod, String orderState, String shippingState, String description,
                               int productPrice,int productAmount,int subtotal,String total,
                               String orderDate) {
            this.businessNumber = businessNumber;
            this.productType = productType;
            this.productName = productName;
            this.paymentMethod = paymentMethod;
            this.shippingMethod = shippingMethod;
            this.orderState = orderState;
            this.shippingState = shippingState;
            this.description = description;
            this.productPrice = productPrice;
            this.productAmount = productAmount;
            this.subtotal = subtotal;
            this.total = total;
            this.orderDate = orderDate;
        }
    }
//
//    public OrderObject(String businessNumber, String productType, String productName, Date orderDate, String paymentMethod, String shippingMethod, String orderState, String shippingState, int productPrice, int productAmount, int subtotal, int total, String description) {
//        this.businessNumber = businessNumber;
//        this.productType = productType;
//        this.productName = productName;
//        this.orderDate = orderDate;
//      //  this.shippingDate = shippingDate;
//        this.paymentMethod = paymentMethod;
//        this.shippingMethod = shippingMethod;
//        this.orderState = orderState;
//        this.shippingState = shippingState;
//        this.productPrice = productPrice;
//        this.productAmount = productAmount;
//        this.subtotal = subtotal;
//        this.total = total;
//        this.description = description;
//    }

}
