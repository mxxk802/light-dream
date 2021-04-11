package com.mxxk.lightdream.dto;

import com.mxxk.lightdream.entity.ShareNature;
import com.mxxk.lightdream.entity.Shares;

import java.util.List;
import java.util.Map;

/**
 * ShareHandler
 *
 * @auther zhang
 * @date 2020/12/16
 **/
public interface ShareHandler {



    /**
     * explain: 根据语言和模块名字获取配置
     * @param language
     * @param shares
     * @param modelName
     * @return
     */
    Map<String,String> getShareNatureByLanguage(String language, List<ShareNature> shares, String modelName);

    /**
     * explain: 获取标题配置
     * @param language
     * @param fields
     * @param modelName
     * @return
     */
    Map<String,String> getShareTitle(String language, List<ShareNature> fields, String modelName);

    /**
     * explain: 根据配置如果配置所持有的字段值在实际中不为空则需要提取出来
     * @param shareColum
     * @param shares
     * @return
     */
    Map<String,String> getShareDataByNature(Map<String, String> shareColum, Shares shares);

    /**
     * explain:将图片信息加入上下文环境
     * @param imgByte
     * @param context
     * @param report
     */
    //void putImageByteToContext(Map imgByte, IContext context, IXDocReport report);




}
