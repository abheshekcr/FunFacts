package com.zencon.zennews.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zencon.zennews.R;
import com.zencon.zennews.adapter.TabViewPagerAdapter;
import com.zencon.zennews.fragments.NewsListFragment;

public class MainActivity extends FragmentActivity {

    private ViewPager mainPageViewPager;

    private final String[] categoryList = {"lead-news","bangladesh","politic","economy","world","sports",
            "entertainment","lifestyle","sciencetech","sciencetech","health","ctg"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();

        ImageLoader.getInstance().init(config);

        initUI();
    }

    private void initUI()
    {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.jokeTypeTab);

        tabLayout.addTab(tabLayout.newTab().setText("Latest News"));
        tabLayout.addTab(tabLayout.newTab().setText("Politics"));
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));

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
}
