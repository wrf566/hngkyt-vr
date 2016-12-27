package com.hngkyt.vr.net.been;

import java.util.List;

/**
 * Created by wrf on 2016/12/23.
 */

public class BannerList {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * url : http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/14821087330612XU52W1G.mp4
         * imgUrl : http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1481881974685GGB4BCYX.png
         */

        private int id;
        private String url;
        private String imgUrl;

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
    }
}
