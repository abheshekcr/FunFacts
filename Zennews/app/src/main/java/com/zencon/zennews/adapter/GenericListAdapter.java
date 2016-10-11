package com.zencon.zennews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class GenericListAdapter<T> extends BaseAdapter
{
    private Context context;
    protected ArrayList<T> items ;
    protected LayoutInflater inflater;

    public GenericListAdapter(Context context, ArrayList<T> items)
    {
        this.items    = items;
        this.context  = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public T getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return getViewId(items, position);
    }

    public void add(T item)
    {
        this.items.add(item);

        notifyDataSetChanged();
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent)
    {
        return createView(position, convertView, parent);

        //return convertView;
    }

    protected abstract View createView(int position,View convertView,ViewGroup holder);

    protected abstract long getViewId(ArrayList<T> items,int pos);
}
