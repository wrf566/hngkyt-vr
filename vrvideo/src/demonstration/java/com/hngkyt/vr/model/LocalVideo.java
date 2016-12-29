package com.hngkyt.vr.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.hngkyt.vr.utils.FileUtils;

import java.io.File;

/**
 * Created by wrf on 2016/12/29.
 */

public class LocalVideo implements Parcelable {

    private File videoFile;
    private File coverFile;
    private File categroyFile;
    private String name;


    public LocalVideo(File videoFile) {
        this.videoFile = videoFile;
    }

    public File getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(File videoFile) {
        this.videoFile = videoFile;
    }

    public File getCoverFile() {
        return new File(videoFile.getParentFile(),FileUtils.getFileNameNoExtension(videoFile)+".jpg");
    }

    public void setCoverFile(File coverFile) {
        this.coverFile = coverFile;
    }

    public File getCategroyFile() {
        return videoFile.getParentFile();
    }

    public String getName() {
        return FileUtils.getFileNameNoExtension(videoFile);

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.videoFile);
        dest.writeSerializable(this.coverFile);
        dest.writeSerializable(this.categroyFile);
        dest.writeString(this.name);
    }

    protected LocalVideo(Parcel in) {
        this.videoFile = (File) in.readSerializable();
        this.coverFile = (File) in.readSerializable();
        this.categroyFile = (File) in.readSerializable();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<LocalVideo> CREATOR = new Parcelable.Creator<LocalVideo>() {
        @Override
        public LocalVideo createFromParcel(Parcel source) {
            return new LocalVideo(source);
        }

        @Override
        public LocalVideo[] newArray(int size) {
            return new LocalVideo[size];
        }
    };

    @Override
    public String toString() {
        return "LocalVideo{" +
                "videoFile=" + videoFile +
                ", coverFile=" + getCoverFile() +
                ", categroyFile=" + getCategroyFile() +
                ", name='" + getName() + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        LocalVideo localVideo = (LocalVideo) o;
        return this.videoFile.equals(localVideo.getVideoFile());
    }
}
