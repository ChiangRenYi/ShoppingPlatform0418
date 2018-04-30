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

        public final String rCount,rPicture,rNumber,rCourseBuildingName,rCourseName,rCourseTeacher,rCourseDate,rCourseWeek,rCourseSTime,rCourseSTime1,rCourseETime,rCourseETime1,
                rTeacherIntro,rCourseContent,rTotal,rRemaining,rPrice,rPayment,rOdid,rStudentid,rStudentname;

        public CourseRecordObjectItem(String rCount,String rPicture, String rNumber, String rCourseBuildingName, String rCourseName, String rCourseTeacher, String rCourseDate,
                         String rCourseWeek, String rCourseSTime, String rCourseSTime1, String rCourseETime,
                         String rCourseETime1, String rTeacherIntro, String rCourseContent,
                         String rTotal, String rRemaining, String rPrice, String rPayment,String rOdid,String rStudentid,String rStudentname) {
            this.rCount = rCount;
            this.rPicture = rPicture;
            this.rNumber = rNumber;
            this.rCourseBuildingName = rCourseBuildingName;
            this.rCourseName = rCourseName;
            this.rCourseTeacher = rCourseTeacher;
            this.rCourseDate = rCourseDate;
            this.rCourseWeek = rCourseWeek;
            this.rCourseSTime = rCourseSTime;
            this.rCourseSTime1 = rCourseSTime1;
            this.rCourseETime = rCourseETime;
            this.rCourseETime1 = rCourseETime1;
            this.rTeacherIntro = rTeacherIntro;
            this.rCourseContent = rCourseContent;
            this.rTotal = rTotal;
            this.rRemaining = rRemaining;
            this.rPrice = rPrice;
            this.rPayment = rPayment;
            this.rOdid = rOdid;
            this.rStudentid = rStudentid;
            this.rStudentname = rStudentname;
        }
    }
}
