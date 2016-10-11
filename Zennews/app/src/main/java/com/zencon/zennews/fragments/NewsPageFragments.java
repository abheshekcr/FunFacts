package com.zencon.zennews.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zencon.zennews.R;

/**
 * Created by aaa111 on 10/8/2016.
 */
public class NewsPageFragments extends Fragment
{
    private View mainPageView;
    private ViewPager mainPageViewPager;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedBundleInstance)
    {
        mainPageView = inflater.inflate(R.layout.fragment_news_list_page,viewGroup,false);

        return mainPageView;
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);


    }


    public void loadNewsList()
    {

    }


}
