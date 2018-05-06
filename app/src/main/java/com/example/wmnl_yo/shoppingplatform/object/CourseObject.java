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

        public final String mCount,mPhoto,mBuilding,mType,mClassname,mTeacher,mDays,mSDate,mEDate,mTime,mTeacherinfo,mContent,mTotal,mRemain,mPrice;

        public CourseObjectItem(String mCount,String mPhoto,String mBuilding,String mType,String mClassname,String mTeacher,String mDays,String mSDate,String mEDate,String mTime,String mTeacherinfo,String mContent,String mTotal,String mRemain,String mPrice) {
            this.mCount = mCount;
            this.mPhoto = mPhoto;
            this.mBuilding = mBuilding;
            this.mType = mType;
            this.mClassname = mClassname;
            this.mTeacher = mTeacher;
            this.mDays = mDays;
            this.mSDate = mSDate;
            this.mEDate = mEDate;
            this.mTime = mTime;
            this.mTeacherinfo = mTeacherinfo;
            this.mContent = mContent;
            this.mTotal = mTotal;
            this.mRemain = mRemain;
            this.mPrice = mPrice;
        }
    }
}
