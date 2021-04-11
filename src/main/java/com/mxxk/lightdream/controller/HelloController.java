package com.mxxk.lightdream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value="hello")
public class HelloController {


    @RequestMapping(value="/show")
    @ResponseBody
    public Map<String,String> hello(){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("id","1111111111");
        return map;
    }

    @RequestMapping(value="/valid")
    public String showVliad(){
        String path="/validate";
        return path;
    }

}
