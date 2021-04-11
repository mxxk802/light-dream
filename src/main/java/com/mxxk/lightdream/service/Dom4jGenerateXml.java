package com.mxxk.lightdream.service;

import com.alibaba.fastjson.JSON;
import com.mxxk.lightdream.dto.ShareReportModel;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Dom4jGenerateXml
 *
 * @auther zhang
 * @date 2021/3/4
 **/
@Service
public class Dom4jGenerateXml {


    /**
     * 解析远程 XML 文件
     *
     * @param url
     * @return
     * @throws DocumentException
     */
    public static Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }

    /**
     * 创建一个 xml 文档
     */
    public static Document createDocument() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        Element author1 = root.addElement("author")
                .addAttribute("name", "James")
                .addAttribute("location", "UK")
                .addText("James Strachan");

        Element author2 = root.addElement("author")
                .addAttribute("name", "Bob")
                .addAttribute("location", "US")
                .addText("Bob McWhirter");

        return document;
    }

    public static void createStockDetailXml(ShareReportModel doc,String tmpPath) {

        Document document=DocumentHelper.createDocument();

        try {
            Element stockRoot=document.addElement("stockRoot");

            Map map = JSON.parseObject(JSON.toJSONString(doc), Map.class);

            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");

            FileWriter writer = new FileWriter(new File(tmpPath));

            XMLWriter xmlWriter=new XMLWriter(writer,format);

            xmlWriter.write(document);

            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 格式化文档
     *
     * @param fileName
     * @return
     */
    public int formatXmlFile(String fileName) {
        int returnValue = 0;

        try {
            SAXReader saxReader = new SAXReader();

            Document doc = saxReader.read(new FileReader(fileName));

            XMLWriter xmlWriter = null;

            /**格式化输出**/
            OutputFormat out = OutputFormat.createPrettyPrint();

            /** 指定XML编码 */
            out.setEncoding("UTF-8");

            xmlWriter = new XMLWriter(new FileWriter(new File(fileName)), out);
            xmlWriter.write(doc);
            xmlWriter.close();
            returnValue = 1;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 执行成功,需返回1 */

        return returnValue;

    }

    /* 修改XML文件中内容,并另存为一个新文件
  * 重点掌握dom4j中如何添加节点,修改节点,删除节点
  * @param filename 修改对象文件
  * @param newfilename 修改后另存为该文件
  * @return 返回操作结果, 0表失败, 1表成功
  */
    public int ModiXMLFile(String filename, String newfilename) {
        int returnValue = 0;
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(filename));
/**
 * 如果u9vip 节点中id属性的内容为1,则修改成id+1
 * 先用xpath查找对象
 * 
 * */

            List list = document.selectNodes("/u9vip/class/@id");
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                if (attribute.getValue().equals("1")) {
                    int i = Integer.parseInt(attribute.getValue()) + 1;
                    attribute.setValue("" + i);
                }
            }

/**
 * 把author项内容改为admin，
 * 并添加demo节点,demo节点的内容为www.u9vip.com ,还为demo节点添加一个属性date 2014-01-14
 */
            list = document.selectNodes("/u9vip/author");
            iter = list.iterator();
            if (iter.hasNext()) {
                Element authorElement = (Element) iter.next();
                authorElement.setText("admin");
                Element demoElement = authorElement.addElement("demo");
                demoElement.setText("www.u9vip.com");
                demoElement.addAttribute("date", "2014-01-14");
            }

/** 修改内容之三: 若title内容为Dom4j Tutorials,则删除该节点 */
            list = document.selectNodes("/u9vip/class");
            iter = list.iterator();
            while (iter.hasNext()) {
                Element bookElement = (Element) iter.next();
                Iterator iterator = bookElement.elementIterator("title");
                while (iterator.hasNext()) {
                    Element titleElement = (Element) iterator.next();
                    if (titleElement.getText().equals("Dom4j Tutorials")) {
                        bookElement.remove(titleElement);
                    }
                }
            }

            try {
/** 将document中的内容写入文件中 */
                XMLWriter writer = new XMLWriter(new FileWriter(new File(newfilename)));
                writer.write(document);
                writer.close();
/** 执行成功,需返回1 */
                returnValue = 1;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }
}

