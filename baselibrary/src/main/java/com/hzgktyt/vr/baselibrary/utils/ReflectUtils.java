package com.hzgktyt.vr.baselibrary.utils;

import java.lang.reflect.Field;

/**
 * 反射工具类
 * Created by wrf on 2016/12/6.
 */

public class ReflectUtils {

    public static Object getSuperClassField(Object obj,String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getSuperclass().getDeclaredField(name);
        field.setAccessible(true);
       return field.get(obj);
    }


}
