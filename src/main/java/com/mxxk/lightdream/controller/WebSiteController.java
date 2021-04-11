package com.mxxk.lightdream.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mxxk.lightdream.entity.Video;
import com.mxxk.lightdream.entity.Webstie;
import com.mxxk.lightdream.mapper.WebsiteMapper;
import com.mxxk.lightdream.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * WebSiteController
 *
 * @auther zhang
 * @date 2020/8/26
 **/
@Controller
@RequestMapping(value="/website")
public class WebSiteController {

    @Resource
    private WebsiteMapper websiteMapper;

    @RequestMapping("/index")
    public String showIndex(Model model){
        String path="/website/siteSearch";
        List<Webstie> sites=websiteMapper.selectAllInfo();

        JSONArray dataArray=new JSONArray();
        for(Webstie v:sites){
            JSONObject dataObject=new JSONObject();
            dataObject.put("title",v.getKeyWord());
            dataObject.put("result",v.getAddress());
            dataArray.add(dataObject);
        }
        model.addAttribute("sites",dataArray);
        return path;
    }
    @RequestMapping("/new")
    public String new0(){
        String path="/website/new";
        return path;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(@Valid Webstie webstie, RedirectAttributes redirectAttrs, Model model){

        String path="/website/new";
        List<Webstie> allSites=websiteMapper.selectAllInfo();
        boolean exist=false;
        if(StringUtils.isNotBlank(webstie.getAddress())||StringUtils.isNotBlank(webstie.getKeyWord())){
            for(Webstie v:allSites){
                if(webstie.getAddress().equals(v.getAddress())||webstie.getKeyWord().equals(v.getKeyWord())){
                    exist=true;
                    break;
                }
            }
        }
        if(!exist){
            websiteMapper.insert(webstie);
            redirectAttrs.addAttribute("onelevel","辅助信息");
            redirectAttrs.addAttribute("twolevel","网址检索");
            path="redirect:/website/index";
        }else{
            model.addAttribute("message","添加的信息有重复");
            redirectAttrs.addFlashAttribute("message","添加的信息有重复");
        }
        return path;
    }

}
