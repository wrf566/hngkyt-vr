package com.hngkyt.vr.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wrf on 2016/12/21.
 */

public class Video implements Parcelable {

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", vedioCategoryPId=" + vedioCategoryPId +
                ", vedioCategoryPName='" + vedioCategoryPName + '\'' +
                ", vedioCategoryId=" + vedioCategoryId +
                ", vedioCategoryName='" + vedioCategoryName + '\'' +
                ", isFree=" + isFree +
                ", money=" + money +
                ", vedioName='" + vedioName + '\'' +
                ", vedioNotes='" + vedioNotes + '\'' +
                ", vedioImgUrl='" + vedioImgUrl + '\'' +
                ", vedioUrl='" + vedioUrl + '\'' +
                ", playAmount=" + playAmount +
                ", addTime=" + addTime +
                '}';
    }

    /**
     * id : 1
     * vedioCategoryPId : 1
     * vedioCategoryPName : 宗教文化
     * vedioCategoryId : 3
     * vedioCategoryName : 基督教
     * isFree : null
     * money : null
     * vedioName : 第一个视频
     * vedioNotes : 简介简介
     * vedioImgUrl : aaaa
     * vedioUrl : aaa
     * playAmount : 2
     * addTime : null
     */

    private int id;
    private int vedioCategoryPId;
    private String vedioCategoryPName;
    private int vedioCategoryId;
    private String vedioCategoryName;
    private boolean isFree;
    private int money;
    private String vedioName;
    private String vedioNotes;
    private String vedioImgUrl;
    private String vedioUrl;
    private int playAmount;
    private long addTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVedioCategoryPId() {
        return vedioCategoryPId;
    }

    public void setVedioCategoryPId(int vedioCategoryPId) {
        this.vedioCategoryPId = vedioCategoryPId;
    }

    public String getVedioCategoryPName() {
        return vedioCategoryPName;
    }

    public void setVedioCategoryPName(String vedioCategoryPName) {
        this.vedioCategoryPName = vedioCategoryPName;
    }

    public int getVedioCategoryId() {
        return vedioCategoryId;
    }

    public void setVedioCategoryId(int vedioCategoryId) {
        this.vedioCategoryId = vedioCategoryId;
    }

    public String getVedioCategoryName() {
        return vedioCategoryName;
    }

    public void setVedioCategoryName(String vedioCategoryName) {
        this.vedioCategoryName = vedioCategoryName;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getVedioName() {
        return vedioName;
    }

    public void setVedioName(String vedioName) {
        this.vedioName = vedioName;
    }

    public String getVedioNotes() {
        return vedioNotes;
    }

    public void setVedioNotes(String vedioNotes) {
        this.vedioNotes = vedioNotes;
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

    public Video() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.vedioCategoryPId);
        dest.writeString(this.vedioCategoryPName);
        dest.writeInt(this.vedioCategoryId);
        dest.writeString(this.vedioCategoryName);
        dest.writeByte(this.isFree ? (byte) 1 : (byte) 0);
        dest.writeInt(this.money);
        dest.writeString(this.vedioName);
        dest.writeString(this.vedioNotes);
        dest.writeString(this.vedioImgUrl);
        dest.writeString(this.vedioUrl);
        dest.writeInt(this.playAmount);
        dest.writeLong(this.addTime);
    }

    protected Video(Parcel in) {
        this.id = in.readInt();
        this.vedioCategoryPId = in.readInt();
        this.vedioCategoryPName = in.readString();
        this.vedioCategoryId = in.readInt();
        this.vedioCategoryName = in.readString();
        this.isFree = in.readByte() != 0;
        this.money = in.readInt();
        this.vedioName = in.readString();
        this.vedioNotes = in.readString();
        this.vedioImgUrl = in.readString();
        this.vedioUrl = in.readString();
        this.playAmount = in.readInt();
        this.addTime = in.readLong();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
