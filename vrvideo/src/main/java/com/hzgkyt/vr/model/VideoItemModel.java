package com.hzgkyt.vr.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wrf on 2016/12/5.
 */

public class VideoItemModel implements Parcelable {
    private String name;
    private String coverURL;


    public VideoItemModel(String name, String coverURL) {
        this.name = name;
        this.coverURL = coverURL;
    }

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
        return "VideoItemModel{" +
                "name='" + name + '\'' +
                ", coverURL='" + coverURL + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.coverURL);
    }

    protected VideoItemModel(Parcel in) {
        this.name = in.readString();
        this.coverURL = in.readString();
    }

    public static final Parcelable.Creator<VideoItemModel> CREATOR = new Parcelable.Creator<VideoItemModel>() {
        @Override
        public VideoItemModel createFromParcel(Parcel source) {
            return new VideoItemModel(source);
        }

        @Override
        public VideoItemModel[] newArray(int size) {
            return new VideoItemModel[size];
        }
    };
}
