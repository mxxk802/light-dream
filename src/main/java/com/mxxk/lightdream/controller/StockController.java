package com.mxxk.lightdream.controller;

import com.alibaba.fastjson.JSON;
import com.mxxk.lightdream.config.ApiConfig;
import com.mxxk.lightdream.config.DocTemplate;
import com.mxxk.lightdream.config.PackConfig;
import com.mxxk.lightdream.dto.ShareReportModel;
import com.mxxk.lightdream.dto.ShareTradeDataModel;
import com.mxxk.lightdream.entity.*;
import com.mxxk.lightdream.entity.dic.DicStockType;
import com.mxxk.lightdream.mapper.ShareNatureMapper;
import com.mxxk.lightdream.mapper.SharesMapper;
import com.mxxk.lightdream.mapper.TradeDataMapper;
import com.mxxk.lightdream.service.*;
import com.mxxk.lightdream.utils.*;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;


/**
 * StockController
 *
 * @auther zhang
 * @date 2020/6/21
 **/
@Controller
@RequestMapping("/stock")
public class StockController {

    public static final String DTO_PACK = "com.mxxk.lightdream.dto";

    private Logger logger = LoggerFactory.getLogger(StockController.class);
    @Autowired
    private HttpClientUtils httpClientUtils;

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private PackConfig packConfig;

    @Autowired
    private ShareNatureMapper shareNatureMapper;

    @Autowired
    private SharesMapper sharesMapper;

    @Autowired
    private StockService stockService;

    @Autowired
    private DocTemplate docTemplate;

    @Autowired
    private XdocReportGenerateWord xdocReportGenerateWord;

    @Autowired
    private Dom4jGenerateXml dom4jGenerateXml;

    @Autowired
    private TradeDataMapper tradeDataMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    private TradeDataMapper tradeDateMapper;

    @RequestMapping(value = {"getStock", "getAllStocks"})
    public String getStock(Model model, HttpServletRequest request) {

        String pageIndexParam = request.getParameter("pageIndex");
        int pageIndex = 1;

        String returnView = "/shares/shareList";
        if (request.getServletPath().contains("getAllStocks")) {
            returnView = "/shares/stockList";
        }

        /**以下是通过接口调用来获取---暂时改为本系统自动连接数据库查询
         String url= FileUtils.mergeUrl(urlConfig.getStarUrl(),"stock","getAllStock").toString();
         String shareData=httpClientUtils.doGet(url);
         if(shareData==null){
         return "获取失败";
         }

         List<Shares> shares= JSON.parseArray(shareData,Shares.class);
         **/
        int pageSize = 10;
        List<Shares> shares = sharesMapper.selectAllShares();
        int totalRecord = 0;
        if (shares != null && shares.size() > 0) {
            totalRecord = shares.size();
        }
        if (StringUtils.isNotBlank(pageIndexParam)) {
            pageIndex = Integer.parseInt(pageIndexParam);
        }

        PageUtils<Shares> shareList = new PageUtils<Shares>(totalRecord, pageIndex, pageSize, shares);
        int pageCount = shareList.getPageCount();
        String onelevel = request.getParameter("onelevel");
        String twolevel = request.getParameter("twolevel");
        model.addAttribute("shareData", shareList.showData());
        model.addAttribute("onelevel", onelevel);
        model.addAttribute("twolevel", twolevel);
        model.addAttribute("totalRecord", totalRecord);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageIndex", pageIndex);
        return returnView;
    }

