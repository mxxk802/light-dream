package com.mxxk.lightdream.controller;

import com.mxxk.lightdream.entity.User;
import com.mxxk.lightdream.mapper.UserMapper;
import com.mxxk.lightdream.utils.PathUtils;
import com.mxxk.lightdream.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

@Controller
@RequestMapping("/mxxk")
public class LoginController {

    private static Logger logger= LoggerFactory.getLogger(LoginController.class);

    // 可选的字符
    private String codes = "0123456789abcdefghijkmnopqrstuvwxyzABCDEFGHIGKMNOPQRSTUVWXYZ";

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/login")
    public String ShowLoginPage(HttpServletRequest request,HttpServletResponse response){
        generateValidate(request,response);
        return "/login";
    }

    @RequestMapping("/createCode")
    public String createValidate(HttpServletRequest request,HttpServletResponse response){
        generateValidate(request,response);

        return "redirect:/mxxk/login";
    }

    @RequestMapping("/index")
    public String showIndexPage(@RequestParam String userName,@RequestParam String password,@RequestParam String validCode,Model mv,HttpSession session){
        //String code=session.getAttribute("randomCode").toString();
        String path="login";
        String message="用户名密码不正确请重新输入！";
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)) return "redirect:/mxxk/login";
        User loginingUser=userMapper.selectByPrimaryKey(1);
        if(userName.equals(loginingUser.getUsername())&&password.equals(loginingUser.getPassword())){
            message="登录成功!";
            mv.addAttribute("admin",loginingUser);

            return "index";
        }
        mv.addAttribute("message",message);

        return path;
    }

    public void generateValidate(HttpServletRequest request, HttpServletResponse response){

        String validPath=PathUtils.resourcePath+"/static/image/login/validate.jpg";
        File validFile=new File(validPath);
//        if(validFile.exists()){
//           validFile.delete();
//        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int width = 65;//定义图片的宽度
        int height = 24;//定义图片的高度
        // 创建具有可访问图像数据缓冲区的Image
        BufferedImage buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 创建一个随机数生成器
        Random random = new Random();
        //将图像填充为 白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        // 设置字体
        g.setFont(font);

        // 画边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width-1 , height-1);

        // 随机产生160条干扰线 ,使图像中的验证码不易被其它程序探测到
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 160; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        // randomCode 用于保存随机产生的验证码  ，以便用户登陆后进行验证
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 随机产生4位数字的验证码
        for (int i = 0; i < 4 ; i++) {
            // 得到随机产生的验证码数字
            //String strRand = String.valueOf(random.nextInt(10));
            //得到数字字母或者字母
            String strRand = String.valueOf(randomChar(random));

            // 产生随机的颜色分量来构造颜色值 ，这样输出的每位数字的颜色值都将不同
            red = random.nextInt(110);
            green = random.nextInt(50);
            blue = random.nextInt(50);

            // 用随机产生的颜色将验证码绘制到图像中
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, 13 * i + 6, 16);
            //将产生的四个随机数组合在一起
            randomCode.append(strRand);
        }

        // 将四位数字的验证码保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("randomCode", randomCode.toString());

        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setContentType("image/jpeg");
        // 将图像输出到servlet输出流中
       // ServletOutputStream sos = response.getOutputStream();
        FileOutputStream out= null;
        try {
            out = new FileOutputStream(validPath);
            ImageIO.write(buffImg, "jpeg", out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private char randomChar(Random random ) {
        int index = random.nextInt(codes.length());
        return codes.charAt(index);
    }

}
