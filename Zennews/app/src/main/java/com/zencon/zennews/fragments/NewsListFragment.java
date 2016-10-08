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
 * Created by aaa111 on 9/26/16.
 */
public class NewsListFragment extends Fragment
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


    public void loadNewsList(String category)
    {
        final ListView listView = (ListView) mainPageView.findViewById(R.id.newsListView);

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://zennews24.com/wp-json/wp/v2/posts/?filter[category_name]="+category,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("response:",response);

                        try {
                            Gson gson = new Gson();

                            String jsonOutput = response;
                            Type listType = new TypeToken<List<News>>(){}.getType();
                            ArrayList<News> posts = (ArrayList<News>) gson.fromJson(jsonOutput, listType);

                            for(News n : posts)
                            {
                                Log.d("Title : " , n.getTitle());
                                Log.d("Thumbnail : " , n.getThumbNailPath());
                                Log.d("Link : " , n.getLink());
                            }

                            listView.setAdapter(new NewsListAdapter(getActivity(),posts));
                        }
                        catch(Exception ex)
                        {
                            Log.d("ex:",ex.getMessage());
                        }
                        Log.d("Response : ",response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // mTextView.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);
    }


}
