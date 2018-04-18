package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL on 2018/3/12.
 */

public class SatisfacationQuestionObject implements Serializable {
    public static final List<SatisfacationQuestionObjectItem> ITEMS = new ArrayList<SatisfacationQuestionObjectItem>();
    public static void addItem(SatisfacationQuestionObjectItem item) {
        ITEMS.add(item);
    }
    public static class SatisfacationQuestionObjectItem implements Serializable {
        public  String rQuestion,rQuestionID,rQuestionPoint,rQuestionReason;

        public SatisfacationQuestionObjectItem(String rQuestion,String rQuestionID,String rQuestionPoint,String rQuestionReason) {
            this.rQuestion=rQuestion;
            this.rQuestionID=rQuestionID;
            this.rQuestionPoint=rQuestionPoint;
            this.rQuestionReason=rQuestionReason;

        }


    }
}
