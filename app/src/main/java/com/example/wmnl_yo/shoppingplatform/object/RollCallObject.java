package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL-Jimmy on 2018/4/23.
 */

public class RollCallObject implements Serializable {
    public static final List<RollCallObjectItem> ITEMS = new ArrayList<RollCallObjectItem>();
    public static void addItem(RollCallObjectItem item) {
        ITEMS.add(item);
    }

    public static class RollCallObjectItem implements Serializable{

        public final String CourseNumber,CourseName,CourseDate,CourseTime;

        public RollCallObjectItem(String CourseNumber,String CourseName, String CourseDate, String CourseTime) {
            this.CourseNumber = CourseNumber;
            this.CourseName = CourseName;
            this.CourseDate = CourseDate;
            this.CourseTime = CourseTime;

        }

    }

}
