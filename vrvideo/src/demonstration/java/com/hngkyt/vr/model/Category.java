package com.hngkyt.vr.model;

/**
 * Created by wrf on 2016/12/29.
 */

public class Category {

    private String name;
    private int drawableId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", drawableId=" + drawableId +
                '}';
    }
}
