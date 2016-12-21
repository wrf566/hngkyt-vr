package com.hngkyt.vr.net.been;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/12/20.
 */

public class CategoryVedios {


    /**
     * vedioList : [{"id":3,"name":"基督教","list":[{"id":38,"vedioCategoryPId":1,"vedioCategoryPName":"宗教文化","vedioCategoryId":3,"vedioCategoryName":"基督教","isFree":null,"money":null,"vedioName":"333333","vedioNotes":"4444444","vedioImgUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/148211401253945P9MUNJ.jpg","vedioUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/14821140076829BRKTA8G.mp4","playAmount":0},{"id":39,"vedioCategoryPId":1,"vedioCategoryPName":"宗教文化","vedioCategoryId":3,"vedioCategoryName":"基督教","isFree":null,"money":null,"vedioName":"hhhhhhh","vedioNotes":"55555555","vedioImgUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482114094505LR84XPH8.jpg","vedioUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482114081648ZDF51SXT.mp4","playAmount":0}]},{"id":4,"name":"佛教","list":[{"id":37,"vedioCategoryPId":1,"vedioCategoryPName":"宗教文化","vedioCategoryId":4,"vedioCategoryName":"佛教","isFree":null,"money":null,"vedioName":"好吧就这样","vedioNotes":"淡淡的答道","vedioImgUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482108723872UOMU1SLX.jpg","vedioUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/14821087330612XU52W1G.mp4","playAmount":0}]}]
     * vedioCategory : {"id":1,"pId":0,"vedioCategoryName":"宗教文化","smallLogoImgUrl":"http://192.168.0.104:8080/yuexinvr/img/category11.png","logoImgUrl":"http://192.168.0.104:8080/yuexinvr/img/category1.png","type":1,"addTime":1481534881000,"updateTime":null,"sysFlag":1}
     */

    private VedioCategoryBean vedioCategory;
    private List<VedioListBean> vedioList;
    public VedioCategoryBean getVedioCategory() {
        return vedioCategory;
    }

    public void setVedioCategory(VedioCategoryBean vedioCategory) {
        this.vedioCategory = vedioCategory;
    }

    public List<VedioListBean> getVedioList() {
        return vedioList;
    }

    public void setVedioList(List<VedioListBean> vedioList) {
        this.vedioList = vedioList;
    }

    @Override
    public String toString() {
        return "CategoryVedios{" +
                "vedioCategory=" + vedioCategory +
                ", vedioList=" + vedioList +
                '}';
    }

    public static class VedioCategoryBean {
        /**
         * id : 1
         * pId : 0
         * vedioCategoryName : 宗教文化
         * smallLogoImgUrl : http://192.168.0.104:8080/yuexinvr/img/category11.png
         * logoImgUrl : http://192.168.0.104:8080/yuexinvr/img/category1.png
         * type : 1
         * addTime : 1481534881000
         * updateTime : null
         * sysFlag : 1
         */

        private int id;
        private int pId;
        private String vedioCategoryName;
        private String smallLogoImgUrl;
        private String logoImgUrl;
        private int type;
        private long addTime;
        private long updateTime;
        private int sysFlag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPId() {
            return pId;
        }

        public void setPId(int pId) {
            this.pId = pId;
        }

        public String getVedioCategoryName() {
            return vedioCategoryName;
        }

        public void setVedioCategoryName(String vedioCategoryName) {
            this.vedioCategoryName = vedioCategoryName;
        }

        public String getSmallLogoImgUrl() {
            return smallLogoImgUrl;
        }

        public void setSmallLogoImgUrl(String smallLogoImgUrl) {
            this.smallLogoImgUrl = smallLogoImgUrl;
        }

        public String getLogoImgUrl() {
            return logoImgUrl;
        }

        public void setLogoImgUrl(String logoImgUrl) {
            this.logoImgUrl = logoImgUrl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }

    public static class VedioListBean implements Parcelable {
        @Override
        public String toString() {
            return "VedioListBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", list=" + list +
                    '}';
        }

        /**
         * id : 3
         * name : 基督教
         * list : [{"id":38,"vedioCategoryPId":1,"vedioCategoryPName":"宗教文化","vedioCategoryId":3,"vedioCategoryName":"基督教","isFree":null,"money":null,"vedioName":"333333","vedioNotes":"4444444","vedioImgUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/148211401253945P9MUNJ.jpg","vedioUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/14821140076829BRKTA8G.mp4","playAmount":0},{"id":39,"vedioCategoryPId":1,"vedioCategoryPName":"宗教文化","vedioCategoryId":3,"vedioCategoryName":"基督教","isFree":null,"money":null,"vedioName":"hhhhhhh","vedioNotes":"55555555","vedioImgUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482114094505LR84XPH8.jpg","vedioUrl":"http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482114081648ZDF51SXT.mp4","playAmount":0}]
         */

        private int id;
        private String name;
        private List<VideoBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<VideoBean> getList() {
            return list;
        }

        public void setList(List<VideoBean> list) {
            this.list = list;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeList(this.list);
        }

        public VedioListBean() {
        }

        protected VedioListBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.list = new ArrayList<VideoBean>();
            in.readList(this.list, VideoBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<VedioListBean> CREATOR = new Parcelable.Creator<VedioListBean>() {
            @Override
            public VedioListBean createFromParcel(Parcel source) {
                return new VedioListBean(source);
            }

            @Override
            public VedioListBean[] newArray(int size) {
                return new VedioListBean[size];
            }
        };
    }
}
