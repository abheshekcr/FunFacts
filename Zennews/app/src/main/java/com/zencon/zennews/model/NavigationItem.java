package com.zencon.zennews.model;

public class NavigationItem
{
    private String title;
    private int leftIconId;

    public NavigationItem(String title, int leftIconId)
    {
        this.title       = title;
        this.leftIconId  = leftIconId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getLeftIconId()
    {
        return leftIconId;
    }

    public void setLeftIconId(int leftIconId)
    {
        this.leftIconId = leftIconId;
    }
}
