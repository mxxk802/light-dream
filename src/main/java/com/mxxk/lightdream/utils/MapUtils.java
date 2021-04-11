package com.mxxk.lightdream.utils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MapUtils
 *
 * @auther zhang
 * @date 2020/7/25
 **/
public class MapUtils {

    /*** 将Object对象里面的属性和值转化成Map对象
     ** @param obj
     * @return
             * @throws IllegalAccessException
     */
    public static Map<String,String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String,String> map = new LinkedHashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
                map.put(fieldName, value==null?"":value.toString());
            } catch (IllegalAccessException e) {
                System.out.println("反射获取对象属性值异常:"+e);
            }

        }
        return map;
    }
}
