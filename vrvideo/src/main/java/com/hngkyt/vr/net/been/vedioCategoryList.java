package com.hngkyt.vr.net.been;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/12/20.
 */

public class vedioCategoryList implements Parcelable {


    private List<VedioChannel> mVedioChannelList;

    public List<VedioChannel> getVedioChannelList() {
        return mVedioChannelList;
    }

    public void setVedioChannelList(List<VedioChannel> vedioChannelList) {
        this.mVedioChannelList = vedioChannelList;
    }

    public static class VedioChannel implements Parcelable {
        /**
         * id : 1
         * logoImgUrl : http://192.168.0.104:8080/yuexinvr/img/category1.png
         * smallLogoImgUrl : http://192.168.0.104:8080/yuexinvr/img/category11.png
         * vedioCategoryName : 宗教文化
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
        public String toString() {
            return "VedioChannel{" +
                    "id=" + id +
                    ", logoImgUrl='" + logoImgUrl + '\'' +
                    ", smallLogoImgUrl='" + smallLogoImgUrl + '\'' +
                    ", vedioCategoryName='" + vedioCategoryName + '\'' +
                    '}';
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
    public String toString() {
        return "vedioCategoryList{" +
                "mVedioChannelList=" + mVedioChannelList +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mVedioChannelList);
    }

    public vedioCategoryList() {
    }

    protected vedioCategoryList(Parcel in) {
        this.mVedioChannelList = new ArrayList<VedioChannel>();
        in.readList(this.mVedioChannelList, VedioChannel.class.getClassLoader());
    }

    public static final Parcelable.Creator<vedioCategoryList> CREATOR = new Parcelable.Creator<vedioCategoryList>() {
        @Override
        public vedioCategoryList createFromParcel(Parcel source) {
            return new vedioCategoryList(source);
        }

        @Override
        public vedioCategoryList[] newArray(int size) {
            return new vedioCategoryList[size];
        }
    };
}
