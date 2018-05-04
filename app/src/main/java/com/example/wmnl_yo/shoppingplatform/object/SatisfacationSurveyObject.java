package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL on 2018/2/26.
 */

public class SatisfacationSurveyObject implements Serializable{
    public static final List<SatisfacationSurveyObjectItem> ITEMS = new ArrayList<SatisfacationSurveyObjectItem>();
    public static void addItem(SatisfacationSurveyObjectItem item) {
        ITEMS.add(item);
    }

    public static class SatisfacationSurveyObjectItem implements Serializable {

        public final String rTeacher,rCourse,rQuestionnaire,rQuestionID,rCoursePlace;

        public SatisfacationSurveyObjectItem(String rTeacher, String rCourse, String rQuestionnaire, String rQuestionID,String rCoursePlace) {
            this.rTeacher = rTeacher;
            this.rCourse = rCourse;
            this.rQuestionnaire = rQuestionnaire;
            this.rQuestionID = rQuestionID;
            this.rCoursePlace = rCoursePlace;

        }
    }
}
