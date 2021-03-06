package com.mxxk.lightdream.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mxxk.lightdream.config.ApiConfig;
import com.mxxk.lightdream.config.PackConfig;
import com.mxxk.lightdream.dto.ShareReportModel;
import com.mxxk.lightdream.dto.ShareTradeDataModel;
import com.mxxk.lightdream.entity.ShareNature;
import com.mxxk.lightdream.entity.Shares;
import com.mxxk.lightdream.entity.TradeData;
import com.mxxk.lightdream.entity.UpAndDownRanking;
import com.mxxk.lightdream.utils.*;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * StockService
 *
 * @auther zhang
 * @date 2020/7/23
 **/
@Service
public class StockService {
    private Logger logger= LoggerFactory.getLogger(StockService.class);
    public static final String DTO_PACK="com.mxxk.lightdream.dto";

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;

    protected static final String CHINESE = "CHINESE";
    protected static final String ENGLISH = "ENGLISH";
    protected static final String JAPANESE = "JAPANESE";
    protected static final String KOREA= "KOREA";

    /**
     * ??????????????????(???????????????????????????)
     * @param id
     * @return
     */
    public Shares getShares(Integer id){
        String url= apiConfig.getStarUrl()+"/stock/getOneDetail/"+id;
        String shareDataStr=httpClientUtils.doGet(url);
        Shares shares= JSON.parseObject(shareDataStr,Shares.class);
        logger.info("????????????????????????:{}??????????????????",url);
        return shares;
    }

