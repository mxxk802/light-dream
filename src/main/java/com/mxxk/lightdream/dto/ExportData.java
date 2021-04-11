package com.mxxk.lightdream.dto;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * ExportData 封装IXdocReport 的数据
 *
 * @auther zhang
 * @date 2020/12/23
 **/

public class ExportData {

    public static String FREEMAKER_NAME="freemarker";
    public static String VELOCITY_NAME="velocity";

    private IXDocReport report;
    private IContext context;



    /**
     * 构造方法
     * @param report
     * @param context
     */
    public ExportData(IXDocReport report, IContext context) {
        this.report = report;
        this.context = context;
        if(report!=null){
            this.report.setFieldsMetadata(this.report.createFieldsMetadata());
        }


    }

    /**
     * 设置普通数据，包括基础数据类型，数组，试题对象
     * 使用时，直接 ${key.k} 或者 [#list d as key]
     * @param key   健
     * @param value 值
     */
    public void setData(String key, Object value) {
        context.put(key, value);
    }

    /**
     * 设置表格数据，用来循环生成表格的 List 数据
     * 使用时，直接 ${key.k}
     * @param key   健
     * @param maps List 集合
     */
    public void setTable(String key, List<Map<String,Object>> maps) {
        FieldsMetadata metadata=this.report.getFieldsMetadata();
        Map<String,Object> map = maps.get(0);
        for (String kk : map.keySet()) {
            metadata.addFieldAsList(key + "." + kk);
        }
        report.setFieldsMetadata(metadata);
        context.put(key, maps);
    }

    /**
     * 设置图片数据
     * 使用时 直接在书签出 key
     * @param key 健
     * @param url 图片地址
     */
    public void setImg(String key, String url) {
        FieldsMetadata metadata = report.getFieldsMetadata();
        metadata = metadata == null ? new FieldsMetadata() : metadata;
        metadata.addFieldAsImage(key);
        report.setFieldsMetadata(metadata);
        try (
                InputStream in = new ClassPathResource(url).getInputStream();
        ) {
            IImageProvider img = new ByteArrayImageProvider(in);
            context.put(key, img);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 将图片数据放入模板文件
     *
     * @param imgByte
     */

    public void putImageByteToContext(Map imgByte) {
        FieldsMetadata fieldsMetadata =this.report.getFieldsMetadata();
        IImageProvider image = null;
        if (imgByte != null && !imgByte.isEmpty()) {
            for (Object key : imgByte.keySet()) {
                image = new ByteArrayImageProvider((byte[]) imgByte.get(key));
                fieldsMetadata.addFieldAsImage(key.toString());
                this.context.put(key.toString(), image);
            }
            fieldsMetadata.setBehaviour(NullImageBehaviour.KeepImageTemplate);
            report.setFieldsMetadata(fieldsMetadata);
        }
    }

    /**
     *  设置metadata值
     * @param name
     * @param clzss
     * @param isList
     */
    public void putDataToFieldMeta(String name,Class clzss,boolean isList){

        FieldsMetadata fieldsMetadata =this.report.getFieldsMetadata();

        try {
            fieldsMetadata.load(name,clzss,isList);
        } catch (XDocReportException e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取文件流数据
     * @return 文件流数组
     */
    public byte[] getByteArr() {
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            report.process(context, out);
            return out.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 输出文件到目录
     * @param targetDir
     */
    public void outPutReport(String targetDir){

        try {
            OutputStream out = new FileOutputStream(targetDir);
            this.report.process(this.context,out);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }

    }

    /**
     * 关闭流
     */
    public void closeStream(InputStream in,OutputStream out){

        if(in != null) {
            try {
                in.close();
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
    /**将word模板内容转化为pdf**/
    public void wordToPdf(String targetName){
// 3) Set PDF as format converter
        Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);;

        try {
            OutputStream out = new FileOutputStream(new File(targetName));
            report.convert(context,options,out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
