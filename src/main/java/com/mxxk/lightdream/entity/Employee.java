package com.mxxk.lightdream.entity;

/**
 * Employee
 *
 * @auther zhang
 * @date 2020/12/21
 **/

public class Employee {

    private String name;
    private String job;
    private String result;

    public Employee(String name, String job, String result) {
        this.name = name;
        this.job = job;
        this.result = result;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
