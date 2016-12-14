package com.hngkyt.vr.model;

import android.graphics.drawable.Drawable;

/**
 * Created by wrf on 2016/12/12.
 */

public class VideoChannelModel {
    private String name;
    private String imageURL;
    private int drawableID;
    private Drawable mDrawable;


    public VideoChannelModel() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }

    public VideoChannelModel(String name, String imageURL, int drawableID, Drawable drawable) {
        this.name = name;
        this.imageURL = imageURL;
        this.drawableID = drawableID;
        mDrawable = drawable;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }


    @Override
    public String toString() {
        return "VideoChannelModel{" +
                "name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", drawableID=" + drawableID +
                ", mDrawable=" + mDrawable +
                '}';
    }
}
