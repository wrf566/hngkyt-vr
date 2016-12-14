package com.hngkyt.vr.model;

import java.util.Arrays;

/**
 * Created by wrf on 2016/12/5.
 */

public class VideoGroupModel {

    private String name;

    private VideoItemModel[] mVideoItemModels;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VideoItemModel[] getVideoItemModels() {
        return mVideoItemModels;
    }

    public void setVideoItemModels(VideoItemModel[] videoItemModels) {
        mVideoItemModels = videoItemModels;
    }

    @Override
    public String toString() {
        return "VideoGroupModel{" +
                "name='" + name + '\'' +
                ", mVideoItemModels=" + Arrays.toString(mVideoItemModels) +
                '}';
    }
}
