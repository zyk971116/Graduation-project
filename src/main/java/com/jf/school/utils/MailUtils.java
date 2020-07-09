package com.jf.school.utils;

import com.jf.school.bean.table.Order;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtils {
    public static void sendMail(Order order,String name,Long tel) throws Exception {
        String from = "zyk971116@qq.com"; //发件人的邮箱地址
        String to = "1987397966@qq.com";    //收件人的邮箱地址
        String subject = "甲煤矿企业订单申请";    //邮件主题
        ;
        String body = "    我公司现向贵公司购买"+AnswerUtils.getDisplayAnswer(order.getQuality()) +
                order.getName() + order.getTotal()+"吨"+ '\n'+
                "      如有问题请联系: " + name +'\n'+"          电话: "+tel;    //邮件内容

        //设置发送邮件的一些属性
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.host", "smtp.qq.com");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.debug", "true");
        prop.setProperty("mail.smtp.ssl.enable", "true"); //qq邮箱必须设置这一项，ssl加密选项


        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //这里需要验证邮箱的授权码，QQ邮箱需要授权码
                return new PasswordAuthentication(from, "cgqqowbbnjohdcbj");
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            Transport transport = session.getTransport();
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSentDate(new Date());
            message.setSubject(subject);
            //如果仅仅是发送文本，使用setText()方法
            message.setText(body);

            //如果是发送html格式的邮件则需要使用setContent()方法，并且设置参数text/html; 这两个方法是等价的,后面执行的会覆盖前一个方法设置的内容
//            message.setContent("<h1>This is HTML message</h1>","text/html" );
            message.saveChanges();
            session.setDebug(true);
            transport.connect(from, "cgqqowbbnjohdcbj");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
