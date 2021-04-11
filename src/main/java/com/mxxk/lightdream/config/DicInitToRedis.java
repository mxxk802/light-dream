package com.mxxk.lightdream.config;

import com.mxxk.lightdream.entity.dic.SystemDictTableEntity;
import com.mxxk.lightdream.mapper.SystemDictMapper;
import com.mxxk.lightdream.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * DicInitToRedis
 *
 * @auther zhang
 * @date 2021/1/26
 **/
@Configuration
public class DicInitToRedis {

    private static Logger logger = LoggerFactory.getLogger(DicInitToRedis.class);

    @Value("${dirName.dicEntityPackage}")
    public String DIC_PACKAGE;

    @Value("${dicNames.tableList}")
    private String tableNameArray;

    @Value("${dicNames.dicInit}")
    private boolean dicInit;

    @Autowired
    private SystemDictMapper systemDictMapper;

    @Autowired
    private DictionaryService dictionaryService;


    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public void queryDataSaveToRedis() {

        logger.info("*********************查询字典开始***************************");

        if (dicInit) {
            String tableList[] = tableNameArray.split(",");

            if (tableList != null && tableList.length > 0) {
                for (String tableName : tableList) {
                    List<SystemDictTableEntity> dicData = systemDictMapper.queryDictionaryByTableName(tableName);

                    String entityName = dictionaryService.getDicEntityName(tableName);
                    String key = DIC_PACKAGE + "." + entityName + "_all";
                    List<Object> array = new ArrayList<>();
                    try {
                        Class clzss = Class.forName(DIC_PACKAGE + "." + entityName);

                        for (SystemDictTableEntity s : dicData) {
                            Object obj = clzss.newInstance();
                            Method methods[] = clzss.getDeclaredMethods();
                            for (Method m : methods) {
                                if (m.getName().equals("setId")) {
                                    m.invoke(obj, s.getId());
                                }
                                if (m.getName().equals("setCode")) {
                                    m.invoke(obj, s.getCode());
                                }
                                if (m.getName().equals("setNameZh")) {
                                    m.invoke(obj, s.getNameZh());
                                }
                                if (m.getName().equals("setNameEn")) {
                                    m.invoke(obj, s.getNameEn());
                                }
                                if (m.getName().equals("setNameJa")) {
                                    m.invoke(obj, s.getNameJa());
                                }
                                if (m.getName().equals("setOrderNum")) {
                                    m.invoke(obj, s.getOrderNum());
                                }
                            }
                            array.add(obj);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                        logger.info("初始化字典表到redis发生异常");
                    }
                    logger.info("=====================将表:<{}>,初始化到redis,key:--{}开始================", tableName, key);
                    redisTemplate.opsForValue().set(key, array);
                    logger.info("=====================初始化表:<{}>,初始化到redis,key:--{}结束================", tableName, key);

                }
            }
        }
        logger.info("*********************查询字典结束***************************");

    }

    /**
     * 生成存入reids的字典key,规则包名+_all
     *
     * @param tableName
     * @return
     */
    public String generateKey(String tableName) {
        String array[] = tableName.split("_");
        StringBuffer entityName = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            String name = array[i];
            entityName.append(name.substring(0).toUpperCase() + name.substring(1, name.length()));
            entityName.append("_all");
        }
        return entityName.toString();
    }

//    public <T> List<T> getDicAllDataByName(T t){
//
//        List<T> list=new ArrayList<>();
//        Class clzss=t.getClass();
//        Method methods[]=clzss.getDeclaredMethods();
//        for(Method m:methods){
//            if(m.getName().equals()){
//
//            }
//        }
//        Object obj=redisTemplate.opsForValue().get(key);
//        list=(List<T>)obj;
//        return list;
//    }
}
