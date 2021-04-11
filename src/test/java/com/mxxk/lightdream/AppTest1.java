package com.mxxk.lightdream;

import com.mxxk.LightDreamApplication;
import com.mxxk.lightdream.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * AppTest1
 *
 * @auther zhang
 * @date 2020/6/4
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= LightDreamApplication.class)
public class AppTest1 {

    @Autowired
    private StringRedisTemplate stringTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testredis(){
        System.out.println("测试类");
        redisTemplate.opsForValue().set("hah","wo shige ");//默认存入二进制
    }

    @Test
    public void test2(){
        String userInfo=(String)redisTemplate.opsForValue().get("hah");
        System.out.println(userInfo);
    }

    @Test
    public void testObjectJson(){
        User user=new User();
        user.setUsername("李四");
        user.setAge(15);
        user.setPassword("123");
        redisTemplate.opsForValue().set("li",user);
    }
    @Test
    public void getObjectJson(){
        User user=(User)redisTemplate.opsForValue().get("li");
        System.out.println(user.getUsername()+user.getPassword()+user.getAge());

    }
 }


