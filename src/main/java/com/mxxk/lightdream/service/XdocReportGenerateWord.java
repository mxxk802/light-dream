package com.mxxk.lightdream.service;

import com.mxxk.lightdream.dto.ExportData;
import com.mxxk.lightdream.dto.ShareReportModel;
import com.mxxk.lightdream.utils.PathUtils;
import com.mxxk.lightdream.utils.WordUtils;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

/**
 * GenerateWord 我们使用xdoc生成word的时候有两个引擎可以选择，1.freeMarker2.velocity
 *
 * @auther zhang
 * @date 2020/12/19
 **/

@Service
public class XdocReportGenerateWord {

    private static Logger logger = LoggerFactory.getLogger(XdocReportGenerateWord.class);


    /** explain: 该方法我们使用freemarker模板引擎来生成work,由于占位符不同，我们需要不同的模板
     * @param doc
     * @param tmpPath
     * @param outputFileName
     */
    public void createWordByFreeMarker(ShareReportModel doc, String tmpPath, String outputFileName, Map imgByte) {

        InputStream input = null;
        OutputStream out = null;

        File tmpFile=new File(tmpPath);

        if(!tmpFile.exists()){
            System.out.println("通过freemarker生成stockword报告的模板不存在");
        }
        // 输出文件，文件存在则删除
        File outputFile = new File(outputFileName);
        // 文件夹不存在，创建所有文件夹
        File parentFile = outputFile.getParentFile();

        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (outputFile.exists()) {
            outputFile.delete();

        }

        try {
            input = new FileInputStream(tmpPath);

            ExportData exportData= WordUtils.createExportData(input,"freemarker");

 /*           FieldsMetadata metadata=report.createFieldsMetadata();

            metadata.load("doc",ShareReportModel.class,true);

            document.setFieldsMetadata(metadata);
            IContext context=report.createContext();
            putDetailModuleToContext(doc,context);//填入模块数据*/
            exportData.setData("doc",doc);
            exportData.setData("indexNo",1);
            exportData.putImageByteToContext(imgByte);//填入图片数据

            exportData.outPutReport(outputFileName);


//            Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
//            report.convert(context, options, out);//这个可以用来转换

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取模板异常！", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("word模板文字替换失败！", e);
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    /**
     * explain: 通过veloctiy引擎来实现
     * @param doc
     * @param tmpPath
     * @param outputFileName
     * @param imgByte
     */
    public void createWordByVelocity(ShareReportModel doc, String tmpPath, String outputFileName, Map imgByte){

        OutputStream out = null;
        InputStream in = null;

        // 输出文件，文件存在则删除
        File outputFile = new File(outputFileName);
        // 文件夹不存在，创建所有文件夹
        File parentFile = outputFile.getParentFile();

        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (outputFile.exists()) {
            outputFile.delete();

        }

        try {
            //用//倒入模版
            in = new FileInputStream(tmpPath);
            ExportData exportData=WordUtils.createExportData(in,"velocity");
            exportData.setData("doc",doc);

            //2.设置填充字段、填充类以及是否为list。
            exportData.setData("doc",doc);
            exportData.setData("indexNo",1);
            exportData.putImageByteToContext(imgByte);//填入图片数据

            exportData.outPutReport(outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
         finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.warn("文件流关闭失败", e);
            }
        }
        logger.info("word文档生成结束，");

    }

    /**
     * 将图片数据放入模板文件
     *
     * @param imgByte
     * @param context
     * @param report
     */

    public void putImageByteToContext(Map imgByte, IContext context, IXDocReport report) {
        FieldsMetadata fieldsMetadata = report.getFieldsMetadata();
        IImageProvider image = null;
        if (imgByte != null && !imgByte.isEmpty()) {
            for (Object key : imgByte.keySet()) {
                image = new ByteArrayImageProvider((byte[]) imgByte.get(key));
                fieldsMetadata.addFieldAsImage(key.toString());
                context.put(key.toString(), image);
            }
            fieldsMetadata.setBehaviour(NullImageBehaviour.KeepImageTemplate);
            //report.setFieldsMetadata(fieldsMetadata);
        }
    }


    public void createLaborContract(){
        String outDoc= PathUtils.word_Path+"myLabor.docx";


    }

}
