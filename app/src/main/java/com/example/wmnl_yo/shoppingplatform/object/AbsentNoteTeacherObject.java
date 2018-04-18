package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/4/11.
 */

public class AbsentNoteTeacherObject implements Serializable {
    public static final List<AbsentNoteTeacherObjectItem> ITEMS = new ArrayList<AbsentNoteTeacherObjectItem>();
    public static void addItem(AbsentNoteTeacherObjectItem item) {
        ITEMS.add(item);
    }

    public static class AbsentNoteTeacherObjectItem implements Serializable {

        public final String asCount,asNumber,asName,asBuilding,asSdate,asEdate,asType,asReason,asMan,asThings,asAstatus,asAnote;

        public AbsentNoteTeacherObjectItem(String asCount, String asNumber, String asName, String asBuilding, String asSdate,String asEdate,
                                             String asType,String asReason,String asMan,String asThings,
                                             String asAstatus, String asAnote) {
            this.asCount = asCount;
            this.asNumber = asNumber;
            this.asName = asName;
            this.asBuilding = asBuilding;
            this.asSdate = asSdate;
            this.asEdate = asEdate;
            this.asType = asType;
            this.asReason = asReason;
            this.asMan = asMan;
            this.asThings = asThings;
            this.asAstatus = asAstatus;
            this.asAnote = asAnote;
        }
    }
}
