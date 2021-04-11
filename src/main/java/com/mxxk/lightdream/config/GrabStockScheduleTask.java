package com.mxxk.lightdream.config;

import com.mxxk.lightdream.entity.Shares;
import com.mxxk.lightdream.mapper.SharesMapper;
import com.mxxk.lightdream.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GrabStockScheduleTask
 *
 * @auther zhang
 * @date 2021/2/2
 **/
@Configuration
//@EnableScheduling
public class GrabStockScheduleTask {

    @Autowired
    private SharesMapper sharesMapper;

    @Autowired
    private HttpClientUtils httpClientUtils;

    //3.添加定时任务
    @Scheduled(cron = "* * 0/3 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        System.out.println("执行静态定时任务时间: " + LocalDateTime.now());

        List<Shares> sharesList=sharesMapper.selectAllShares();
        String http = "http://localhost:8081/stock/tradeData/";

        if(sharesList!=null&&sharesList.size()>0){
            for(Shares share:sharesList){
                System.out.println(share.getStockCode());
                http=http+share.getStockCode();
                //httpClientUtils.doGet(http);
            }
        }
    }
}
