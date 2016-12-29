package com.hngkyt.vr.model;

/**
 * Created by wrf on 2016/12/29.
 */

public class LocalBanner {

    private int bannerdrawableId;
    private LocalVideo mLocalVideo;

    @Override
    public String toString() {
        return "LocalBanner{" +
                "bannerdrawableId=" + bannerdrawableId +
                ", mLocalVideo=" + mLocalVideo +
                '}';
    }

    public int getBannerdrawableId() {
        return bannerdrawableId;
    }

    public void setBannerdrawableId(int bannerdrawableId) {
        this.bannerdrawableId = bannerdrawableId;
    }

    public LocalVideo getLocalVideo() {
        return mLocalVideo;
    }

    public void setLocalVideo(LocalVideo localVideo) {
        mLocalVideo = localVideo;
    }
}
