package com.hzgkyt.vr.model;

import java.util.List;

/**
 * Created by wrf on 2016/12/1.
 */

public class ViedoGroupModel {

    private String name;
    private List<VideoItem> mVideoItemList;

    public static class VideoItem {
        String name;
        String coverURL;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCoverURL() {
            return coverURL;
        }

        public void setCoverURL(String coverURL) {
            this.coverURL = coverURL;
        }


        @Override
        public String toString() {
            return "VideoItem{" +
                    "name='" + name + '\'' +
                    ", coverURL='" + coverURL + '\'' +
                    '}';
        }
    }



    @Override
    public String toString() {
        return "ViedoGroupModel{" +
                "name='" + name + '\'' +
                ", mVideoItemList=" + mVideoItemList +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VideoItem> getVideoItemList() {
        return mVideoItemList;
    }

    public void setVideoItemList(List<VideoItem> videoItemList) {
        mVideoItemList = videoItemList;
    }


}
