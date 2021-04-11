package com.mxxk.lightdream.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;

/**
 * Created by liguoxiang on 2015/6/29.
 */
public class PathUtils {
    private static Logger logger= LoggerFactory.getLogger(PathUtils.class);
    private PathUtils() {
    }
    public static String workPath;
    public static String webRootPath;
    public static String webAppPath;
    public static String downloadPath;
    public static String templatePath;
    public static String classesPath;
    public static String resourcePath;

    //生成文档路径
    public static String word_Path;
    public static String pdf_path;
    public static String excel_path;
    public static String xml_path;
    public static String txt_path;

    static {

        try {
            webRootPath = (new File("")).getAbsolutePath()+File.separator;
            webRootPath = URLDecoder.decode(webRootPath, "UTF-8");
            //classesPath=ResourceUtils.getURL("classpath:").getPath();
            classesPath= Thread.currentThread().getContextClassLoader().getResource("").getPath();

        } catch (Exception e) {

            logger.info("核心工具类PathUtils出错,系统启动失败");
            e.printStackTrace();
        }

        webRootPath = webRootPath.replace("\\", "/");

        workPath = ((new File(webRootPath)).getParent() + "/").replaceAll("\\\\", "/");
        webAppPath=webRootPath+"src/main/webapp/";
        resourcePath=webRootPath+"src/main/resources/";
        downloadPath =webAppPath+"download/";
        templatePath=webAppPath+"template/";
        word_Path = downloadPath +"wordReport/";
        pdf_path = downloadPath + "pdfReport/";
        excel_path = downloadPath + "excelReport/";
        xml_path = downloadPath + "xmlReport/";
        txt_path = downloadPath + "txtReport/";



    }


    public static String replaceSeparator(String str) {
        return str.replace("\\", "/").replace("\\\\", "/");
    }

    /**
     * 获取项目根路径
     *
     * @return
     */
    private static String getResourceBasePath() {
        // 获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }

        String pathStr = path.getAbsolutePath();
        // 如果是在eclipse中运行，则和target同级目录,如果是jar部署到服务器，则默认和jar包同级
        pathStr = pathStr.replace("\\target\\classes", "").replace("\\","/");

        return pathStr;
    }

    public static void main(String[] args) {

        System.out.println(workPath);
        System.out.println(webRootPath);
        System.out.println(webAppPath);
        System.out.println(downloadPath);
        System.out.println(word_Path);
       System.out.println(classesPath);
       System.out.println(templatePath);
//        System.out.println(word_Path);
       // String url="E:\\workspacespringboot\\light-dream\\src\\main\\resources";
       // System.out.println(PathUtils.replaceSeparator(url));


        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
