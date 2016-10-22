package com.zencon.zennews.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zencon.zennews.R;
import com.zencon.zennews.activity.NewsPageActivity;
import com.zencon.zennews.adapter.NewsListAdapter;
import com.zencon.zennews.model.News;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa111 on 9/26/16.
 */
public class NewsListFragment extends  android.support.v4.app.Fragment
{
    private View mainPageView;
    private ListView newsListView;
    private FrameLayout frameLayout;
    private ViewPager mainPageViewPager;
    private ProgressDialog progressDialog;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedBundleInstance)
    {
        mainPageView = inflater.inflate(R.layout.fragment_news_list_page,viewGroup,false);

        frameLayout = (FrameLayout) mainPageView.findViewById(R.id.newsPageLayout);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Please Wait");

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
                "http://zennews24.com/wp-json/wp/v2/posts?fields=id,title,ccw_thumbnail&filter[category_name]="+category+"&filter[posts_per_page]=20",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();

                        Log.d("response:",response);

                        try
                        {
                            final Gson gson = new Gson();

                            String jsonOutput = response;
                            Type listType = new TypeToken<List<News>>(){}.getType();
                            ArrayList<News> posts = (ArrayList<News>) gson.fromJson(jsonOutput, listType);

                            /*for(News n : posts)
                            {
                                Log.d("Title : " , n.getTitle().getRendered());
                                Log.d("Thumbnail : " , n.getThumbNailPath());
                                Log.d("Link : " , n.getLink());
                            }*/

                            final NewsListAdapter newsListAdapter = new NewsListAdapter(getActivity(),getActivity(),posts);

                            listView.setAdapter(new NewsListAdapter(getActivity(),getActivity(),posts));

                            newsListView = (ListView) mainPageView.findViewById(R.id.newsListView);

                            newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                            {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                                {
                                    Toast.makeText(getActivity().getApplicationContext(), "Touch", Toast.LENGTH_LONG).show();

                                    Intent i = new Intent(getActivity().getApplicationContext(), NewsPageActivity.class);

                                    i.putExtra("NewsID", newsListAdapter.getItem(position).getId() + "");

                                    startActivity(i);
                                }
                            });
                        }
                        catch(Exception ex)
                        {
                            Log.d("ex:",ex.getMessage());
                        }
                        Log.d("Response : ",response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                    }
        });

        queue.add(stringRequest);

        progressDialog.show();
    }

    public interface NewsListItemClickListener
    {
        public void onNewsItemClicked(News news);
    }
}
