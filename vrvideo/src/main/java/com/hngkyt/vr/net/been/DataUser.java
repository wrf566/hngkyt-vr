package com.hngkyt.vr.net.been;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wrf on 2016/12/19.
 */

public class DataUser implements Parcelable {


    /**
     * id : 4
     * userName : 15123123123
     * realName : null
     * password : 202CB962AC59075B964B07152D234B70
     * phone : 15123123123
     * faceImgUrl : null
     * religiousBelief : 1
     * addTime : 1481622005000
     * sysFlag : 1
     * age : 18
     * job : 工作
     * code :
     */

    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String ID = "id";

    public static final String PASSWORD_TYPE = "passwordType";

    public static final int TYPE_REGISTER =1;
    public static final int TYPE_FORGET_PASSWORD =2;

    private int id;
    private String userName;
    private String realName;
    private String password;
    private String phone;
    private String faceImgUrl;
    private int religiousBelief;
    private long addTime;
    private int sysFlag;
    private int age;
    private String job;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getFaceImgUrl() {
        return faceImgUrl;
    }

    public void setFaceImgUrl(String faceImgUrl) {
        this.faceImgUrl = faceImgUrl;
    }

    public int getReligiousBelief() {
        return religiousBelief;
    }

    public void setReligiousBelief(int religiousBelief) {
        this.religiousBelief = religiousBelief;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public int getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(int sysFlag) {
        this.sysFlag = sysFlag;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DataUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", faceImgUrl='" + faceImgUrl + '\'' +
                ", religiousBelief=" + religiousBelief +
                ", addTime=" + addTime +
                ", sysFlag=" + sysFlag +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", code='" + code + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.userName);
        dest.writeString(this.realName);
        dest.writeString(this.password);
        dest.writeString(this.phone);
        dest.writeString(this.faceImgUrl);
        dest.writeInt(this.religiousBelief);
        dest.writeLong(this.addTime);
        dest.writeInt(this.sysFlag);
        dest.writeInt(this.age);
        dest.writeString(this.job);
        dest.writeString(this.code);
    }

    public DataUser() {
    }

    protected DataUser(Parcel in) {
        this.id = in.readInt();
        this.userName = in.readString();
        this.realName = in.readString();
        this.password = in.readString();
        this.phone = in.readString();
        this.faceImgUrl = in.readString();
        this.religiousBelief = in.readInt();
        this.addTime = in.readLong();
        this.sysFlag = in.readInt();
        this.age = in.readInt();
        this.job = in.readString();
        this.code = in.readString();
    }

    public static final Parcelable.Creator<DataUser> CREATOR = new Parcelable.Creator<DataUser>() {
        @Override
        public DataUser createFromParcel(Parcel source) {
            return new DataUser(source);
        }

        @Override
        public DataUser[] newArray(int size) {
            return new DataUser[size];
        }
    };
}
