package com.hngkyt.vr.net;

/**
 * Created by wrf on 2016/12/14.
 */

public interface Constants {

    String PATH_YUEXINVR = "yuexinvr/";
    //下面这几个路径是李彦波写的，所以以app开头
    String PATH_APP = "app/";
    String PATH_SENDCODE = PATH_YUEXINVR + PATH_APP + "sendCode";
    String PATH_REGISTER = PATH_YUEXINVR + PATH_APP + "register";
    String PATH_LOGIN = PATH_YUEXINVR + PATH_APP + "login";
    //下面这几个路径是刘琴写的，所以path有所不同
    String PATH_VEDIOAPP = "vedioApp/";
    String PATH_GETVEDIOCATEGORY = PATH_YUEXINVR + PATH_VEDIOAPP + "getVedioCategory";
    String PATH_GETCATEGORYVEDIOS = PATH_YUEXINVR + PATH_VEDIOAPP + "getCategoryVedios";
    String PATH_PLAYAMONT = PATH_YUEXINVR + PATH_VEDIOAPP + "playAmont";
    String PATH_GETVEDIOS = PATH_YUEXINVR + PATH_VEDIOAPP + "getVedios";
    String PATH_GETVEDIODETAIL = PATH_YUEXINVR + PATH_VEDIOAPP + "getVedioDetail";
    //又不同了...
    String PATH_GETVERSION = PATH_YUEXINVR + PATH_APP + "getVersion";

    String PHONE = "phone";


    String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
}
