package com.mxxk.lightdream.service;

import org.springframework.stereotype.Service;


public interface ICacheService {

    void initDictCache();//加载字典表缓存
    void initExpressionCache();//加载表达式缓存

}
