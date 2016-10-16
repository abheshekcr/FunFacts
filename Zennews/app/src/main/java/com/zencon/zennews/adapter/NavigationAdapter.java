package com.zencon.zennews.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import com.zencon.zennews.R;
import com.zencon.zennews.model.NavigationItem;

import java.util.ArrayList;


class ItemHolder
{
    //public ImageView navIconView;
    public TextView  navTextView;
}

public class NavigationAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<NavigationItem> navigationItems;

    public NavigationAdapter(Context context, ArrayList<NavigationItem> navigationItems)
    {
        this.context = context;
        this.navigationItems = navigationItems;
    }

    public int getCount()
    {
        return navigationItems.size();
    }

    public Object getItem(int position)
    {
        return navigationItems.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent)
    {
        /*if(convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.navigation_drawer_list_item, null);
        }

        ImageView navLeftIcon = (ImageView) convertView.findViewById(R.id.nav_icon);
        TextView navText = (TextView)  convertView.findViewById(R.id.nav_title);

        navText.setText(navigationItems.get(position).getTitle());
        ImageLoader.getInstance().displayImage("drawable://" + navigationItems.get(position).getLeftIconId(), navLeftIcon);

        return convertView;*/


            ItemHolder holder = null;

            if (convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

                convertView =  inflater.inflate(R.layout.navigation_drawer_list_item, null);

                holder = new ItemHolder();

                //holder.navIconView = (ImageView) convertView.findViewById(R.id.nav_icon);
                holder.navTextView = (TextView)  convertView.findViewById(R.id.nav_title);

                convertView.setTag(holder);
            }
            else
            {
                holder = (ItemHolder) convertView.getTag();
            }

            //ImageLoader.getInstance().displayImage("drawable://" + navigationItems.get(position).getLeftIconId(),  holder.navIconView);
            holder.navTextView.setText(navigationItems.get(position).getTitle());


        return convertView;
    }
}
