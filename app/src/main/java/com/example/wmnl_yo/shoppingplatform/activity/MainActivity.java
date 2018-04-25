package com.example.wmnl_yo.shoppingplatform.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.PushDialog;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.adapter.FragmentAdapter;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteEntryFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteEntryFragmentTeacher;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteRecordDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteRecordDetailFragmentTeacher;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteRecordFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteRecordFragmentTeacher;
import com.example.wmnl_yo.shoppingplatform.fragment.AnnouncementDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.BuildingDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.BuildingFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.CalendarFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.CategoryFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ChatFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.CourseQueryDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.CourseQueryFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.CourseQueryResultFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.CourseRecordDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.CourseRecordFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.HealthCircumferenceFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.HealthHeightFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.HealthManageFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.HealthTemperatureFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.HealthWeightFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.OpinionAddFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.OpinionFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.OrderDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.OrderManageFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.OrderResultFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.RollCallDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.RollCallFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.SatisfacationChildFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.SatisfacationQuestionnaireFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.SatisfactionSurveyFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingCarATMFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingCarDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingFinialFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingListFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingObjectDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingObjectFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingPaymentFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.UploadPhotoListFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.UploadVideoFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.WebViewFragment;
import com.example.wmnl_yo.shoppingplatform.service.MyFirebaseInstanceIDService;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.apache.commons.lang3.StringEscapeUtils;

public class MainActivity extends AppCompatActivity implements
        BuildingDetailFragment.OnFragmentInteractionListener,BuildingFragment.OnFragmentInteractionListener,
        HealthManageFragment.OnFragmentInteractionListener,
        HealthCircumferenceFragment.OnFragmentInteractionListener,
        HealthHeightFragment.OnFragmentInteractionListener,
        HealthWeightFragment.OnFragmentInteractionListener,
        HealthTemperatureFragment.OnFragmentInteractionListener,
        OrderManageFragment.OnFragmentInteractionListener, OrderResultFragment.OnFragmentInteractionListener, OrderDetailFragment.OnFragmentInteractionListener,
        UploadVideoFragment.OnFragmentInteractionListener,
        OpinionFragment.OnFragmentInteractionListener, OpinionAddFragment.OnFragmentInteractionListener,
        RollCallFragment.OnFragmentInteractionListener, RollCallDetailFragment.OnFragmentInteractionListener,
        CalendarFragment.OnFragmentInteractionListener,
        CourseQueryFragment.OnFragmentInteractionListener, CourseRecordFragment.OnFragmentInteractionListener,CourseQueryResultFragment.OnFragmentInteractionListener,CourseQueryDetailFragment.OnFragmentInteractionListener,CourseRecordDetailFragment.OnFragmentInteractionListener,
        AbsentNoteEntryFragment.OnFragmentInteractionListener,AbsentNoteEntryFragmentTeacher.OnFragmentInteractionListener,AbsentNoteRecordFragment.OnFragmentInteractionListener,AbsentNoteRecordFragmentTeacher.OnFragmentInteractionListener,AbsentNoteRecordDetailFragment.OnFragmentInteractionListener,AbsentNoteRecordDetailFragmentTeacher.OnFragmentInteractionListener,
        WebViewFragment.OnFragmentInteractionListener,
        ChatFragment.OnFragmentInteractionListener,
        UploadPhotoListFragment.OnFragmentInteractionListener,
        CategoryFragment.OnFragmentInteractionListener,
        AnnouncementDetailFragment.OnFragmentInteractionListener,
        ShoppingListFragment.OnFragmentInteractionListener,ShoppingPaymentFragment.OnFragmentInteractionListener,ShoppingFinialFragment.OnFragmentInteractionListener,ShoppingCarATMFragment.OnFragmentInteractionListener,
        ShoppingCarDetailFragment.OnFragmentInteractionListener,ShoppingObjectFragment.OnFragmentInteractionListener,ShoppingObjectDetailFragment.OnFragmentInteractionListener,SatisfactionSurveyFragment.OnFragmentInteractionListener,
        SatisfacationChildFragment.OnFragmentInteractionListener,
        SatisfacationQuestionnaireFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    public static MenuItem menuSearchItem;
    public static final String CITY_NAME = "cityName";
    public static String account, password,pushDialogtext;
    public static String refreshedToken;
    long[] vibrate = {50};

    private SharedPreferences preferences;
    StringBuilder title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("55123-MainActivity", "onCreate");
        preferences = getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);

        setSubTitle("");
