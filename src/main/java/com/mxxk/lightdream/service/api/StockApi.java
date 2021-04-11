package com.mxxk.lightdream.service.api;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * StockApi
 *
 * @auther zhang
 * @date 2020/6/21
 **/
@Service
public class StockApi {

     @Autowired
     private CloseableHttpClient httpClient;

     public String showStock(String requestJson){

          // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
          CloseableHttpClient httpClient = HttpClientBuilder.create().build();

          HttpGet get=new HttpGet("http://localhost:8082/stock/getAllStock");

          CloseableHttpResponse response=null;
          String result=null;
          try {
               response= httpClient.execute(get);

               HttpEntity httpEntity=response.getEntity();
               if(response.getStatusLine().getStatusCode()== HttpStatus.OK.value()){
                    System.out.println("股东查询成功，响应状态为:" + HttpStatus.OK.value());
                    result=EntityUtils.toString(httpEntity,"utf-8");
               }


          } catch (IOException e) {
               e.printStackTrace();
          }finally {
               try {
                    // 释放资源
                    if (httpClient != null) {
                         httpClient.close();
                    }
                    if (response != null) {
                         response.close();
                    }
               } catch (IOException e) {
                    e.printStackTrace();
               }
          }
          return result;

     }

}
