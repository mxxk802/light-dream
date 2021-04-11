<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Graphics2D"%>
<%@page import="java.awt.Color"%>
<%@page import="java.awt.Font"%>
<%@page import="javax.imageio.ImageIO"%>.
<%@page import="java.util.Random"%>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="com.mxxk.lightdream.utils.PathUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>验证码</title>
</head>
<body>
<%
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
        String strRand = String.valueOf(random.nextInt(10));

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
    //HttpSession session = request.getSession();
    session.setAttribute("randomCode", randomCode.toString());

    // 禁止图像缓存
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    response.setContentType("image/jpeg");
    // 将图像输出到servlet输出流中
    ServletOutputStream sos = response.getOutputStream();
    FileOutputStream fout=new FileOutputStream(PathUtils.resourcePath+"/static/image/login/validate.jpg");
    ImageIO.write(buffImg, "jpeg", fout);
    sos.close();
    //sos = null;
    out.clear();
    fout.close();
    out = pageContext.pushBody();
%>
</body>
</html>
