package com.mxxk.lightdream.config;

import com.mxxk.lightdream.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * InitCache
 *
 * @auther zhang
 * @date 2020/6/6
 **/
@Component
public class InitCache implements ApplicationRunner{

    @Autowired
    private ICacheService iCacheService;

    @Override
    @Async
    public void run(ApplicationArguments args) throws Exception {
        iCacheService.initDictCache();
        iCacheService.initExpressionCache();
    }
}
