package com.zencon.zennews.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zencon.zennews.R;
import com.zencon.zennews.model.NewsDetails;

public class NewsPageActivity extends Activity
{
    private TextView titleTextView;
    private TextView newsTextView;
    private ImageView newsImageView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();

        setContentView(R.layout.activity_news_page);

        Bundle args = getIntent().getExtras();

        String newsID = args.getString("NewsID");

        initUI();

        loadNewsDetails(newsID);
    }

    private void initUI()
    {
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        newsTextView  = (TextView) findViewById(R.id.newsTextView);
        newsImageView = (ImageView) findViewById(R.id.newsImageView);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
    }

    public void loadNewsDetails(String id)
    {

        RequestQueue queue = Volley.newRequestQueue(this);

        Log.d("URL:","http://zennews24.com/wp-json/wp/v2/posts/"+id+"?fields=id,title,link,content,ccw_thumbnail");

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://zennews24.com/wp-json/wp/v2/posts/"+id+"?fields=id,title,link,content,ccw_thumbnail",
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

                            //Type listType = new TypeToken<List<NewsDetails>>(){}.getType();

                            NewsDetails newsDetails = gson.fromJson(jsonOutput, NewsDetails.class);

                            titleTextView.setText(newsDetails.getTitle().getRendered());
                            newsTextView.setText(Html.fromHtml(newsDetails.getContent().getRendered()));

                            //ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance

                            //imageLoader.displayImage(newsDetails.getThumbNailPath(), newsImageView);
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
}

