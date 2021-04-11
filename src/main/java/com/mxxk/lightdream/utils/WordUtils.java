package com.mxxk.lightdream.utils;

import com.mxxk.lightdream.dto.ExportData;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;


/**
 * WordUtils
 *
 * @auther zhang
 * @date 2020/7/29
 **/
public class WordUtils {
    private static Logger logger= LoggerFactory.getLogger(WordUtils.class);
    /**
     * 根据模板导出word文件
     *
     * @param params     ReportData对象为数据对象，里面存储Map 数据
     * @param templateName   模板文件路径
     * @param outputFileName 输出文件路径
     */
    public static void reportDoc(Map<String, Object> params, String templateName, String outputFileName) {
        //Map<String, Object> params = reportData.getParameters();
        InputStream in = null;
        OutputStream outputStream = null;
        IXDocReport report = null;
        try {
            // 1) Load ODT file and set Velocity template engine and cache it to the registry
            in = new FileInputStream(new File(templateName));

            // 2) Create Java model context
            IContext context = getReportContext(report, params);
            // 输出文件，文件存在则删除
            File outputFile = new File(outputFileName);
            // 文件夹不存在，创建所有文件夹
            File parentFile = outputFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (outputFile.exists()) {
                outputFile.renameTo(new File(outputFileName + "." + new Date().getTime()));
            }
            // 生成新的文件
            outputStream = new FileOutputStream(outputFileName);
            report.process(context, outputStream);
        } catch (IOException e) {
            logger.warn("文件流获取失败", e);
        } catch (XDocReportException e) {
            logger.warn("导出失败", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.warn("文件流关闭失败", e);
            }
        }
    }
    private static IContext getReportContext(IXDocReport report, Map<String, Object> params) throws XDocReportException {
        IContext context = null;
        if (report != null) {
            context = report.createContext();
            FieldsMetadata metadata = new FieldsMetadata();
            for (Iterator iterator = params.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String name = StringUtils.obj2Str(entry.getKey());
                Object value = entry.getValue();
                context.put(name, value);
            }
            report.setFieldsMetadata(metadata);
        }
        return context;
    }

    /**
     * 替换模板中的表达式，并转换为pdf格式输出
     * @param srcPath 模板文件 docx文件
     * @param destPath 输出文件 pdf文件
     * @param param 参数
     */
    public void wordToPDF(String srcPath, String destPath, Map<String, String> param) {
        File outFile = new File(destPath);
        InputStream is = null;
        OutputStream out = null;
        try {
            is = new FileInputStream(srcPath);
            logger.info(is.toString());
            // 通过填充Velocity模板引擎和缓存来加载Docx文件
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(is, TemplateEngineKind.Velocity);
            // 创建上下文Java模型
            IContext context = report.createContext();
            // 遍历参数
            for(Map.Entry<String, String> entry : param.entrySet()) {
                logger.info("遍历参数: " + entry.getKey() + " ---- " + entry.getValue());
                context.put(entry.getKey(), entry.getValue());
            }
            if(outFile.exists()) {
                outFile.delete();
            }
            // 通过将Java模型与Docx合并生成文档
            out = new FileOutputStream(outFile);
//			report.process(context, out);  // 直接输出word文档
            // 转换成PDF
            logger.info("开始转换成PDF");
            Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
            report.convert(context, options, out);
            logger.info("PDF转换完成");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取模板异常！", e);
        } catch (XDocReportException e) {
            e.printStackTrace();
            logger.error("word模板文字替换失败！", e);
        } finally {
            if(is != null) {
                try {
                    is.close();
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
     * 获取 Word 模板的两个操作对象 IXDocReport 和 IContext
     * @param src 模板相对于类路径的地址
     * @return 模板数据对象
     */
    public static ExportData createExportData(InputStream src,String engineName) {
        try {
            IXDocReport report = createReport(src,engineName);
            IContext context = report.createContext();
            return new ExportData(report, context);
        } catch (XDocReportException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 加载模板的方法，主要是指定模板的路径和选择渲染数据的模板
     * @param in 模板相对于类路径的地址
     * @return word 文档操作类
     */
    private static IXDocReport createReport(InputStream in,String engineName) {


        IXDocReport ix = null;
        try {

            if(!StringUtils.isBlank(engineName) && ExportData.FREEMAKER_NAME.equals(engineName)){
                ix = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
            }

            if (!StringUtils.isBlank(engineName) && ExportData.VELOCITY_NAME.equals(engineName)) {
                ix = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
        return ix;

    }


}
