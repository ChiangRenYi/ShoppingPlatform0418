package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/4/11.
 */

public class AbsentStudentRecordObject implements Serializable {
    public static final List<AbsentStudentRecordObjectItem> ITEMS = new ArrayList<AbsentStudentRecordObjectItem>();
    public static void addItem(AbsentStudentRecordObjectItem item) {
        ITEMS.add(item);
    }

    public static class AbsentStudentRecordObjectItem implements Serializable {

        public final String asCount,asNumber,asName,asSdate,asBuilding,asCourse,assTime,assTime1,aseTime,aseTime1,asType,asMoney,asReason,asAstatus;

        public AbsentStudentRecordObjectItem(String asCount, String asNumber, String asName, String asSdate, String asBuilding,String asCourse,
                                       String assTime,String assTime1,String aseTime,String aseTime1,
                                       String asType, String asMoney, String asReason, String asAstatus) {
            this.asCount = asCount;
            this.asNumber = asNumber;
            this.asName = asName;
            this.asSdate = asSdate;
            this.asBuilding = asBuilding;
            this.asCourse = asCourse;
            this.assTime = assTime;
            this.assTime1 = assTime1;
            this.aseTime = aseTime;
            this.aseTime1 = aseTime1;
            this.asType = asType;
            this.asMoney = asMoney;
            this.asReason = asReason;
            this.asAstatus = asAstatus;
        }
    }
}
