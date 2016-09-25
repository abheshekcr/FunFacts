package com.zencon.zennews.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 9/25/2016.
 */

public class News
{
    @SerializedName("link")
    private String link;

    @SerializedName("title")
    private Title title;

    @SerializedName("ccw_thumbnail")
    private String thumbNailPath;

    public News(String link, Title title,String thumbNailPath)
    {
        this.link = link;
        this.title = title;
        this.thumbNailPath = thumbNailPath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getThumbNailPath() {
        return thumbNailPath;
    }

    public void setThumbNailPath(String thumbNailPath) {
        this.thumbNailPath = thumbNailPath;
    }
}
