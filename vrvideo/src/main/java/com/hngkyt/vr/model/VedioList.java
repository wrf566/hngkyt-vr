package com.hngkyt.vr.model;

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
    private List<Video> vedioList;

    public String getVedioCategoryName() {
        return vedioCategoryName;
    }

    public void setVedioCategoryName(String vedioCategoryName) {
        this.vedioCategoryName = vedioCategoryName;
    }

    public List<Video> getVedioList() {
        return vedioList;
    }

    public void setVedioList(List<Video> vedioList) {
        this.vedioList = vedioList;
    }


}
