package com.mxxk.lightdream.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * FastJsonUtil
 *
 * @auther zhang
 * @date 2021/1/24
 **/
public class FastJsonUtil {
    private static Logger logger= LoggerFactory.getLogger(FastJsonUtil.class);

    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static byte[] obj2Json(Object obj) {
        byte b[]=null;
        try {
            b= JSON.toJSONString(obj).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info("FastJsonUtil 对象转化为对象失败！");
        }
        return b;
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }
    public static <T> T json2Obj(byte b[], Class<T> objClass) {
        T obj=null;
        try {
            obj=JSON.parseObject(new String(b,"utf-8"), objClass);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info("FastJsonUtil 解析为对象失败！");
        }
        return obj;
    }

}
