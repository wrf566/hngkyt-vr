package com.hngkyt.vr.model;

/**
 * Created by wrf on 2016/12/22.
 */

public class Version {

    /**
     * id : 1
     * vesionCode : 1
     * versionName : 1.0.0
     * updateTitle : 1.0.0版本更新
     * url : http://www.baidu.com
     * addTime : 1482373921000
     * sysFlag : 1
     * updateContent : 视频模块上线sdfsdfds
     */

    private int id;
    private int vesionCode;
    private String versionName;
    private String updateTitle;
    private String url;
    private long addTime;
    private int sysFlag;
    private String updateContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVesionCode() {
        return vesionCode;
    }

    public void setVesionCode(int vesionCode) {
        this.vesionCode = vesionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
}
