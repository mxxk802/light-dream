package com.mxxk.lightdream;

import com.mxxk.LightDreamApplication;
import com.mxxk.lightdream.dto.ExportData;
import com.mxxk.lightdream.entity.Employee;
import com.mxxk.lightdream.entity.Project;
import com.mxxk.lightdream.service.XdocReportGenerateWord;
import com.mxxk.lightdream.utils.PathUtils;
import com.mxxk.lightdream.utils.StringUtils;
import com.mxxk.lightdream.utils.WordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * VelocityTest
 *
 * @auther zhang
 * @date 2020/12/26
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= LightDreamApplication.class)
public class VelocityTest {

    @Autowired
    private XdocReportGenerateWord xdoc;


    @Test
    public void createDoc(){
        String src= StringUtils.formatFilePath(PathUtils.templatePath+"velocityTest.docx");
        try {
            InputStream in=new FileInputStream(src);
            ExportData exportData= WordUtils.createExportData(in,"velocity");


            Employee e1=new Employee("张三","医生","病了");
            Employee e2=new Employee("李四","老师","上课");

            Project p1=new Project("感冒","买药了");
            Project p2=new Project("教学","背教案");

            List<Project> projects=new ArrayList<>();
            projects.add(p1);
            projects.add(p2);

            List<String> list=new ArrayList<>();
            list.add("情深深");
            list.add("雨蒙蒙");
            list.add("多少楼台");
            list.add("烟雨中");

            Map<String,Employee> map=new HashMap<>();

            map.put("e1",e1);
            map.put("e2",e2);

            Map<String,String> map2=new HashMap<>();
            map2.put("hi","哈哈");
            map2.put("en","恩恩额");

            exportData.setData("title","velocity制作word简述");
            exportData.setData("one","基本数据标签");
            exportData.setData("two","列表数据标签");
            exportData.setData("pro",projects);
            exportData.setData("emp",e1);
            exportData.setData("map",map);
            exportData.setData("map2",map2);
            exportData.setData("song",list);
            exportData.setData("indexNo",1);
            exportData.setData("start",new Date());
            exportData.setData("isNull",true);
            exportData.setData("str","jingtian");


            // exportData.putDataToFieldMeta("song",String.class,true);

            String outDoc=PathUtils.word_Path+"velocityResult.docx";

            exportData.outPutReport(outDoc);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
