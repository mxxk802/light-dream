package com.mxxk.lightdream.mapper;

import com.mxxk.lightdream.entity.ShareNature;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShareNatureMapper {

    ShareNature selectByCode(String keyCode);

    List<ShareNature> selectAllShareNature();
}
