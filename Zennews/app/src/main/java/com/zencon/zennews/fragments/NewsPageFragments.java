package com.zencon.zennews.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zencon.zennews.R;
import com.zencon.zennews.adapter.NewsListAdapter;
import com.zencon.zennews.model.News;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
        mainPageView = inflater.inflate(R.layout.fragment_news_page,viewGroup,false);

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
