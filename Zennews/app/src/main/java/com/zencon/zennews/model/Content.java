package com.zencon.zennews.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aaa111 on 10/22/16.
 */
public class Content
{
    @SerializedName("rendered")
    private String rendered;

    public Content(String rendered)
    {
        this.rendered = rendered;
    }

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }
}
