package com.mxxk.lightdream.utils;

import java.util.*;

public class Test {



    public static void main(String[] args) {
//        String a="abc";
//        String b=new String("abc");
//        String c="abc";
//        String d=new String("abc");
//        System.out.println(a==b);
//        System.out.println(a==c);
//        System.out.println(b==d);
//        System.out.println(a.equals(b));
//        System.out.println(d.equals(b));
//        StringBuffer str=new StringBuffer("abcdefg");
//        str.append("hijklm");
//        System.out.println(str.reverse());
//        System.out.println(str.indexOf("b"));
//        System.out.println(str.charAt(2));
//        String len=new String("zhangpengfei");
//        System.out.println(len.toUpperCase());
        java.util.Random random=new java.util.Random();
        int result=random.nextInt(31);// 返回[0,10)集合中的整数，注意不包括10
        System.out.println(result);
        Map hs=new HashMap();
        hs.put("zpf","123");
        Map ta=new Hashtable();
        Map tred=new TreeMap();
        tred.put(10,"zpfi");
        tred.put(2,"ywl");
        tred.put(5,"zxl");
        Iterator it=tred.keySet().iterator();
        while(it.hasNext()){
           Object key=it.next();
            System.out.println("键为"+key+"所对应的值为"+tred.get(key));
        }
//        System.out.println(hs.containsKey("zpf"));
//        System.out.println(hs.containsValue("123"));

        System.out.println(StringUtils.isNum("--"));

        double a=0.12;
        double b=5.45;

        System.out.println(StringUtils.divide(a,b));

    }
}
