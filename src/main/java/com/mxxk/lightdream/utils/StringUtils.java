package com.mxxk.lightdream.utils;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by tongda-kf02 on 2016/7/22.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 全角转半角
     * @param fullStr
     * @return
     */
    public static String full2Half(String fullStr) {
        if(org.apache.commons.lang3.StringUtils.isBlank(fullStr)){
            return fullStr;
        }
        char[] c = fullStr.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 65281 && c[i] <= 65374) {
                c[i] = (char) (c[i] - 65248);
            } else if (c[i] == 12288) { // 空格
                c[i] = (char) 32;
            }
        }
        return new String(c).trim();
    }

    /**
     * 判断字符串a是否在数组b中
     * @param a
     * @param b
     * @return
     */
    public static boolean inArray(String a,String[] b){
        boolean boo = false;
        if(org.apache.commons.lang3.StringUtils.isBlank(a)||b==null||b.length==0){
            return boo;
        }else{
            for(String c:b){
                if(c.equals(a)){
                    boo = true;
                    break;
                }
            }
            return boo;
        }
    }



    public static String plus(String ... value){
        DecimalFormat decimalFormat = new DecimalFormat("##,###");
        try{
            Long l = 0L;
            if(value.length>0){
                for(int i=0;i<value.length;i++){
                    Long v = 0l;
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(value[i])){
                        v = decimalFormat.parse(value[i]).longValue();
                    }
                    l = l+v;
                }
                return l.toString();
            }
        }catch (Exception e){
            logger.error("求和出错",e);
        }
        return "";

    }

    /**
     * value-value1;
     * @param value
     * @param value1
     * @return
     */
    public static String minus(String value,String ... value1){
        if(org.apache.commons.lang3.StringUtils.isBlank(value)){
            return "";
        }
        DecimalFormat decimalFormat = new DecimalFormat("##,###");
        try{
            Long lvalue = decimalFormat.parse(value).longValue();
            Long l = 0L;
            if(value1.length>0){
                for(int i=0;i<value1.length;i++){
                    Long v = 0l;
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(value1[i])){
                        v = decimalFormat.parse(value1[i]).longValue();
                    }
                    l = l+v;
                }
                return ((Long)(lvalue-l)).toString();
            }
        }catch (Exception e){
            logger.error("求差出错",e);
        }
        return "";

    }

    /**
     * 除法
     * @param d1
     * @param d2
     * @return
     */
    public static Double minus(Double d1,Double d2) {
        BigDecimal bigDecimal = new BigDecimal(d1-d2);

        double f1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        return f1;
    }
    /**
     * 除法
     * @param d1
     * @param d2
     * @return
     */
    public static Double divide(Double d1,Double d2) {

        // 除数，实现2/12
        Double d=d1/d2;
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }


    /**
     * 去掉汉字
     * 如：111,111,111 美元
     * @return 111,111,111
     */
    public static String removeUnit(String currency){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(currency)){
            currency = currency.replaceAll("[\\u4e00-\\u9fa5]","");
            return currency.trim();
        }
        return "";
    }

    /**
     * 只保留小数和整数
     * @param str
     * @return
     */
    public static String keepNum(String str){

        return  str.replaceAll("[^0-9\\.]","");
    }

    /**
     * 获取中文字符
     * @param v
     * @return
     */
    public static String getChineseCharacter(String v){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(v)){
            Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
            Matcher matcher = pattern.matcher(v);
            if(matcher.find()){
                v=matcher.group();
            }else{
                return "";
            }
        }
        return v;
    }

    /**
     * 拼接n个数组
     * @param arrs
     * @return
     */
    public static File[] appendArr(File[]...arrs){
        if(arrs==null||arrs.length<=0){
            return null;
        }

        List<File> list = new ArrayList();
        for(File[] arr:arrs){
            if(arr==null||arr.length==0){
                continue;
            }
            for(File t:arr){
                list.add(t);
            }
        }
        if(list.size()<=0){
            return null;
        }
        File[] result = new File[list.size()];
        for(int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 去掉文件名称的非法字符
     * @param fileName
     * @return
     */
    public static String fixFileName(String fileName){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(fileName)){
            return fileName.replace("/","").replace("\\","").replace("*","").replace(":","").replace("?","").replace("<","").replace(">","").replace("|","");
        }
        return fileName;
    }
    /**
     * 取map里的值，如果没取到返回空串儿
     * @param map
     * @param key
     * @return
     */
    public static String getMapValStr(Map map,String key){
        if(map!=null&&!map.isEmpty()){
            Object val = map.get(key);
            return val==null?"":val.toString();
        }
        return "";
    }
    public static String obj2Str(Object obj){

        return obj==null?"":obj.toString();

    }

    /**
     * 格式化文件路径
     * @param url
     * @return
     */
    public static String formatFilePath(String url){
        if(StringUtils.isNotBlank(url)){
            url.replace("\\","/");
        }
        return url;
    }

    /**
     * 将字符串首字母大写
     * @return
     */
    public static String upcaseFirstChar(String str){
        if(str!=null||str!=""){
            return str.substring(0,1).toUpperCase()+str.substring(1,str.length());
        }else{
            return null;
        }
    }

    /**
     * 分组
     * @param data
     * @param eachPieces
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitToPieces(Collection<T> data,int eachPieces){
        if(CollectionUtils.isEmpty(data)){
            return new ArrayList<>();
        }
        if(eachPieces<=0){
           throw new IllegalArgumentException("参数有误");
        }
        List<List<T>> result=new ArrayList<>();

        for(int i=0;i<data.size();i+=eachPieces){
            result.add(data.stream().skip(i).limit(eachPieces).collect(Collectors.toList()));
        }
        return result;
    }

    //序列化对象
    public static byte[] serialize(Object obj) throws IOException {
        String serStr = "";
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
                serStr = b.toString("ISO-8859-1");
                serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
            }
            return serStr.getBytes();
        }
    }

    //反序列化
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return o.readObject();
            }
        }
    }

    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean  isNotBlank(String str){

        boolean isTrue=false;
        if(str!=null&&str!=""&&!str.isEmpty()) {
            isTrue = true;
        }
        return isTrue;
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return (str==null||str=="")?true:false;
    }



    public static boolean isNum(String param){
        boolean isMatch=Pattern.matches("^(\\-|\\+)?\\d+(\\.\\d+)?$",param);
        return isMatch;
    }

    public static void main(String[] args) {
        String pack="com.mxxk.lightdream.dto.";
        String str="entSurvey";
       // ShareReportModel shareReportModel=new ShareReportModel("entSurvey","CHINESE");

        System.out.println("哈哈");


        try {
            Class clzss=Class.forName("com.mxxk.lightdream.dto.ShareReportModel");
            //Constructor con = clzss.getConstructor();
            //Object obj = con.newInstance();

            Field[] fields = clzss.getDeclaredFields();
            for(Field field:fields) {
                System.out.println("getFields(): "+field.getName());
                String fieldName=field.getName();
                if(fieldName.endsWith("Model")){
                    System.out.println("hah");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Class clzss=Class.forName(pack+upcaseFirstChar(str)+"Mapper");
//            Object obj=clzss.newInstance();
//            BaseModel baseModel=(BaseModel) obj;
//            //baseModel.initModel();
//            Method initModel=clzss.getDeclaredMethod("initModel");
//            //initModel.invoke();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println();
    }
}
