package com.mxxk.lightdream.entity.dic;

/**
 * DicBaseEntity
 *
 * @auther zhang
 * @date 2020/6/4
 **/
public class DicBaseEntity<T> {
    public DicBaseEntity() {
    }

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }


}
