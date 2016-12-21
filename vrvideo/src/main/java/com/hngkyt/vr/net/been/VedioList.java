package com.hngkyt.vr.net.been;

import java.util.List;

/**
 * Created by wrf on 2016/12/21.
 */

public class VedioList {

    /**
     * vedioCategoryName : 基督教
     * vedioList : [{"id":42,"vedioCategoryId":3,"isFree":1,"money":900,"vedioName":"反反复复丰富","vedioImgUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482115860774OSLB4ZCV.jpg","vedioUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482115870760QYA62O6G.mp4","vedioStatus":1,"playAmount":0,"addTime":1482115906000,"updateTime":1482226357000,"sysFlag":1,"vedioNotes":null},{"id":40,"vedioCategoryId":3,"isFree":0,"money":0,"vedioName":"333333","vedioImgUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482115713021RR6YIOFP.jpg","vedioUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482115717906C1Z3ZLQI.mp4","vedioStatus":1,"playAmount":0,"addTime":1482115748000,"updateTime":null,"sysFlag":1,"vedioNotes":null},{"id":39,"vedioCategoryId":3,"isFree":1,"money":3300,"vedioName":"hhhhhhh","vedioImgUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482114094505LR84XPH8.jpg","vedioUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482114081648ZDF51SXT.mp4","vedioStatus":1,"playAmount":0,"addTime":1482114138000,"updateTime":null,"sysFlag":1,"vedioNotes":null},{"id":38,"vedioCategoryId":3,"isFree":0,"money":0,"vedioName":"333333","vedioImgUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/148211401253945P9MUNJ.jpg","vedioUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/14821140076829BRKTA8G.mp4","vedioStatus":1,"playAmount":1,"addTime":1482114037000,"updateTime":null,"sysFlag":1,"vedioNotes":null}]
     */

    private String vedioCategoryName;
    private List<VedioListBean> vedioList;

    public String getVedioCategoryName() {
        return vedioCategoryName;
    }

    public void setVedioCategoryName(String vedioCategoryName) {
        this.vedioCategoryName = vedioCategoryName;
    }

    public List<VedioListBean> getVedioList() {
        return vedioList;
    }

    public void setVedioList(List<VedioListBean> vedioList) {
        this.vedioList = vedioList;
    }

    public static class VedioListBean {
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
        private int vedioCategoryId;
        private int isFree;
        private int money;
        private String vedioName;
        private String vedioImgUrl;
        private String vedioUrl;
        private int vedioStatus;
        private int playAmount;
        private long addTime;
        private long updateTime;
        private int sysFlag;
        private String vedioNotes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVedioCategoryId() {
            return vedioCategoryId;
        }

        public void setVedioCategoryId(int vedioCategoryId) {
            this.vedioCategoryId = vedioCategoryId;
        }

        public int getIsFree() {
            return isFree;
        }

        public void setIsFree(int isFree) {
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

        public int getVedioStatus() {
            return vedioStatus;
        }

        public void setVedioStatus(int vedioStatus) {
            this.vedioStatus = vedioStatus;
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

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getSysFlag() {
            return sysFlag;
        }

        public void setSysFlag(int sysFlag) {
            this.sysFlag = sysFlag;
        }

        public String getVedioNotes() {
            return vedioNotes;
        }

        public void setVedioNotes(String vedioNotes) {
            this.vedioNotes = vedioNotes;
        }
    }
}
