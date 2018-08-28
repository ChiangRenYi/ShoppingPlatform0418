package com.example.wmnl_yo.shoppingplatform.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by WMNL-Jimmy on 2018/5/24.
 */

public class CompetenceObject {
    public static final List<CompetenceObjectItem> ITEMS = new ArrayList<CompetenceObjectItem>();
    public static void addItem(CompetenceObjectItem item) {
        ITEMS.add(item);
    }


    public static class CompetenceObjectItem implements Serializable {

        public  String competenceName;
        public  Boolean competenceState;


        public CompetenceObjectItem(String competenceName,Boolean competenceState) {
            this.competenceName = competenceName;
            this.competenceState = competenceState;

        }

        public void setCompetenceState(Boolean state){
            this.competenceState = state;
        }
    }
}
