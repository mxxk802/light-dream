package com.mxxk.lightdream.entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * ApiResponse
 *
 * @auther zhang
 * @date 2020/6/24
 **/
public class ApiResponse implements Serializable {

    //状态码
    private Integer statusCode;
    //返回消息
    private String  message;
    //返回对象
    private Object data;

    public static String buildFailResponse(Integer statusCode, String message)
    {
        return JSONObject.toJSONString(new ApiResponse(statusCode,message,"NULL"));
    }


    public static String buildSuccessResponse(String message)
    {
        return JSONObject.toJSONString(new ApiResponse(org.springframework.http.HttpStatus.OK.value(),message,"NULL"));
    }


    public static String buildSuccessResponse(Integer statusCode, Object data)
    {
        return JSONObject.toJSONString(new ApiResponse(org.springframework.http.HttpStatus.OK.value(),"NULL",data));
    }


    public static String buildSuccessResponse(Integer statusCode,String message, Object data)
    {
        return JSONObject.toJSONString(new ApiResponse(org.springframework.http.HttpStatus.OK.value(),message,data));
    }


    public ApiResponse(Integer statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data=data;
    }

    public ApiResponse(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ApiResponse() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "statusCode=" + statusCode +
                ", Message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
