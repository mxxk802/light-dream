package com.mxxk.lightdream.mapper;


import com.mxxk.lightdream.entity.Video;
import com.mxxk.lightdream.entity.Webstie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WebsiteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Video
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Video
     *
     * @mbggenerated
     */
    int insert(Webstie record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Video
     *
     * @mbggenerated
     */
    int insertSelective(Webstie record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Video
     *
     * @mbggenerated
     */
    Video selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Video
     *
     * @mbggenerated
     */
    List<Webstie> selectAllInfo();


    int updateByPrimaryKeySelective(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Video
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Webstie record);
}