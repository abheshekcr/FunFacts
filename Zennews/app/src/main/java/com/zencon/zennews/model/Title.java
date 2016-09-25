package com.zencon.zennews.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 9/25/2016.
 */

public class Title
{
    @SerializedName("rendered")
    private String rendered;

    public Title(String rendered)
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
