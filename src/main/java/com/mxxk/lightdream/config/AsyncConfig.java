package com.mxxk.lightdream.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * AsyncConfig 如果存入redis 的数据量较大，可以采用线程池方式，如果数据量小。可不用
 *
 * @auther zhang
 * @date 2020/6/6
 **/
//@Configuration
//@EnableAsync
public class AsyncConfig {

    private static final Logger log= LoggerFactory.getLogger(AsyncConfig.class);

    @Bean
    public TaskExecutor taskExecutor(){
        log.info("-----------------线程池初始化开始-----------------");

        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();

        // 设置核心线程数
        executor.setCorePoolSize(10);
        // 设置最大线程数
        executor.setMaxPoolSize(20);
        // 设置队列容量
        executor.setQueueCapacity(40);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        executor.setThreadNamePrefix("async-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);

        log.info("-----------------线程池初始化结束-----------------");

        return executor;
    }

}
