package com.mxxk.lightdream.utils;

public class IdUtils {
    public static String createId(int length){

        String id = "";
        for(int i=0;i<length;i++){
            id+=String.valueOf((int)(Math.random()*10));
        }
        return id;
    }
}
