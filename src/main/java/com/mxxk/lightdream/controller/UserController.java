package com.mxxk.lightdream.controller;

import com.mxxk.lightdream.entity.User;
import com.mxxk.lightdream.mapper.UserMapper;
import com.mxxk.lightdream.service.UserService;
import com.mxxk.lightdream.utils.PageUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @RequestMapping(value="allUser")
    public String showAllUser(@RequestParam(value="pageIndex")int pageIndex, Model model){

        if(pageIndex==0){
            pageIndex=1;
        }
        int pageSize=10;

        List<User> allUser=userMapper.selectAllUser();
        int totalRecord=allUser.size();
        PageUtils<User> users=new PageUtils<User>(totalRecord,pageIndex,pageSize,allUser);
        int pageCount=users.getPageCount();
        model.addAttribute("allUser",users.showData());
        model.addAttribute("totalRecord",totalRecord);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("pageIndex",pageIndex);
        return "/userMng/userList";
    }

    @RequestMapping(value="exportUser")
    @ResponseBody
    public String exportUserList(){
        String result="success";
        List<User> allUser=userMapper.selectAllUser();
        userService.generateExcel(allUser,"userData");
        return result;
    }

    @RequestMapping("/{id}")
    @ResponseBody
    @Cacheable(value="loginUser")
    public User getUserById(@PathVariable int id){
        User user =userMapper.selectByPrimaryKey(id);
        System.out.println("查询数据库数据");
        return user;
    }



}
