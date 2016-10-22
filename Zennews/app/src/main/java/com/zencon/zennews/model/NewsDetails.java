package com.zencon.zennews.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aaa111 on 10/22/16.
 */
public class NewsDetails
{
    @SerializedName("title")
    private Title title;

    @SerializedName("ccw_thumbnail")
    private String thumbNailPath;

    @SerializedName("content")
    private Content content;

    public NewsDetails(Title title,String thumbNailPath,Content content)
    {
        this.title = title;
        this.thumbNailPath = thumbNailPath;
        this.content = content;
    }

    public Title getTitle()
    {
        return title;
    }

    public void setTitle(Title title)
    {
        this.title = title;
    }

    public String getThumbNailPath()
    {
        return thumbNailPath;
    }

    public void setThumbNailPath(String thumbNailPath) {
        this.thumbNailPath = thumbNailPath;
    }

    public Content getContent()
    {
        return content;
    }

    public void setContent(Content content)
    {
        this.content = content;
    }
}
