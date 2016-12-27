package com.hngkyt.vr.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/12/20.
 */

public class VideoChannelList implements Parcelable {


    private List<VedioChannel> vedioCategoryList;

    public List<VedioChannel> getVedioCategoryList() {
        return vedioCategoryList;
    }

    public void setVedioCategoryList(List<VedioChannel> vedioCategoryList) {
        this.vedioCategoryList = vedioCategoryList;
    }

    public static class VedioChannel implements Parcelable {
        @Override
        public String toString() {
            return "VedioChannel{" +
                    "id=" + id +
                    ", logoImgUrl='" + logoImgUrl + '\'' +
                    ", smallLogoImgUrl='" + smallLogoImgUrl + '\'' +
                    ", vedioCategoryName='" + vedioCategoryName + '\'' +
                    '}';
        }

        /**
         * id : 1
         * logoImgUrl : http://192.168.0.104:8080/yuexinvr/img/largeCategory/sport.png
         * smallLogoImgUrl : http://192.168.0.104:8080/yuexinvr/img/smallCategory/sport.png
         * vedioCategoryName : 极限
         */

        private int id;
        private String logoImgUrl;
        private String smallLogoImgUrl;
        private String vedioCategoryName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogoImgUrl() {
            return logoImgUrl;
        }

        public void setLogoImgUrl(String logoImgUrl) {
            this.logoImgUrl = logoImgUrl;
        }

        public String getSmallLogoImgUrl() {
            return smallLogoImgUrl;
        }

        public void setSmallLogoImgUrl(String smallLogoImgUrl) {
            this.smallLogoImgUrl = smallLogoImgUrl;
        }

        public String getVedioCategoryName() {
            return vedioCategoryName;
        }

        public void setVedioCategoryName(String vedioCategoryName) {
            this.vedioCategoryName = vedioCategoryName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.logoImgUrl);
            dest.writeString(this.smallLogoImgUrl);
            dest.writeString(this.vedioCategoryName);
        }

        public VedioChannel() {
        }

        protected VedioChannel(Parcel in) {
            this.id = in.readInt();
            this.logoImgUrl = in.readString();
            this.smallLogoImgUrl = in.readString();
            this.vedioCategoryName = in.readString();
        }

        public static final Creator<VedioChannel> CREATOR = new Creator<VedioChannel>() {
            @Override
            public VedioChannel createFromParcel(Parcel source) {
                return new VedioChannel(source);
            }

            @Override
            public VedioChannel[] newArray(int size) {
                return new VedioChannel[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.vedioCategoryList);
    }

    public VideoChannelList() {
    }

    protected VideoChannelList(Parcel in) {
        this.vedioCategoryList = new ArrayList<VedioChannel>();
        in.readList(this.vedioCategoryList, VedioChannel.class.getClassLoader());
    }

    public static final Parcelable.Creator<VideoChannelList> CREATOR = new Parcelable.Creator<VideoChannelList>() {
        @Override
        public VideoChannelList createFromParcel(Parcel source) {
            return new VideoChannelList(source);
        }

        @Override
        public VideoChannelList[] newArray(int size) {
            return new VideoChannelList[size];
        }
    };

    @Override
    public String toString() {
        return "VideoChannelList{" +
                "vedioCategoryList=" + vedioCategoryList +
                '}';
    }
}
