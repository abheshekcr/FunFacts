package com.zencon.zennews.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zencon.zennews.R;
import com.zencon.zennews.model.News;

import java.util.ArrayList;

/**
 * Created by USER on 9/25/2016.
 */

class ViewHolder
{
    public TextView text;
    public ImageView image;
}

public class NewsListAdapter extends GenericListAdapter<News>
{
    private int layoutId;
    private Activity root;

    public NewsListAdapter(Context context,Activity root,ArrayList<News> items)
    {
        super(context,items);

        this.root = root;
        this.layoutId = R.layout.news_row_item;
    }

    public View createView(int position,View convertView,ViewGroup parent)
    {
        ViewHolder holder = null;

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.news_row_item,null);

            holder = new ViewHolder();

            holder.text = (TextView)convertView.findViewById(R.id.jokeTextView);

            holder.image = (ImageView) convertView.findViewById(R.id.newsThumbImageView);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        ViewHolder hd = (ViewHolder) convertView.getTag();

        News news = items.get(position);

        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance

        imageLoader.displayImage(news.getThumbNailPath(), holder.image);

        hd.text.setText(news.getTitle());

        return convertView;
    }

    public long getViewId(ArrayList<News> items,int pos)
    {
        return -1;
    }

      /*  private final Activity context;
        private final ArrayList<News> newsList;


        public NewsListAdapter(Activity context,ArrayList<News> newsList)
        {
            super(context, R.layout.news_row_item);

            this.context = context;
            this.newsList = newsList;
        }


        public int getCount()
    {
        return   newsList.size();
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            // reuse views
            if (rowView == null)
            {
                LayoutInflater inflater = context.getLayoutInflater();

                rowView = inflater.inflate(R.layout.news_row_item, null);

                // configure view holder
                ViewHolder viewHolder = new ViewHolder();

                viewHolder.text = (TextView) rowView.findViewById(R.id.newsTitleTextView);
                viewHolder.image = (ImageView) rowView.findViewById(R.id.newsThumbImageView);

                rowView.setTag(viewHolder);
            }

            Log.d("Data:",newsList.get(position).getTitle());

            // fill data
            ViewHolder holder = (ViewHolder) rowView.getTag();

            News news = newsList.get(position);

            ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance

            imageLoader.displayImage(news.getThumbNailPath(), holder.image);

            holder.text.setText(news.getTitle());

            return rowView;
        }
    }*/
}

