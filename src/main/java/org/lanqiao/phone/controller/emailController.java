package org.lanqiao.phone.controller;

import org.lanqiao.phone.service.BackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
@Controller
public class emailController {
    @Autowired
    BackUserService service;
    @RequestMapping("emial/{id}")
    public String  senemial(@PathVariable int id ,String useremial ) throws MessagingException {
        System.out.println(id);
        Properties props = new Properties();
        //设置发送的协议
        props.setProperty("mail.transport.protocol", "smtp");
        //设置是否需要认证
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        session.setDebug(true);
        //设置要发送的消息
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("2466213746@qq.com"));//发送者的用户名，须和下面的connect方法中的用户名一致，不然要报错，但看教程里的并没有一致
        msg.setText("你的投诉无效，请不要无理取闹");

        //设置用于传输的类
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", 25, "2466213746@qq.com", "lvkhuaneuwtydigi");//发送者的用户名和密码
        service.SendEmailY( id );

        //发送邮件
        transport.sendMessage(msg, new Address[] { new InternetAddress(
                "1584539698@qq.com") });//接收者的邮箱
        transport.close();
       return "redirect:/tousu";

    }
@RequestMapping("shopemial/{id}")
public String  senshopemial(@PathVariable int id) throws MessagingException {
    Properties props = new Properties();
    //设置发送的协议
    props.setProperty("mail.transport.protocol", "smtp");
    //设置是否需要认证
    props.setProperty("mail.smtp.auth", "true");
    Session session = Session.getInstance(props);
    session.setDebug(true);
    //设置要发送的消息
    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress("2466213746@qq.com"));//发送者的用户名，须和下面的connect方法中的用户名一致，不然要报错，但看教程里的并没有一致
    msg.setText("有用户投诉，先对你们发出警告，请认真对待每一个客户");

    //设置用于传输的类
    Transport transport = session.getTransport();
    transport.connect("smtp.qq.com", 25, "2466213746@qq.com", "lvkhuaneuwtydigi");//发送者的用户名和密码
    service.SendEmailY( id );
    //发送邮件
    transport.sendMessage(msg, new Address[] { new InternetAddress(
            "1584539698@qq.com") });//接收者的邮箱
    transport.close();
    return "redirect:/tousu";

}
}
