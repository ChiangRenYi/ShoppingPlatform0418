package com.example.wmnl_yo.shoppingplatform;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
//    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    //private static final int COUNT = 25;
    static{

//        allItems.add(new ItemObject("United States", R.drawable.one));
//        allItems.add(new ItemObject("Canada", R.drawable.two));
//        allItems.add(new ItemObject("United Kingdom", R.drawable.three));
//        allItems.add(new ItemObject("Germany", R.drawable.four));
//        allItems.add(new ItemObject("Sweden", R.drawable.five));
//        allItems.add(new ItemObject("United Kingdom", R.drawable.six));
//        allItems.add(new ItemObject("Germany", R.drawable.seven));
//        allItems.add(new ItemObject("Sweden", R.drawable.eight));
//        allItems.add(new ItemObject("United States", R.drawable.one));
//        allItems.add(new ItemObject("Canada", R.drawable.two));
//        allItems.add(new ItemObject("United Kingdom", R.drawable.three));
//        allItems.add(new ItemObject("Germany", R.drawable.four));
//        allItems.add(new ItemObject("Sweden", R.drawable.five));
//        allItems.add(new ItemObject("United Kingdom", R.drawable.six));
//        allItems.add(new ItemObject("Germany", R.drawable.seven));
//        allItems.add(new ItemObject("Sweden", R.drawable.eight));
        //new DummyItem("1", "55123","");
    }



    public static void addItem(DummyItem item) {
        ITEMS.add(item);
    }

//    private static DummyItem createDummyItem(int position) {
//        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
//    }

//    private static String makeDetails(int position) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Details about Item: ").append(position);
//        for (int i = 0; i < position; i++) {
//            builder.append("\nMore details information here.");
//        }
//        return builder.toString();
//    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final int content;
        public final String title;
        public final int details;

        public DummyItem(int content , String title , int details) {
            this.content = content;
            this.title = title;
            this.details = details;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