//        setTitle("親子館");
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
        viewPager = (ViewPager) findViewById(R.id.pager1);

        final String[] from = new String[]{"cityName"};
        final int[] to = new int[]{android.R.id.text1};
//        mAdapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_1, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item
                return true;
            }
        });
        // Inflate a menu to be displayed in the toolbar
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

//
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
//            @Override

//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//
//            }
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//        };
//        mActionBarDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        //fragmentAdapter控制
        account = preferences.getString("PREFERENCE_ACCOUNT", "");
        tabLayout.addTab(tabLayout.newTab().setText("設定").setIcon(R.drawable.ic_settings));
        tabLayout.addTab(tabLayout.newTab().setText("公告").setIcon(R.drawable.loupe));
        tabLayout.addTab(tabLayout.newTab().setText("帳戶").setIcon(R.drawable.user));
        if(loginActivity.userPeople.equals("student")) {
            tabLayout.addTab(tabLayout.newTab().setText("購物車").setIcon(R.drawable.shoppingcart));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(fragmentAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab LayoutTab) {

                viewPager.setCurrentItem(LayoutTab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {

            }
        });


        final MyFirebaseInstanceIDService myFirebaseInstanceIDService = new MyFirebaseInstanceIDService();
        myFirebaseInstanceIDService.onTokenRefresh();


        Thread thread = new Thread(){
            public void run(){
                myFirebaseInstanceIDService.sendRegistrationToServer(refreshedToken);
            }
        };
        thread.start();
        //紅色標示
//        BadgeView badgeView = new BadgeView(this);
//        badgeView.setTargetView(((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3));
//      //  badgeView.setTargetView(tabLayout.findViewById(R.drawable.shoppingcart));
//        badgeView.setText("10");
//   //     badgeView.setBadgeGravity(Gravity.END | Gravity.TOP);

        //推播
//        PusherOptions options = new PusherOptions();
//        options.setCluster("ap1");
//        Pusher pusher = new Pusher("71a6ae25a75c862961c0", options);
//
//        Channel channel = pusher.subscribe("my-channel");
//        channel.bind("my-event", new SubscriptionEventListener() {
//            @Override
//            public void onEvent(String channelName, String eventName, final String data) {
//                System.out.println(data);
//                Log.d("55123",data);
//                String str[] = data.split(": ");
//                String str1[] = str[1].toString().split("\"");
//
//                String text = StringEscapeUtils.unescapeJava(str1[1]);
//                pushDialogtext = text;
//                Log.d("55125",text);
//
//                NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//                //Step2. 設定當按下這個通知之後要執行的activity
//                Intent notifyIntent = new Intent(MainActivity.this,MainActivity.class);
//                notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                PendingIntent appIntent = PendingIntent.getActivity(MainActivity.this, 0, notifyIntent, 0);
//                //Step3. 透過 Notification.Builder 來建構 notification，
//                //並直接使用其.build() 的方法將設定好屬性的 Builder 轉換
//                //成 notification，最後開始將顯示通知訊息發送至狀態列上。
//                Notification notification
//                        = new Notification.Builder(MainActivity.this)
//                        .setContentIntent(appIntent)
//                        .setSmallIcon(R.drawable.ic_notification) // 設置狀態列裡面的圖示（小圖示）　　
//                        .setLargeIcon(BitmapFactory.decodeResource(MainActivity.this.getResources(),R.drawable.ic_notification)) // 下拉下拉清單裡面的圖示（大圖示）
//                        .setTicker("notification on status bar.") // 設置狀態列的顯示的資訊
//                        .setWhen(System.currentTimeMillis())// 設置時間發生時間
//                        .setAutoCancel(true) // 設置通知被使用者點擊後是否清除  //notification.flags = Notification.FLAG_AUTO_CANCEL;
//                        .setContentTitle("親子館") // 設置下拉清單裡的標題
//                        .setContentText(text)// 設置上下文內容
//                        .setOngoing(true)//true使notification變為ongoing，用戶不能手動清除
//                        .setVibrate(vibrate)
//                        .setDefaults(Notification.DEFAULT_ALL).build();
//                // 將此通知放到通知欄的"Ongoing"即"正在運行"組中
//                notification.flags = Notification.FLAG_ONGOING_EVENT;
//                // 表明在點擊了通知欄中的"清除通知"後，此通知不清除，
//                // 經常與FLAG_ONGOING_EVENT一起使用
//                notification.flags = Notification.FLAG_NO_CLEAR;
//                //閃爍燈光
//                notification.flags = Notification.FLAG_SHOW_LIGHTS;
//                mNotificationManager.notify(0, notification);
//
//
//            }
//
//        });
        DialogFragment dialog = PushDialog.newInstance(pushDialogtext);
        dialog.show(getSupportFragmentManager(),"dialog");
//        pusher.connect();


    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
            setSubTitle("");
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

        account = preferences.getString("PREFERENCE_ACCOUNT", "");
        password = preferences.getString("PREFERENCE_PASSWORD", "");

        if (account.equals("") && password.equals("")) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void setSubTitle(String a) {
        if (!a.equals("")) {
            setTitle("托育中心" + a);
        } else {
            setTitle("托育中心");
        }
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//
//        menuSearchItem = menu.findItem(R.id.my_search);
//
//        menuSearchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                replaceFragment(ShoppingListFragment.class, null);
//
//                return false;
//            }
//        });
////        // Get the SearchView and set the searchable configuration
////        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//////        SearchView searchView = (SearchView) menuSearchItem.getActionView();
////        SearchView searchView = (SearchView) menuSearchItem.getActionView();
//////        searchView.setSuggestionsAdapter(mAdapter);
////        // Assumes current activity is the searchable activity
////        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
////        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
////        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
////            @Override
////            public boolean onSuggestionClick(int position) {
////                // Your code here
////                return true;
////            }
////
////            @Override
////            public boolean onSuggestionSelect(int position) {
////                // Your code here
////                return true;
////            }
////        });
////
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String s) {
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String s) {
//////                populateAdapter(s);
////                return false;
////            }
////        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    boolean done = getSupportFragmentManager().popBackStackImmediate();
                    Log.d("55123", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()) + "：" + String.valueOf(done));
                    if (done == true && getSupportFragmentManager().getBackStackEntryCount() == 0) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setHomeButtonEnabled(false);
                        setSubTitle("");
                    }

                }
