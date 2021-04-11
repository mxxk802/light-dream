package com.mxxk.lightdream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * VueController
 *
 * @auther zhang
 * @date 2021/3/12
 **/
@Controller
public class VueController {

    @RequestMapping("/vue")
    public String show(Model model){

        return "vuetest";
    }

}