    /**
     * ????????????????????????
     * @return
     */
    public ShareReportModel getShareReportData(Shares shares, String language, List<ShareNature> shareNatureList,PackConfig packConfig){
        ShareReportModel doc=new ShareReportModel(shares.getStockName(),language);
        Class clzss= null;
        try {
            clzss=doc.getClass();
            Field field[]=clzss.getDeclaredFields();
            for(Field f:field){
                String fieldName=f.getName();

                if(StringUtils.isNotBlank(fieldName)&&fieldName.endsWith("Model")){
                    String setMethodName="set"+StringUtils.upcaseFirstChar(fieldName);
                    String moduleName=fieldName.substring(0,fieldName.length()-5);
                    Class childField=Class.forName(DTO_PACK+"."+StringUtils.upcaseFirstChar(fieldName));
                    Method setMethod=clzss.getDeclaredMethod(setMethodName,childField);
                    // Constructor con=childField.getConstructor();
                    Object obj=childField.newInstance();
                    // BaseModel mod=(BaseModel)obj;
                    Method init=childField.getDeclaredMethod("initModel",String.class,List.class,String.class,Shares.class);
                    init.invoke(obj,language,shareNatureList,moduleName,shares);
                    //mod.initModel(language,shareNatureList,moduleName,stockService);
                    setMethod.invoke(doc,obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
    /**??????????????????????????????
     *
     * @param language
     * @param shares
     * @param modelName
     * @return
     */
    public Map<String,String> getShareNatureByLanguage(String language, List<ShareNature> shares,String modelName){

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


    /**??????????????????
     *
     * @param language
     * @param fields
     * @param modelName
     * @return
     */
    public Map<String,String> getShareTitle(String language, List<ShareNature> fields,String modelName){

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
     * ??????????????????
     * @param shareColum
     * @param shares
     * @return
     */
    public Map<String,String> getShareDataByNature(Map<String,String> shareColum, Shares shares){

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
//            for (Map.Entry<String,String> entry: shareColum.entrySet()) {
//                String key=entry.getKey();
//                if(StringUtils.isBlank(shareData.get(key))){
//
//                }
//                if(StringUtils.isNotBlank(shareData.get(key))){
//                    map.put(key,shareData.get(key));
//                }
//            }
        }
        return map;

    }

    /**
     *
     */
    public void putImageByteToContext(Map imgByte, IContext context, IXDocReport report){
        FieldsMetadata fieldsMetadata = new FieldsMetadata();
        IImageProvider image = null;
        if (imgByte != null && !imgByte.isEmpty()) {
            for (Object key : imgByte.keySet()) {
                image = new ByteArrayImageProvider((byte[]) imgByte.get(key));
                fieldsMetadata.addFieldAsImage(key.toString());
                context.put(key.toString(), image);
            }
            fieldsMetadata.setBehaviour(NullImageBehaviour.KeepImageTemplate);
            report.setFieldsMetadata(fieldsMetadata);
        }
    }

    /**
     * ????????????????????????
     * @param html
     * @param tradeData
     * @throws Exception
     */
    public void getData(String html, TradeData tradeData) throws Exception {
        //????????????????????????????????????

        //System.out.println(html.length()+"HTML??????");
        //System.out.println(html);
        ShareTradeDataModel shareModel = new ShareTradeDataModel();
        //??????Jsoup??????
        Document doc = Jsoup.parse(html);
        //System.out.println(doc.text() + "doc??????");
        //??????html??????????????????
        //Elements elements = doc.select("table[id=quotetab_stock]");
        Element quotetab_stock = doc.getElementById("quotetab_stock");
        //Elements rows=quotetab_stock.getElementsByTag("tr");

        // Element element=doc.firstElementSibling("table[id=quotetab_stock]");
        Element newest_element = doc.getElementById("last");
        tradeData.setNewestPrice(Double.parseDouble(StringUtils.keepNum(newest_element.text())));

        Elements tds = quotetab_stock.select("td");
        for (int i = 0; i < tds.size(); i++) {
            String text = tds.get(i).text();
//            System.out.println(text);
            if (StringUtils.isNotBlank(text)) {
                if (text.contains("??????")) {
                    tradeData.setStartPrice(Double.parseDouble(text.split(":")[1]));
                }
                if (text.contains("??????") && text.indexOf("??????") == 0) {
                    tradeData.setMaxPrice(Double.parseDouble(text.split(":")[1]));
                }
                if (text.contains("??????") && text.indexOf("??????") == 0) {
                    tradeData.setAmplitude(text.split(":")[1]);
                }
                if (text.contains("?????????") && text.indexOf("?????????") == 0) {
                    tradeData.setChangeHands(text.split(":")[1]);
                }
//
                if (text.contains("??????") && text.indexOf("??????") == 0) {
                    tradeData.setYesPrice(Double.parseDouble(text.split(":")[1]));
                }
                if (text.contains("??????") && text.indexOf("??????") == 0) {
                    tradeData.setMinPrice(Double.parseDouble(text.split(":")[1]));
                }
                if (text.contains("?????????") && text.indexOf("?????????") == 0) {
                    tradeData.setTradeHands(text.split(":")[1]);
                }
                if (text.contains("?????????") && text.indexOf("?????????") == 0) {
                    tradeData.setTradeSumMoney(text.split(":")[1]);
                }
                if (text.contains("?????????") && text.indexOf("?????????") == 0) {
                    String perate=text.split(":")[1];
                    if(StringUtils.isNotBlank(perate)&&StringUtils.isNum(perate)){
                        tradeData.setPeRatio(Double.parseDouble(StringUtils.removeUnit(text.split(":")[1])));
                    }
                }
                if (text.contains("??????????????????") && text.indexOf("??????????????????") == 0) {
                    String peRatioAfterDeduction=text.split(":")[1];
                    if(StringUtils.isNotBlank(peRatioAfterDeduction)&&StringUtils.isNum(peRatioAfterDeduction)){
                        tradeData.setPeRatioAfterDeduction(Double.parseDouble(StringUtils.removeUnit(text.split(":")[1])));
                    }
                }
                if (text.contains("?????????") && text.indexOf("?????????") == 0) {
                    tradeData.setPbRatio(Double.parseDouble(StringUtils.keepNum(text.split(":")[1])));
                }
                if (text.contains("????????????")) {
                    tradeData.setEarningsPerShare(StringUtils.keepNum(text.split("????????????")[1]) + "???");
                }
            }
        }

        tradeData.setPriceChange(StringUtils.minus(tradeData.getNewestPrice(), tradeData.getYesPrice()));
        if (tradeData.getYesPrice() != null) {
            double priceChange = tradeData.getPriceChange()/tradeData.getYesPrice()*100;
            tradeData.setPriceChangeRange(String.format("%.2f",priceChange)+"%");

        }

        Elements tdList = doc.select("td");

        for (Element e : tdList) {
            String indAndpe = e.text();
            if (StringUtils.isNotBlank(indAndpe) && indAndpe.contains("???????????????")) {

                int start = indAndpe.indexOf("???????????????");
                int end = indAndpe.indexOf("??????");

                String cut_str = indAndpe.substring(start, end).trim().replace("???????????????", "").replace("????????????????????????", "");
                String cut_array[] = cut_str.split("???");
                if (cut_array.length >= 3) {
                    tradeData.setIndustry(cut_array[0].replace("???????????????", ""));
                    tradeData.setAvgPeRatio(Double.parseDouble(StringUtils.keepNum(cut_array[1].trim())));
                    tradeData.setAvgPeRatioAfterDeduction(Double.parseDouble(StringUtils.keepNum(cut_array[2].trim())));
                }

                break;
            }
        }
        Elements rtables = doc.getElementsByClass("Rtable");//?????????
        //?????????????????????
        List<UpAndDownRanking> upAndDownRankings = new ArrayList<>();
        if (rtables != null) {
            Elements actEle = rtables.get(0).select("td");

            for (Element stockEle : actEle) {
                String indAndpe = stockEle.text();
                if (StringUtils.isNotBlank(indAndpe) && indAndpe.contains("?????????")) {
                    //System.out.println(stockEle.siblingElements().get(0).text());
                    tradeData.setTotalStocks(stockEle.siblingElements().get(0).text());
                }
                if (StringUtils.isNotBlank(indAndpe) && indAndpe.contains("????????????")) {
                    //System.out.println(stockEle.siblingElements().get(0).text());
                    tradeData.setCirculateStocks(stockEle.siblingElements().get(0).text());
                }
            }
            //??????
            if (rtables.get(1) != null) {
                Elements upelements = rtables.get(1).select("td");
                upelements.remove(0);

//                for (Element up : upelements) {
//                    String upstr = up.text();
//                    //System.out.println(upstr);
//
//                }
                List<List<Element>> upList = StringUtils.splitToPieces(upelements, 4);

                for (List<Element> elements : upList) {
                    UpAndDownRanking data = new UpAndDownRanking();
                    data.setMark("1");
                    data.setStockName(elements.get(0).text());
                    data.setCurrentPrice(Double.parseDouble(elements.get(1).text()));
                    data.setChangePrice(elements.get(2).text());
                    data.setUpAndDownRange(elements.get(3).text());
                    upAndDownRankings.add(data);
                }
            }

            //??????
            if (rtables.get(2) != null) {
                Elements downElements = rtables.get(2).select("td");
                downElements.remove(0);

                List<List<Element>> downList = StringUtils.splitToPieces(downElements, 4);

                for (List<Element> elements : downList) {
                    UpAndDownRanking data = new UpAndDownRanking();
                    data.setMark("2");
                    data.setStockName(elements.get(0).text());
                    data.setCurrentPrice(Double.parseDouble(elements.get(1).text()));
                    data.setChangePrice(elements.get(2).text());
                    data.setUpAndDownRange(elements.get(3).text());
                    upAndDownRankings.add(data);
                }
            }
        }
        shareModel.setUpAndDownRanking(upAndDownRankings);
        //????????????????????????
        tradeData.setData(JsonLibUtil.obj2Json(shareModel));

    }


    public void saveTradeData2Redis(TradeData tradeData){

        String stockCode=tradeData.getStockCode();
         String data= JSONObject.toJSONString(tradeData);
         this.stringRedisTemplate.opsForValue().set(stockCode,data);
         logger.info("================================???????????????"+stockCode+",??????redis??????=========================");
    }
    @Async
    public void mailSend(){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("================???????????????**************");

    }
}
