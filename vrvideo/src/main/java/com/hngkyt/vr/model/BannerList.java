package com.hngkyt.vr.model;

import java.util.List;

/**
 * Created by wrf on 2016/12/23.
 */

public class BannerList {


    public static final int TYPE_VIDEO = 1;
    public static final int TYPE_URL = 2;


    private List<Banner> bannerList;

    public List<Banner> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }

    @Override
    public String toString() {
        return "BannerList{" +
                "bannerList=" + bannerList +
                '}';
    }

    public static class Banner {
        /**
         * id : 1
         * url : http://www.baidu.com
         * imgUrl : http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1481881974685GGB4BCYX.png
         * type : 2
         * vedioId : null
         */

        private int id;
        private String url;
        private String imgUrl;
        private int type;
        private int vedioId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getVedioId() {
            return vedioId;
        }

        public void setVedioId(int vedioId) {
            this.vedioId = vedioId;
        }

        @Override
        public String toString() {
            return "Banner{" +
                    "id=" + id +
                    ", url='" + url + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", type=" + type +
                    ", vedioId=" + vedioId +
                    '}';
        }
    }
}