    @RequestMapping(value = "/new0")
    public String new0(Model model){

        List<DicStockType> dicstockType=dictionaryService.getDicAllDataMergeSource(DicStockType.class);
       // DicStockType d1=dictionaryService.getOneDicDataMergeSource(new DicStockType(),"1");
        model.addAttribute("dicstockType",dicstockType);

        return "/shares/new";
    }
    @RequestMapping(value="/saveBaseInfo",method = RequestMethod.POST)
    public String saveStockInfo(@Valid Shares shares,Model model){
        String path="redirect:/stock/getAllStocks";
        if(StringUtils.isBlank(shares.getStockCode())){
            return "redirect:/stock/new0";
        }

        Shares oldData=sharesMapper.selectByStockcode(shares.getStockCode());
        if(oldData!=null){
           sharesMapper.updateByPrimaryKey(shares);//如果有旧数据则更新
        }else{
            sharesMapper.insertSelective(shares);
        }
        //sharesMapper.insert(shares);

        return path;
    }

    @RequestMapping(value = "getDetail/{id:^\\d+$}")
    public String getDetail(@PathVariable("id") Integer id, Model model) {

//        String url=urlConfig.getStarUrl()+"/stock/getOneDetail/"+id;
//        String shareDataStr=httpClientUtils.doGet(url);
        /**1.通过接口调用获得
         *Shares shares= stockService.getShares(id);
         **/

        Shares shares = sharesMapper.selectByPrimaryKey(id);
        List<ShareNature> shareNatureList = shareNatureMapper.selectAllShareNature();
        String language = "CHINESE";
        ShareReportModel doc = new ShareReportModel(shares.getStockName(), language);
        doc = doc.getShareReportData(shares, language, shareNatureList, packConfig);//根据配置文件获取相关的各个要展示的模块内容
        //ShareReportModel doc=stockService.getShareReportData(shares,language,shareNatureList);

        //generateWord(shareShow,"D:\\tempword\\shareTemp.docx", "D:\\tempword\\t.docx");
        model.addAttribute("doc", doc);
        model.addAttribute("shares", shares);
        model.addAttribute("reportId", id);
        return "/shares/shareReport";
    }

