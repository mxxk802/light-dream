package com.mxxk.lightdream.service;

import com.mxxk.lightdream.entity.User;
import com.mxxk.lightdream.utils.ExcelUtils;
import com.mxxk.lightdream.utils.PathUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private Logger logger= LoggerFactory.getLogger(UserService.class);

   // private final String excelPath = PathUtils.webRootPath + "/download/excelReport/";//存放word路径


    public void generateExcel(List<User> users,String sheetName){

        int columnWidth[]=new int[]{5,10,20,10,15,30};
        String fileName="AllUserList";
        //创建一个工作簿，对应一个excel文件
        HSSFWorkbook book=new HSSFWorkbook();
        //在book中创建一个sheet，对应excel中的一个sheet
        HSSFSheet sheet=book.createSheet(sheetName);

        int columnNumber=columnWidth.length;
        //设置每一列的列宽
        ExcelUtils.setTileColumn(columnWidth,sheet);

        HSSFRow titleRow=sheet.createRow(0);

        //创建标题单元格样式以及字体样式
        HSSFCellStyle style=ExcelUtils.setCellStyle(book,HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                HSSFColor.LIGHT_TURQUOISE.index,HSSFCellStyle.SOLID_FOREGROUND);

        // 创建字体样式
        HSSFFont titleFont = ExcelUtils.setFontStyle(book,HSSFFont.BOLDWEIGHT_BOLD,"黑体",(short) 15);

        style.setFont(titleFont);

        ArrayList<String> titleNames=new ArrayList<>();
        titleNames.add("序号");
        titleNames.add("用户名");
        titleNames.add("密码");
        titleNames.add("年龄");
        titleNames.add("电话");
        titleNames.add("邮箱");

        //设置标题
        for(int i=0;i<titleNames.size();i++){

           HSSFCell cell= titleRow.createCell(i);
           cell.setCellStyle(style);
           cell.setCellValue(titleNames.get(i));
        }
        int i=1;
        while(i<users.size()){
            HSSFRow dataRow=sheet.createRow(i);
            User user=users.get(i-1);
            HSSFCell cell1=dataRow.createCell(0);
            cell1.setCellValue(user.getId());

            HSSFCell cell2=dataRow.createCell(1);
            cell2.setCellValue(user.getUsername());

            HSSFCell cell3=dataRow.createCell(2);
            cell3.setCellValue(user.getPassword());

            HSSFCell cell4=dataRow.createCell(3);
            cell4.setCellValue(user.getAge());

            HSSFCell cell5=dataRow.createCell(4);
            cell5.setCellValue(user.getTel());

            HSSFCell cell6=dataRow.createCell(5);
            cell6.setCellValue(user.getMail());

            i++;
        }

        //子目录
        String childPath="userInfo"+File.separator;

        File userFile=new File(PathUtils.excel_path+File.separator+childPath+fileName+".xls");

        //获取上级文件夹
        File parentFile = userFile.getParentFile();
        if(!parentFile.exists()&&!parentFile.isDirectory()){
            parentFile.mkdir();
        }

        FileOutputStream outputStream=null;
        try {
             outputStream= new FileOutputStream(userFile);
            book.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {

            }

        }

    }

}
