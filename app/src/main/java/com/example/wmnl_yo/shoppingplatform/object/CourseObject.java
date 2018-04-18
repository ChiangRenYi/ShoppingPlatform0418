package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/4/11.
 */

public class CourseObject implements Serializable {
    public static final List<CourseObjectItem> ITEMS = new ArrayList<CourseObjectItem>();
    public static void addItem(CourseObjectItem item) {
        ITEMS.add(item);
    }

    public static class CourseObjectItem implements Serializable {

        public final String mCount,mPicture,mNumber,mCourseBuildingName,mCourseName,mCourseTeacher,mCourseDate,mCourseWeek,mCourseSTime,mCourseSTime1,mCourseETime,mCourseETime1,
                mTeacherIntro,mCourseContent,mTotal,mRemaining,mPrice;

        public CourseObjectItem(String mCount,String mPicture, String mNumber, String mCourseBuildingName, String mCourseName, String mCourseTeacher, String mCourseDate,
                         String mCourseWeek, String mCourseSTime, String mCourseSTime1, String mCourseETime,
                         String mCourseETime1, String mTeacherIntro, String mCourseContent,
                         String mTotal, String mRemaining, String mPrice) {
            this.mCount = mCount;
            this.mPicture = mPicture;
            this.mNumber = mNumber;
            this.mCourseBuildingName = mCourseBuildingName;
            this.mCourseName = mCourseName;
            this.mCourseTeacher = mCourseTeacher;
            this.mCourseDate = mCourseDate;
            this.mCourseWeek = mCourseWeek;
            this.mCourseSTime = mCourseSTime;
            this.mCourseSTime1 = mCourseSTime1;
            this.mCourseETime = mCourseETime;
            this.mCourseETime1 = mCourseETime1;
            this.mTeacherIntro = mTeacherIntro;
            this.mCourseContent = mCourseContent;
            this.mTotal = mTotal;
            this.mRemaining = mRemaining;
            this.mPrice = mPrice;
        }
    }
}
