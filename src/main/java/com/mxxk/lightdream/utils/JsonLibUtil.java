package com.mxxk.lightdream.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * JsonLibUtil
 *
 * @auther zhang
 * @date 2021/1/24
 **/
public class JsonLibUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonLibUtil.class);

    public static String bean2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    public static byte[] obj2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        byte[] b = null;
        try {
            b = jsonObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info("JsonLibUtil 转化为 byte[] 失败！");
        }
        return b;
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Obj(byte b[], Class<T> objClass) {
        T t = null;
        try {
            t = (T) JSONObject.toBean(JSONObject.fromObject(new String(b, "utf-8")), objClass);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info("JsonLibUtil byte[]解析为 object 失败！");
        }
        return t;
    }

}
