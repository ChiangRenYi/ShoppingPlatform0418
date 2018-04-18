package com.example.wmnl_yo.shoppingplatform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL-Jimmy on 2017/9/3.
 */

public class PhotoItem {

    public static final List<PhotoPic> ITEM = new ArrayList<PhotoPic>();

    public static void addItem(PhotoPic pic) {
        ITEM.add(pic);
    }

    public static class PhotoPic {

        public final int content;
        public final String title;
        public final int details;

        public PhotoPic(int content , String title , int details) {
            this.content = content;
            this.title = title;
            this.details = details;
        }

        @Override
        public String toString() {
            return title;
        }
    }

}
