package com.mxxk.lightdream.entity;

/**
 * Project
 *
 * @auther zhang
 * @date 2020/12/21
 **/
public class Project {

    private String name;
    private String info;

    public Project(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
