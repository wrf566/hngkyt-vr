package com.hngkyt.vr.net.been;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wrf on 2016/12/21.
 */

public class VideoBean implements Parcelable {

    /**
     * id : 42
     * vedioCategoryId : 3
     * isFree : 1
     * money : 900
     * vedioName : 反反复复丰富
     * vedioImgUrl : http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482115860774OSLB4ZCV.jpg
     * vedioUrl : http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482115870760QYA62O6G.mp4
     * vedioStatus : 1
     * playAmount : 0
     * addTime : 1482115906000
     * updateTime : 1482226357000
     * sysFlag : 1
     * vedioNotes : null
     */

    private int id;
    private String vedioName;
    private String vedioImgUrl;
    private String vedioUrl;
    private int playAmount;
    private long addTime;
    private String vedioNotes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getVedioName() {
        return vedioName;
    }

    public void setVedioName(String vedioName) {
        this.vedioName = vedioName;
    }

    public String getVedioImgUrl() {
        return vedioImgUrl;
    }

    public void setVedioImgUrl(String vedioImgUrl) {
        this.vedioImgUrl = vedioImgUrl;
    }

    public String getVedioUrl() {
        return vedioUrl;
    }

    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }



    public int getPlayAmount() {
        return playAmount;
    }

    public void setPlayAmount(int playAmount) {
        this.playAmount = playAmount;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }


    public String getVedioNotes() {
        return vedioNotes;
    }

    public void setVedioNotes(String vedioNotes) {
        this.vedioNotes = vedioNotes;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "id=" + id +
                ", vedioName='" + vedioName + '\'' +
                ", vedioImgUrl='" + vedioImgUrl + '\'' +
                ", vedioUrl='" + vedioUrl + '\'' +
                ", playAmount=" + playAmount +
                ", addTime=" + addTime +
                ", vedioNotes='" + vedioNotes + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.vedioName);
        dest.writeString(this.vedioImgUrl);
        dest.writeString(this.vedioUrl);
        dest.writeInt(this.playAmount);
        dest.writeLong(this.addTime);
        dest.writeString(this.vedioNotes);
    }

    public VideoBean() {
    }

    protected VideoBean(Parcel in) {
        this.id = in.readInt();
        this.vedioName = in.readString();
        this.vedioImgUrl = in.readString();
        this.vedioUrl = in.readString();
        this.playAmount = in.readInt();
        this.addTime = in.readLong();
        this.vedioNotes = in.readString();
    }

    public static final Parcelable.Creator<VideoBean> CREATOR = new Parcelable.Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel source) {
            return new VideoBean(source);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };
}
