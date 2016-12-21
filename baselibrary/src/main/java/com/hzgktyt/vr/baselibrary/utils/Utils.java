package com.hzgktyt.vr.baselibrary.utils;

import android.content.Context;

/**
 * <pre>
 *     time  : 16/12/08
 *     desc  :
 * </pre>
 */
public class Utils {

    public static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }
}
