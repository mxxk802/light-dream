package com.mxxk.lightdream.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * HttpResult
 *
 * @auther zhang
 * @date 2020/6/22
 **/
public class HttpResult {

    private boolean result;
    private int code;//code码
    private String message;//回调信息
    private String body;//返回内容

    public HttpResult(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public HttpResult(int code, String message, String body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public HttpResult(boolean result, int code, String message, String body) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public HttpResult(boolean result, int code, String body) {
        this.result = result;
        this.code = code;
        this.body = body;
    }

    public HttpResult() {
    }

    /**
     * 获取失败
     * @param code
     * @param message
     * @param body
     * @return
     */
    public static String callBackFail(int code, String message, String body){
        return JSONObject.toJSONString(new HttpResult(false,code,message,"NULL"));
    }

    /**获取成功
     *
     *
     * @param code
     * @param message
     * @param body
     * @return
     */
    public static String callBackSuccess(int code, String message, String body){
        return JSONObject.toJSONString(new HttpResult(true,code,message,body));
    }

    /**
     * 不要备注信息
     * @param result
     * @param code
     * @param body
     * @return
     */
    public static String callBackSuccess(boolean result, int code, String body){
        return JSONObject.toJSONString(new HttpResult(true,code,body));
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
