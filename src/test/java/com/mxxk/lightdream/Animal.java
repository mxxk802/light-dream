package com.mxxk.lightdream;

/**
 * Animal
 *
 * @auther zhang
 * @date 2020/9/21
 **/
public class Animal {

    public void show(){
        System.out.println("我是没有返回值，不带参数的方法");
    }
    public void show(String name){
        System.out.println("我是没有返回值，带参数的方法----");


    }

    public String show(String name,int age){
        return "我是带有返回值类型的方法";
    }
}
