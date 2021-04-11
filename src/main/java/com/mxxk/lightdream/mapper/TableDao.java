package com.mxxk.lightdream.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * TableDao
 *
 * @auther zhang
 * @date 2021/1/28
 **/
@Mapper
public interface  TableDao {

    @Select("select * from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<Map> listTable();

    @Select("select * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}")
     List<Map> listTableColumn(String tableName);
}
