package com.mxxk.lightdream.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mxxk.lightdream.entity.Video;
import com.mxxk.lightdream.mapper.VideoMapper;
import com.mxxk.lightdream.utils.FileUtils;
import com.mxxk.lightdream.utils.PageUtils;
import com.mxxk.lightdream.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * VideoController
 *
 * @auther zhang
 * @date 2020/8/19
 **/
@Controller
@RequestMapping(value="video")
public class VideoController {

    @Resource
    private VideoMapper videoMapper;


    @RequestMapping("/index")
    public String videoIndex(Model model){
        //Video video=videoMapper.selectByPrimaryKey(1);
        List<Video> videos=videoMapper.selectAllInfo();
        //JSONArray data= JSONArray.
        //List<Map> map=new ArrayList<>();
        JSONArray dataArray=new JSONArray();
        for(Video v:videos){
            JSONObject dataObject=new JSONObject();
            dataObject.put("title",v.getKeyWord());
            dataObject.put("result",v.getAddress());
            dataArray.add(dataObject);
        }
        model.addAttribute("videos",dataArray);

        return "/video/videoSearch";
    }

    /**
     * 新建页面
     * @return
     */
    @RequestMapping("/new0")
    public String new0(){
       String path="/video/new";
       return path;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(@Valid Video video,RedirectAttributes redirectAttrs,Model model){

        String path="/video/new";
        List<Video> allVideo=videoMapper.selectAllInfo();
        boolean exist=false;
        if(StringUtils.isNotBlank(video.getAddress())||StringUtils.isNotBlank(video.getKeyWord())){
             for(Video v:allVideo){
                if(video.getAddress().equals(v.getAddress())||video.getKeyWord().equals(v.getKeyWord())){
                    exist=true;
                    break;
                }
             }
        }
        if(!exist){
            videoMapper.insert(video);
            redirectAttrs.addAttribute("onelevel","辅助信息");
            redirectAttrs.addAttribute("twolevel","视频列表");
            path="redirect:/video/getAllVideo";
        }else{
            model.addAttribute("message","添加的信息有重复");
            redirectAttrs.addFlashAttribute("message","添加的信息有重复");
        }

        return path;
    }
    @RequestMapping("/edit/{id:^\\d+$}")
    public String edit(@PathVariable("id") Integer id,Model model,HttpServletRequest request){
        String path="/video/edit";

        Video video=videoMapper.selectByPrimaryKey(id);
        model.addAttribute("video",video);
         return path;

    }
    @RequestMapping("/update")
    public String updateVideo(@ModelAttribute("video") Video video,Model model,HttpServletRequest request){


        //String path="redirect:/video/edit/"+video.getId();
        String  path="redirect:/video/getAllVideo";
//        String onelevel=request.getParameter("onelevel");
//        String twolevel=request.getParameter("twolevel");
//        model.addAttribute("onelevel",onelevel);
//        model.addAttribute("twolevel",twolevel);
        videoMapper.updateByPrimaryKey(video);

       return path;
    }

    /**
     * 获取所有信息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/getAllVideo")
    public String getAllVideo(Model model, HttpServletRequest request){
        String pageIndexParam=request.getParameter("pageIndex");
        int pageIndex=1;

        String returnView="/video/videoList";


        int pageSize=10;

        List<Video> videos=videoMapper.selectAllInfo();
        int totalRecord=0;
        if(videos!=null&&videos.size()>0){
            totalRecord=videos.size();
        }
        if(StringUtils.isNotBlank(pageIndexParam)){
            pageIndex=Integer.parseInt(pageIndexParam);
        }

        PageUtils<Video> videoList=new PageUtils<Video>(totalRecord,pageIndex,pageSize,videos);
        int pageCount=videoList.getPageCount();
        String onelevel=request.getParameter("onelevel");
        String twolevel=request.getParameter("twolevel");
        model.addAttribute("onelevel",onelevel);
        model.addAttribute("twolevel",twolevel);
        model.addAttribute("totalRecord",totalRecord);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("videos",videos);
        return returnView;
    }

    @RequestMapping("/delete/{id:^\\d+$}")
    public String deleteOne(@PathVariable("id") Integer id,Model model){
       String  path="redirect:/video/getAllVideo";
        videoMapper.deleteByPrimaryKey(id);
        return path;

    }

}
