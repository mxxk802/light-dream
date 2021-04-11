package com.mxxk.lightdream.mapper;

import com.mxxk.lightdream.entity.dic.SystemDictTableEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SystemDictMapper
 *
 * @auther zhang
 * @date 2020/6/6
 **/
@Mapper
@Repository
public interface SystemDictMapper {

    List<SystemDictTableEntity> querySystemDictTable();

    SystemDictTableEntity queryDicById(int id);

    List<SystemDictTableEntity> queryDictionaryByTableName(String tableName);



}
