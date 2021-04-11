package com.mxxk.lightdream.controller;

import com.mxxk.lightdream.entity.User;
import com.mxxk.lightdream.utils.PathUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FreeMarkerController
 *
 * @auther zhang
 * @date 2020/12/21
 **/
@Controller
@RequestMapping("/freeMarker")
public class FreeMarkerController {


    /**
     * 根据模板生成相应的html
     * @return
     */
    @RequestMapping("/generateHtml")
    @ResponseBody
    public String index(){

        String message="通过freemarker生成html成功！";

        Configuration config=new Configuration();

        try {
            config.setDirectoryForTemplateLoading(new File(PathUtils.resourcePath+"static/ftl"));

            Template template=config.getTemplate("hello.ftl");

            Map map=new HashMap();
            map.put("hello","Hello FreeMarker!");
            map.put("name", "这是一段带有攻击性的字符串请点击");
            List<User> usrs=new ArrayList<>();
            User u1=new User();
            u1.setUsername("丁力");
            u1.setPassword("123");
            User u2=new User();
            u2.setUsername("许文强");
            u2.setPassword("345");
            usrs.add(u1);
            usrs.add(u2);
            map.put("userList",usrs);

            /**
            StringWriter out=new StringWriter();//内存输出流显示
            template.process(map,out);
            String result=out.toString();
            System.out.println(result);
             **/
            Writer writer=new FileWriter(new File(PathUtils.resourcePath+"static/ftl/hello.html"));
            Writer writerDoc=new FileWriter(new File(PathUtils.resourcePath+"static/ftl/hello.docx"));
            template.process(map,writer);
            template.process(map,writerDoc);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return message;

    }
}
