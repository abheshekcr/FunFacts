package com.zencon.zennews.model;

import com.google.gson.annotations.SerializedName;

public class News
{
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    @SerializedName("ccw_thumbnail")
    private String thumbNailPath;

    @SerializedName("content")
    private String content;

    public News(int id,String link, String title,String thumbNailPath,String content)
    {
        this.id = id;
        this.link = link;
        this.title = title;
        this.thumbNailPath = thumbNailPath;
        this.content = content;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getThumbNailPath()
    {
        return thumbNailPath;
    }

    public void setThumbNailPath(String thumbNailPath)
    {
        this.thumbNailPath = thumbNailPath;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
