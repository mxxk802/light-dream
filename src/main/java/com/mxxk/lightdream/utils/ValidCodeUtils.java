package com.mxxk.lightdream.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * ValidCodeUtils
 *
 * @auther zhang
 * @date 2021/2/1
 **/
public class ValidCodeUtils {


    private int w = 60;
    private int h = 25;
    private Random r = new Random();
    private String[] fontNames =
            { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
    // 可选的字符
    private String codes = "23456789abcdefghijkmnopqrstuvwxyzABCDEFGHIGKMNOPQRSTUVWXYZ";
    // 背景色
    private Color bgColor = new Color(255, 255, 255);
    private String text;


    // 返回背景颜色，150尽量不靠近白色
    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, blue, green);
    }


    // 返回随机字体，
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = r.nextInt(4);// 生成随机的样式 加粗 倾斜
        int size = r.nextInt(5) + 24;
        return new Font(fontName, style, size);
    }


    // 扰乱横线
    private void drawLine(BufferedImage image) {
        int num = 3;
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++)
        {
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h);
            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(Color.BLUE);
            g2.drawLine(x1, y1, x2, y2);


        }
    }


    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    //创建图片缓冲区，并得到空白图片
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(this.bgColor);
        g2.fillRect(0, 0, w, h);
        return image;
    }


    // 得到验证码图片
    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        // 用来装载生成的验证码文本
        StringBuilder sb = new StringBuilder();
        // 向图片中画4个字符
        for (int i = 0; i < 4; i++)
        {
            String s = randomChar()+"";
            sb.append(s);
            float x = i * 1.0F * w / 4;//将4个字符分开显示
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s, x, h - 5);//4个字符的位置
        }
        this.text = sb.toString();
        drawLine(image);
        return image;
    }


    // 得到验证码的文本内容
    public String getText()
    {
        return text;
    }


    // 保存图片到指定的输出流
    public  void output(BufferedImage image, OutputStream out)
            throws IOException
    {
        ImageIO.write(image, "JEPG", out);
    }

}
