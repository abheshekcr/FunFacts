package com.zencon.zennews.activity;

import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zencon.zennews.R;
import com.zencon.zennews.adapter.NewsListAdapter;
import com.zencon.zennews.model.News;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.newsListView);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();

        ImageLoader.getInstance().init(config);

        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://zennews24.com/wp-json/wp/v2/posts/?filter[category_name]=lead-news",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Gson gson = new Gson();

                            String jsonOutput = response;
                            Type listType = new TypeToken<List<News>>(){}.getType();
                            ArrayList<News> posts = (ArrayList<News>) gson.fromJson(jsonOutput, listType);

                            for(News n : posts)
                            {
                                Log.d("Title : " , n.getTitle().getRendered());
                                Log.d("Thumbnail : " , n.getThumbNailPath());
                                Log.d("Link : " , n.getLink());
                            }

                            listView.setAdapter(new NewsListAdapter(MainActivity.this,posts));
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
