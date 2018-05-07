package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/4/11.
 */

public class CourseRecordObject implements Serializable {
    public static final List<CourseRecordObjectItem> ITEMS = new ArrayList<CourseRecordObjectItem>();
    public static void addItem(CourseRecordObjectItem item) {
        ITEMS.add(item);
    }

    public static class CourseRecordObjectItem implements Serializable {

        public final String rCount,rPhoto,rBuilding,rType,rClassname,rTeacher,rDays,rSDate,rEDate,rTime,rTeacherinfo,rContent,rTotal,rRemain,rPrice,rPayment,rOdid,rStudentid,rStudentname;

        public CourseRecordObjectItem(String rCount,String rPhoto, String rBuilding, String rType, String rClassname, String rTeacher, String rDays,
                         String rSDate, String rEDate, String rTime, String rTeacherinfo,
                         String rContent, String rTotal, String rRemain,
                         String rPrice, String rPayment,String rOdid,String rStudentid,String rStudentname) {
            this.rCount = rCount;
            this.rPhoto = rPhoto;
            this.rBuilding = rBuilding;
            this.rType = rType;
            this.rClassname = rClassname;
            this.rTeacher = rTeacher;
            this.rDays = rDays;
            this.rSDate = rSDate;
            this.rEDate = rEDate;
            this.rTime = rTime;
            this.rTeacherinfo = rTeacherinfo;
            this.rContent = rContent;
            this.rTotal = rTotal;
            this.rRemain = rRemain;
            this.rPrice = rPrice;
            this.rPayment = rPayment;
            this.rOdid = rOdid;
            this.rStudentid = rStudentid;
            this.rStudentname = rStudentname;
        }
    }
}
