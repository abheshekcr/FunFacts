package com.zencon.zennews.activity;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zencon.zennews.R;
import com.zencon.zennews.model.News;

public class NewsPageActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();

        setContentView(R.layout.activity_news_page);

        Bundle args = getIntent().getExtras();

        String newsDetail = args.getString("NewsDetail");

        News news = gson.fromJson(newsDetail, News.class);

        initUI(news);
    }

    private void initUI(News news)
    {
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView newsTextView  = (TextView) findViewById(R.id.newsTextView);
        ImageView newsImageView = (ImageView) findViewById(R.id.newsImageView);

        titleTextView.setText(news.getTitle());
        newsTextView.setText(Html.fromHtml(news.getContent()));

        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance

        imageLoader.displayImage(news.getThumbNailPath(), newsImageView);
    }
}

