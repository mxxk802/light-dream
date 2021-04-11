package com.mxxk.lightdream.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * FileUtils
 *
 * @auther zhang
 * @date 2020/7/7
 **/

public class FileUtils {

    public static StringBuffer mergeUrl(String url,String ... value){

        StringBuffer buffer=new StringBuffer(url);
        if(value.length>0){
            for(int i=0;i<value.length;i++){
                if(StringUtils.isNotBlank(value[i])){
                    buffer.append("/");
                    buffer.append(value[i]);
                }
            }

        }
        return buffer;
    }

    private static Document read(File xmlFile) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(xmlFile);
    }
    public  int getDocPageSize(String filePath)  throws Exception {
        XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(filePath));
        int pages = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();//总页
        int wordCount = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getCharacters();// 忽略空格的字符另外还有getCharactersWithSpaces()方法获取带空格的总字数�?
        System.out.println ("pages=" + pages + " wordCount=" + wordCount);
        return pages;
    }


 }
