package com.mxxk.lightdream.dto;

import com.mxxk.lightdream.config.PackConfig;
import com.mxxk.lightdream.entity.ShareNature;
import com.mxxk.lightdream.entity.Shares;
import com.mxxk.lightdream.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/**
 * ShareReportAbstract
 *
 * @auther zhang
 * @date 2020/12/16
 **/
public abstract class ShareReportAbstract {

    private static Logger logger = LoggerFactory.getLogger(ShareReportAbstract.class);


    public String stockName;//股票名称
    public String language;//股票语言


    public ShareReportAbstract(String stockName, String language) {
        this.stockName = stockName;
        this.language = language;

    }

    /**
     * 获取详细信息
     *
     * @param shares
     * @param language
     * @param shareNatureList
     * @return
     */
    public ShareReportModel getShareReportData(Shares shares, String language, List<ShareNature> shareNatureList, PackConfig packConfig) {
        //ShareReportModel doc=new ShareReportModel(shares.getStockName(),language);//这样也可以不过
        ShareReportModel reportInfo = null;//这样也可以不过
        Class doc = null;
        try {
            doc = Class.forName(packConfig.getStockReport());
            Constructor docConstractor = doc.getDeclaredConstructor(String.class, String.class);
            reportInfo = (ShareReportModel) docConstractor.newInstance(shares.getStockName(), language);

            Field field[] = doc.getDeclaredFields();
            for (Field f : field) {//循环ShareReportModel。class字节码对象，可以拿到相应类中的属性和方法
                String fieldName = f.getName();
                if (StringUtils.isNotBlank(fieldName) && fieldName.endsWith("Model")) {

                    Class childField = Class.forName(f.getType().getName());//根据模块名字获取字节码对象
                    String moduleName = fieldName.substring(0, fieldName.length() - 5);
                    //获取带参数的一般方法 因为该参数是模块对象
                    Constructor constructor = childField.getConstructor(String.class, List.class, String.class, Shares.class);
                    Object moduel = (BaseModel)constructor.newInstance(language, shareNatureList, moduleName, shares);
                    f.set(reportInfo, moduel);
                    //执行doc对象中相应的模块的set方法给相应模块赋值

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            logger.info("股票报告信息封装异常！异常类名：");
        }
        return reportInfo;
    }


//    public ShareReportModel getShareReportData(Shares shares, String language, List<ShareNature> shareNatureList) {
//        ShareReportModel doc=new ShareReportModel(shares.getStockName(),language);
//        Class clzss= null;
//        try {
//            clzss=doc.getClass();
//            Field field[]=clzss.getDeclaredFields();
//            for(Field f:field){
//                String fieldName=f.getName();
//
//                if(StringUtils.isNotBlank(fieldName)&&fieldName.endsWith("Model")){
//                    String setMethodName="set"+StringUtils.upcaseFirstChar(fieldName);
//                    String moduleName=fieldName.substring(0,fieldName.length()-5);
//                    Class childField=Class.forName(DTO_PACK+"."+StringUtils.upcaseFirstChar(fieldName));
//                    Method setMethod=clzss.getDeclaredMethod(setMethodName,childField);
//                    // Constructor con=childField.getConstructor();
//                    Object obj=childField.newInstance();
//                    // BaseModel mod=(BaseModel)obj;
//                    Method init=childField.getDeclaredMethod("initModel",String.class,List.class,String.class,Shares.class);
//                    init.invoke(obj,language,shareNatureList,moduleName,shares);
//                    //mod.initModel(language,shareNatureList,moduleName,stockService);
//                    setMethod.invoke(doc,obj);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return doc;
//    }


    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