//                else if(getSupportFragmentManager().getBackStackEntryCount() == 1){
//
//                }
                else {
                    Log.d("55123", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));

                }
//                Fragment fragment = null;
//                Class fragmentClass = null;
//                fragmentClass = MainFragmentStudent.class;
//                try {
//                    fragment = (Fragment) fragmentClass.newInstance();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                break;
            default:
                break;
        }
        return false;
    }



    // You must implements your logic to get data using OrmLite
//    private void populateAdapter(String query) {
//        final MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "cityName"});
//        for (int i = 0; i < SUGGESTIONS.length; i++) {
//            if (SUGGESTIONS[i].toLowerCase().contains(query.toLowerCase()))
//                c.addRow(new Object[]{i, SUGGESTIONS[i]});
//        }
//        mAdapter.changeCursor(c);
//    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void replaceFragment(Class fm, Fragment fragobj) {
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = fm;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragobj != null)
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flContent, fragobj).commit();
        else
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flContent, fragment).commit();
    }

    public void replaceFragment_for_Announcement(Class fm, Fragment fragobj) {
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = fm;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragobj != null)
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flAnnouncement, fragobj).commit();
        else
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flAnnouncement, fragment).commit();
    }

    public void replaceFragment_for_ShoppingCar(Class fm, Fragment fragobj) {
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = fm;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragobj != null) {
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flShoppping, fragobj).commit();

        }else {
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flShoppping, fragment).commit();
        }
    }

}