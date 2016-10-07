package com.zencon.zennews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.zencon.zennews.fragments.NewsListFragment;

public class TabViewPagerAdapter extends FragmentStatePagerAdapter
{
    private int numOfTabs;
    private Fragment currentFragment;

    public TabViewPagerAdapter(FragmentManager fragmentManager, int numOfTabs)
    {
        super(fragmentManager);

        this.numOfTabs = numOfTabs;
    }

    public Fragment getItem(int position)
    {
        return new NewsListFragment();
    }

    public int getCount()
    {
        return numOfTabs;
    }

    public Fragment getCurrentFragment()
    {
        return currentFragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object)
    {
        if(getCurrentFragment() != object)
        {
            currentFragment = (Fragment)object;
        }

        super.setPrimaryItem(container,position,object);
    }

    public CharSequence getPageTitle(int position)
    {
        return "";
    }
}
