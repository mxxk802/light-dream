package com.mxxk.lightdream.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtils {

    public static final String HEITI = "黑体";

    /**
     * 设置excel 每一列的宽度
     *
     * @param columnSize
     * @param sheet
     * @return
     */
    public static HSSFSheet setTileColumn(int columnSize[], HSSFSheet sheet) {

        for (int i = 0; i < columnSize.length; i++) {

            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    sheet.setColumnWidth(i, columnSize[j] * 256);//
                }
            }
        }
        return sheet;
    }

    public static HSSFCellStyle setCellStyle(HSSFWorkbook book,short align,short vertAlign,short bgcolor,short fp) {
        HSSFCellStyle style = book.createCellStyle();
        style.setAlignment(align);

        style.setVerticalAlignment(vertAlign);

        style.setFillForegroundColor(bgcolor);

        style.setFillPattern(fp);
        return style;

    }

    /**
     * 设置字体样式
     *
     * @param book
     * @param boldWeight
     * @param name
     * @param fontSize
     * @return
     */
    public static HSSFFont setFontStyle(HSSFWorkbook book, short boldWeight, String name, short fontSize) {
        // 创建字体样式
        HSSFFont font = (HSSFFont) book.createFont();
        // 字体加粗
        font.setBoldweight(boldWeight);
        //设置字体类型
        font.setFontName(name);
        //设置字体大小
        font.setFontHeightInPoints(fontSize);
        return font;
    }


}
