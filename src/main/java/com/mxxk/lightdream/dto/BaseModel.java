package com.mxxk.lightdream.dto;

import com.mxxk.lightdream.entity.ShareNature;
import com.mxxk.lightdream.entity.Shares;
import com.mxxk.lightdream.utils.Constant;
import com.mxxk.lightdream.utils.MapUtils;
import com.mxxk.lightdream.utils.StringUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * BaseModel 每个具体的模块都包含
 *
 * @auther zhang
 * @date 2020/8/27
 **/
public  class BaseModel implements ShareHandler {


    protected String moduleName;//模块名字
    protected boolean nullData;//模块数据是否为空
    protected Map<String,String> moduleTitle;//模块标题
    protected Map<String,String> fieldName;//数据列名展示
    protected Map<String,String> data;//数据集合

    public BaseModel() {
    }

    public BaseModel(String language, List<ShareNature> fields, String modelName,Shares shares) {
        this.moduleName = modelName;
        this.moduleTitle=getShareTitle(language,fields,modelName);
        this.fieldName=getShareNatureByLanguage(language,fields,modelName);
        this.data=getShareDataByNature(this.fieldName,shares);
        this.nullData=this.data.isEmpty()?true:false;
    }

//    /**
//     * 初始化报告并
//     * @param language
//     * @param fields
//     * @param modelName
//     * @param shares
//     */
//    public  void initModel(String language, List<ShareNature> fields, String modelName,Shares shares){
//
//        this.moduleName = moduleName;
//        this.moduleTitle=getShareTitle(language,fields,modelName);
//        this.fieldName=getShareNatureByLanguage(language,fields,modelName);
//        this.data=getShareDataByNature(this.fieldName,shares);
//        this.nullData=this.data.isEmpty()?true:false;
//
//    };

    @Override
    public Map<String, String> getShareNatureByLanguage(String language, List<ShareNature> shares, String modelName) {
        Map<String,String> map=new LinkedHashMap<>();

        if(shares!=null&&shares.size()>0){

            for(ShareNature nature:shares){
                String prefix=modelName+".";
                if(Constant.Language.CHINESE.getName().equals(language)&&nature.getKeyCode().contains(prefix)){
                    map.put(nature.getKeyCode().replace(prefix,""),nature.getNameCn());
                }
                if(Constant.Language.ENGLISH.getName().equals(language)&&nature.getKeyCode().contains(prefix)){
                    map.put(nature.getKeyCode().replace(prefix,""),nature.getNameEn());
                }
                if(Constant.Language.JAPANESE.getName().equals(language)&&nature.getKeyCode().contains(prefix)){
                    map.put(nature.getKeyCode().replace(prefix,""),nature.getNameJp());
                }
            }

        }
        return map;
    }

    /**
     * 根据模块名字获取标题
     * @param language
     * @param fields
     * @param modelName
     * @return
     */
    @Override
    public Map<String, String> getShareTitle(String language, List<ShareNature> fields, String modelName) {
        Map<String,String> map=new LinkedHashMap<>();

        if(fields!=null&&fields.size()>0){

            for(ShareNature nature:fields){

                if(Constant.Language.CHINESE.getName().equals(language)&&nature.getKeyCode().equals(modelName)){
                    map.put(nature.getKeyCode(),nature.getNameCn());
                }
                if(Constant.Language.ENGLISH.getName().equals(language)&&nature.getKeyCode().equals(modelName)){
                    map.put(nature.getKeyCode(),nature.getNameEn());
                }
                if(Constant.Language.JAPANESE.getName().equals(language)&&nature.getKeyCode().equals(modelName)){
                    map.put(nature.getKeyCode(),nature.getNameJp());
                }
            }
        }
        return map;
    }

    /**
     * 根据配置属性--配置字段以及具体字段有无值来决定具体字段的存留
     * @param shareColum
     * @param shares
     * @return
     */
    @Override
    public Map<String, String> getShareDataByNature(Map<String, String> shareColum, Shares shares) {
        Map<String,String> map=new LinkedHashMap<>();
        Map<String,String> shareData=null;
        try {
            shareData= MapUtils.objectToMap(shares);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(shareColum!=null&&!shareColum.isEmpty()&&shareData!=null){

            Iterator<Map.Entry<String,String>> it=shareColum.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String,String> entry=it.next();
                String key=entry.getKey();
                if(StringUtils.isNotBlank(shareData.get(key))){
                    map.put(key,shareData.get(key));
                }else{
                    it.remove();
                }
            }
        }
        return map;
    }



    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isNullData() {
        return nullData;
    }

    public void setNullData(boolean nullData) {
        this.nullData = nullData;
    }

    public Map<String, String> getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(Map<String, String> moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public Map<String, String> getFieldName() {
        return fieldName;
    }

    public void setFieldName(Map<String, String> fieldName) {
        this.fieldName = fieldName;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
