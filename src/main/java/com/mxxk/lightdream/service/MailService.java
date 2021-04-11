package com.mxxk.lightdream.service;

import com.mxxk.lightdream.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * MailService
 *
 * @auther zhang
 * @date 2021/1/27
 **/
@Service
public class MailService {

    private static Logger logger= LoggerFactory.getLogger(MailService.class);

    @Value("${spring.mail.username}")
    private String sendName;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送普通邮件
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    @Async
    public void sendSimpleMail(String from,String to,String subject,String content){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        if(StringUtils.isBlank(from)){
            from=sendName;
        }
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        //simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            e.printStackTrace();
            logger.info("邮件发送失败！");
        }
    }

    /**
     * 代附件的邮件
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param file
     */
    public void sendAttachFileMail(String from, String to, String subject, String content, File file) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            mimeMessageHelper.addAttachment(file.getName(),file);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 带图片的邮件
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param srePAth
     * @param resIds
     */
    public void sendMailWithImg(String from,String to,String subject,String content,String [] srePAth,String[]resIds){
        if (srePAth.length!=resIds.length){
            System.out.println("发送失败");
            return;
        }
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);
            for (int i = 0; i < srePAth.length; i++) {
                FileSystemResource res = new FileSystemResource(new File(srePAth[i]));
                mimeMessageHelper.addInline(resIds[i],res);
            }
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用thymeleaf模板
     * @param from
     * @param to
     * @param subject
     * @param context
     */
    public void sendHtmlMail(String from,String to ,String subject,String context){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(context);
            mimeMessageHelper.setSubject(subject);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
