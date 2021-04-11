package com.mxxk.lightdream.service;

import com.mxxk.lightdream.entity.dic.DicBaseEntity;
import com.mxxk.lightdream.entity.dic.DicStockType;
import com.mxxk.lightdream.entity.dic.SystemDictTableEntity;
import com.mxxk.lightdream.mapper.SystemDictMapper;
import com.mxxk.lightdream.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * DictionaryService
 *
 * @auther zhang
 * @date 2020/6/3
 **/
@Service
public class DictionaryService {

   @Value("${dirName.dicEntityPackage}")
   public String DIC_PACKAGE;

   @Autowired
   private RedisTemplate redisTemplate;

   @Autowired
   private SystemDictMapper systemDictMapper;


   public DicBaseEntity getDicEntity(String tableName) {

      Class clzss = null;
      DicBaseEntity entity=null;
      try {
         String array[] = tableName.split("_");
         StringBuffer entityName = new StringBuffer();
         for (int i = 0; i < array.length; i++) {
            String name = array[i];
            entityName.append(name.substring(0).toUpperCase() + name.substring(1, name.length()));
         }
         if (entityName.length() > 0) {
            clzss = Class.forName(DIC_PACKAGE + entityName.toString());
            if(clzss!=null && clzss.newInstance() instanceof DicBaseEntity){
               entity=(DicBaseEntity)clzss.newInstance();
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      return entity;

   }

   /**
    * 根据表名初始化字典实体对象
    * @param t
    * @param tableName
    * @param <T>
    * @return
    */
   public <T> T getDicEntity(T t,String tableName){
      Class clzss = null;
      String objName="";
      try {
         String array[] = tableName.split("_");
         StringBuffer entityName = new StringBuffer();
         for (int i = 0; i < array.length; i++) {
            String name = array[i];
            name= StringUtils.upcaseFirstChar(name);
            entityName.append(name);
         }
         objName=entityName.toString();
         if (objName.length() > 0) {
            clzss = Class.forName(DIC_PACKAGE + entityName.toString());
            if(clzss!=null){
               t=(T)clzss.newInstance();
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
       return t;
   }

   /**
    * 根据表名获取类名
    * @param tableName
    * @return
    */
   public String getDicEntityName(String tableName){

      String objName="";
      try {
         String array[] = tableName.split("_");
         StringBuffer entityName = new StringBuffer();
         for (int i = 0; i < array.length; i++) {
            String name = array[i];
            name= StringUtils.upcaseFirstChar(name);
            entityName.append(name);
         }
         objName=entityName.toString();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return objName;
   }

   /**
    * 获取所有的dicstockType
    * @return
    */
   public List<SystemDictTableEntity> getAllDicStockType(){
     // redisTemplate.opsForValue().get();
      List<SystemDictTableEntity> dicData=null;
      Class clzss=DicStockType.class;
      String packName=clzss.getPackage().getName();
      String dicstockKey=DicStockType.class.getPackage().getName()+"_all";
      dicData=systemDictMapper.queryDictionaryByTableName("dic_stock_type");
      return dicData;
   }

   /**
    * 从redis中获取某个字典的全部信息
    * @param t
    * @param <T>
    * @return
    */
   public <T> List<T> getDicAllDataByNameFromReis(T t){

      List<T> list=new ArrayList<>();
      String key=t.getClass().getPackage().getName()+"_all";
      Object obj=redisTemplate.opsForValue().get(key);
      list=(List<T>)obj;
      return list;
   }

   /**
    * 先去查询redis，如果没有值去查mysql
    * @param
    * @param
    * @return
    */
   public <T> List<T> getDicAllDataMergeSource(Class c){
      List<T> list=null;

      String key=c.getName()+"_all";
      Long startTime=System.currentTimeMillis();
      Object obj=redisTemplate.opsForValue().get(key);
      //Object obj=null;
      if(obj!=null){
         list= (List<T>)obj;
      }else{
         list=(List<T>)systemDictMapper.queryDictionaryByTableName("dic_stock_type");

      }
      Long entTime=System.currentTimeMillis();

       System.out.println(entTime-startTime);

      return list;
   }

   /**
    * 根据code获取T
    * @param t
    * @param code
    * @param <T>
    * @return
    */
   public <T> T getOneDicDataMergeSource(Class t,String code){
      List<T> list=getDicAllDataMergeSource(t);
      T o=null;
      if(list!=null&&list.size()>0){
         try {
            for(T obj:list){
               Method method=obj.getClass().getMethod("getCode");
               String val=method.invoke(obj,null)!=null?method.invoke(obj,null).toString():"";
               if(code.equals(val)){
                  o=(T)obj;
                  break;
               }
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return o;
   }

   /**
    * 将父类数据转存在子类中
    * @param from
    * @param to
    * @param <T>
    */
   public <T> void putDataFromFatherToChild(List<? super T> from, List<T> to){
      for(int i=0;i<from.size();i++){
         to.add((T)from.get(i));
      }

   }

   public static <T> void copy(List<? super T> dest, List<T> src)
   {
      for (int i=0; i<src.size(); i++)
         dest.set(i,src.get(i));
   }
}