    @RequestMapping(value = "createDoc/{id:^\\d+$}", method = RequestMethod.POST)
    @ResponseBody
    public String createDoc(@PathVariable("id") Integer id, HttpServletRequest request) {
        String message = "生成成功";
        String imgDataStr = request.getParameter("imgData");
        Map<String, Object> imgDataUrl = null;
        imgDataUrl = (HashMap) JSON.parseObject(imgDataStr, HashMap.class);
        //BASE64Decoder decoder = new BASE64Decoder();//这个可能
        try {
            if (imgDataUrl != null) {
                for (String key : imgDataUrl.keySet()) {
                    //byte[] b = decoder.decodeBuffer((imgDataUrl.get(key)).toString().substring(22));
                    byte[] b = Base64.decodeBase64((imgDataUrl.get(key)).toString().substring(22));
                    imgDataUrl.put(key.toString(), b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Shares shares = sharesMapper.selectByPrimaryKey(id);
        List<ShareNature> shareNatureList = shareNatureMapper.selectAllShareNature();

        String language = "CHINESE";

        /**
         * 老式接口的方法
         */
        //ShareReportModel doc=stockService.getShareReportData(shares,language,shareNatureList,stockService);

        /**
         *从本系统自己获取
         */

        String outDocfreemarker = PathUtils.word_Path + shares.getEntName() + "freemarker.docx";
        String outDocVelocity = PathUtils.word_Path + shares.getEntName() + "velocity.docx";
        String outXml=PathUtils.xml_path+shares.getEntName()+".xml";
        ShareReportModel doc = new ShareReportModel(shares.getStockName(), language);
        doc = doc.getShareReportData(shares, language, shareNatureList, packConfig);

        /**以下是生成word调用代码--使用freemarker**/
        xdocReportGenerateWord.createWordByFreeMarker(doc, StringUtils.formatFilePath(PathUtils.webAppPath + docTemplate.getStockFreemarkerTemplate()), outDocfreemarker, imgDataUrl);
        /**以下是生成word，velocity模板**/
        xdocReportGenerateWord.createWordByVelocity(doc, StringUtils.formatFilePath(PathUtils.webAppPath + docTemplate.getStockVelocityTemplate()), outDocVelocity, imgDataUrl);

        /**生成xml文件**/
        Dom4jGenerateXml.createStockDetailXml(doc,outXml);

        return message;
    }


    @RequestMapping(value = "tradeData/{stockCode}", method = RequestMethod.GET)
    public String grabTradeData(@PathVariable("stockCode") String stockCode) {


        String east_hqzx = "http://quote.cfi.cn/quote.aspx?actcode=&actstockid=&searchcode=" + stockCode + "&x=14&y=11";
        String east_hqzxData = httpClientUtils.doGet(east_hqzx);
        Shares shares = sharesMapper.selectByStockcode(stockCode);
        TradeData tradeData = new TradeData();
        tradeData.setStockCode(stockCode);
        tradeData.setStockName(shares.getStockName());
        tradeData.setStockDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));

        String sz_stockUrl="http://quote.cfi.cn/";
        if(stockCode.startsWith("0")){
            Document doc = Jsoup.parse(east_hqzxData);//如果是深市
            Element content=doc.getElementById("content");
            Elements elements=content.select("a");
            String key=shares.getStockName()+"("+shares.getStockCode()+")";

            for(Element e:elements){
                if(e.text().contains(key)){
                    String url=e.attr("href");
                    sz_stockUrl+=url;
                    System.out.println(url);
                    break;
                }
            }

        }

        try {
//            if(stockCode.startsWith("6")||stockCode.startsWith("3")){
//
//            }else{
//                String shenzhen_hqzxData = httpClientUtils.doGet(sz_stockUrl);
//                stockService.getData(shenzhen_hqzxData, tradeData);
//            }
            stockService.getData(east_hqzxData, tradeData);
        } catch (Exception e) {
            try {
                String shenzhen_hqzxData = httpClientUtils.doGet(sz_stockUrl);
                stockService.getData(shenzhen_hqzxData, tradeData);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.info("丛" + east_hqzx + "拉取数据失败，请查找原因");
        }
        TradeData oldData = tradeDataMapper.selectByStockCode(stockCode);
        if (oldData != null) {
            tradeData.setId(oldData.getId());

            tradeDataMapper.updateByPrimaryKeySelective(tradeData);
        } else {
            tradeDataMapper.insert(tradeData);
        }
        logger.info("========================查询并注入"+shares.getStockName()+"股票交易数据结束================================");

        stockService.saveTradeData2Redis(tradeData);

        String content="股票名称："+shares.getStockName()+",成功保存进mysql";

        //mailService.sendSimpleMail("919071573@qq.com","mxxk802@163.com","股票"+shares.getStockName()+"存入redis成功",content);//发送邮件采用异步（启动类上加上注解@EnableAsync）
        //mailService.sendSimpleMail("mxxk802@163.com","919071573@qq.com","股票"+shares.getStockName()+"存入redis成功",content);//发送邮件采用异步（启动类上加上注解@EnableAsync）

        return "redirect:/stock/getAllStocks";
    }

    @RequestMapping(value = "showTradeData/{stockCode}", method = RequestMethod.GET)
    public String showTradeData(@PathVariable("stockCode") String stockCode, Model model) {

        TradeData tradeData = tradeDataMapper.selectByStockCode(stockCode);

        ShareTradeDataModel tradeModel=new ShareTradeDataModel();
        List<UpAndDownRanking> upRank=new ArrayList<>();
        List<UpAndDownRanking> downRank=new ArrayList<>();

        if(tradeData.getData()!=null){
            tradeModel=FastJsonUtil.json2Obj(tradeData.getData(),ShareTradeDataModel.class);
            for(UpAndDownRanking upAndDownRanking:tradeModel.getUpAndDownRanking()){
                if("1".equals(upAndDownRanking.getMark())){
                    upRank.add(upAndDownRanking);
                }else{
                    downRank.add(upAndDownRanking);
                }
            }
        }

        model.addAttribute("data", tradeData);
        model.addAttribute("tradeModel", tradeModel.getUpAndDownRanking());

        return "/shares/TradeData";

    }



}
