package com.mxxk.lightdream;

import com.mxxk.lightdream.dto.ShareReportModel;
import com.mxxk.lightdream.entity.User;
import com.mxxk.lightdream.service.XdocReportGenerateWord;
import com.mxxk.lightdream.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * AppTest
 *
 * @auther zhang
 * @date 2020/6/2
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private XdocReportGenerateWord xdoc;

    @Test
    public void testObject() throws Exception{
        User user=new User();
        user.setUsername("zpf");
        user.setPassword("123456");
        user.setAge(18);
        ValueOperations<String,User> options= redisTemplate.opsForValue();
        options.set("test",user);
        boolean exist=redisTemplate.hasKey("test");
        System.out.println("redis是否存在相应的key："+exist);
        User getuser=(User)redisTemplate.opsForValue().get("test");
        System.out.println("从redis中获取的user"+getuser.toString());
    }
    @Test
    public void quyu(){
        int a=1008%10;
        System.out.println(a);
    }

    @Test
    public void startgy(){
        ShareReportModel module=new ShareReportModel("上汽集团","CHINESE");//这样也可以不过

        Class doc= null;
        try {
            doc=Class.forName("com.mxxk.lightdream.dto.ShareReportModel");
            Field fields[]=doc.getDeclaredFields();
            Method[] methods=doc.getDeclaredMethods();
            Constructor[] constructors=doc.getDeclaredConstructors();
            for(Method method:methods){//循环ShareReportModel。class字节码对象，可以拿到相应类中的属性和方法
                String fieldName=method.getName();
                Class child=null;
                if(StringUtils.isNotBlank(fieldName)&&fieldName.endsWith("Model")&&fieldName.startsWith("get")){

                    System.out.println(fieldName);
                    method.getDefaultValue();
                    System.out.println(method.getDefaultValue());


                    //执行doc对象中相应的模块的set方法给相应模块赋值

                }
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Test
    public void createDoc(){
        xdoc.createLaborContract();
    }
}
