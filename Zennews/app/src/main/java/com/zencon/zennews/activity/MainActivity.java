package com.zencon.zennews.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zencon.zennews.R;
import com.zencon.zennews.adapter.NavigationAdapter;
import com.zencon.zennews.adapter.TabViewPagerAdapter;
import com.zencon.zennews.fragments.NewsListFragment;
import com.zencon.zennews.model.NavigationItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView navigationDrawerList;
    private DrawerLayout navigationDrawerLayout;
    private LinearLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] navigationMenuTitles;
    //private TypedArray navigationLeftMenuIcons;
    private ArrayList<NavigationItem> navDrawerItems;
    private NavigationAdapter adapter;
    private Toolbar myToolbar;

    private ViewPager mainPageViewPager;

    private final String[] categoryList = {"lead-news","bangladesh","politic","economy","world","sports",
                                           "entertainment","lifestyle","sciencetech","health","ctg"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();

        ImageLoader.getInstance().init(config);

        initUI();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainPageViewPager.setCurrentItem(0);

                Fragment f = ((TabViewPagerAdapter) mainPageViewPager.getAdapter()).getCurrentFragment();

                if (f instanceof NewsListFragment && f != null)
                {
                    ((NewsListFragment) f).loadNewsList(categoryList[0]);
                }
            }
        },1000);


    }

    private void initUI()
    {
        initToolBar();

        initNavigation();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.newsTabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Latest News"));
        tabLayout.addTab(tabLayout.newTab().setText("bangladesh"));
        tabLayout.addTab(tabLayout.newTab().setText("politic"));
        tabLayout.addTab(tabLayout.newTab().setText("economy"));
        tabLayout.addTab(tabLayout.newTab().setText("world"));
        tabLayout.addTab(tabLayout.newTab().setText("sports"));
        tabLayout.addTab(tabLayout.newTab().setText("entertainment"));
        tabLayout.addTab(tabLayout.newTab().setText("lifestyle"));
        tabLayout.addTab(tabLayout.newTab().setText("health"));
        tabLayout.addTab(tabLayout.newTab().setText("ctg"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabViewPagerAdapter pagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount() + 1);

        mainPageViewPager = (ViewPager) findViewById(R.id.mainPageViewPager);

        mainPageViewPager.setAdapter(pagerAdapter);

        mainPageViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainPageViewPager.setCurrentItem(tab.getPosition());

                Fragment f = ((TabViewPagerAdapter) mainPageViewPager.getAdapter()).getCurrentFragment();

                if (f instanceof NewsListFragment && f != null) {
                    ((NewsListFragment) f).loadNewsList(categoryList[tab.getPosition()]);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initToolBar()
    {
        myToolbar = (Toolbar) findViewById(R.id.zenNewsToolBar);

        myToolbar.setNavigationIcon(R.drawable.menu);

        myToolbar.setTitle("Zennews24");

        //myToolbar.setTitleTextColor(0x22222);

        setSupportActionBar(myToolbar);
    }

    private void initNavigation()
    {
        final RelativeLayout ll = (RelativeLayout) findViewById(R.id.menuLayout);

        drawer = (LinearLayout) findViewById(R.id.cleanRideDrawer);

        navigationMenuTitles = getResources().getStringArray(R.array.navigation_list_items);
       // navigationLeftMenuIcons = getResources().obtainTypedArray(R.array.navigation_list_left_icon);

        navigationDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationDrawerList = (ListView) findViewById(R.id.navSliderMenuTopList);

        navDrawerItems = new ArrayList<NavigationItem>();

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[0]));

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[1]));

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[2]));

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[3]));

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[4]));

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[5]));

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[5]));

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[6]));

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[7]));

        navDrawerItems.add(new NavigationItem(navigationMenuTitles[8]));

        navigationDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        adapter = new NavigationAdapter(getApplicationContext(), navDrawerItems);

        navigationDrawerList.setAdapter(adapter);

        mDrawerToggle = new ActionBarDrawerToggle(this, navigationDrawerLayout, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();

                navigationDrawerList.bringToFront();

                navigationDrawerLayout.requestLayout();
            }
        };

        navigationDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigationDrawerLayout.openDrawer(GravityCompat.START);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            displayView(position);
        }
    }


    private void displayView(int position)
    {
        switch (position)
        {
            case 0:

                mainPageViewPager.setCurrentItem(0);

                myToolbar.setTitle("Latest News");

                break;
            case 1:
                mainPageViewPager.setCurrentItem(1);

                myToolbar.setTitle("Bangladesh");

                break;
            case 2:
                mainPageViewPager.setCurrentItem(2);

                myToolbar.setTitle("Politic");

                break;
            case 3:
                mainPageViewPager.setCurrentItem(3);

                myToolbar.setTitle("Economy");

                break;
            case 4:
                mainPageViewPager.setCurrentItem(4);

                myToolbar.setTitle("World");

                break;
            case 5:
                mainPageViewPager.setCurrentItem(5);

                myToolbar.setTitle("Sports");

                break;
            case 6:
                mainPageViewPager.setCurrentItem(6);

                myToolbar.setTitle("Entertainment");

                break;
            case 7:
                mainPageViewPager.setCurrentItem(7);

                myToolbar.setTitle("Lifestyle");

                break;

            case 8:
                mainPageViewPager.setCurrentItem(8);

                myToolbar.setTitle("Health");

             break;
            case 9:
                mainPageViewPager.setCurrentItem(9);

                myToolbar.setTitle("Chittagong");

                break;
            default:
                break;
        }

        navigationDrawerLayout.closeDrawer(drawer);
    }
}
