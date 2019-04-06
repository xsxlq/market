package com.xsxlq.market.util;

import com.xsxlq.market.pojo.EmailInfo;
import com.xsxlq.market.pojo.Mail.MailBean;
import com.xsxlq.market.service.EmailInfoService;
import com.xsxlq.market.service.impl.EmailInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件
 */
@Component
public class MailUtil {

    @Autowired
    private EmailInfoService emailInfoService;

    static Logger logger;

    static {
        logger = LoggerFactory.getLogger(MailUtil.class);
    }

    private static JavaMailSenderImpl mailSender;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @PostConstruct
    public void init(){
        mailSender = javaMailSender;

    }

    /**
     * 发送包含简单文本的邮件(实体类参数)
     */
    public void sendText(MailBean mailBean) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人
        simpleMailMessage.setTo(mailBean.getRecipient());
        //寄件人
        simpleMailMessage.setFrom("614171641@qq.com");
        simpleMailMessage.setSubject(mailBean.getSubject());
        simpleMailMessage.setText(mailBean.getContent());
        // 发送邮件
        mailSender.send(simpleMailMessage);
        logger.info("JavaMailUtil类提示:---邮件已发送---to("+mailBean.getRecipient()+")");

    }
    /**
     * 发送包含简单文本的邮件(字符串参数)
     */
    public void sendText(String recipient,String subject,String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人
        simpleMailMessage.setTo(recipient);
        //寄件人
        simpleMailMessage.setFrom("614171641@qq.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        // 发送邮件(可以设置管理员总邮件发送开关--建表判断)
//        mailSender.send(simpleMailMessage);
        //入表
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setRecipient(recipient);
        emailInfo.setContent("邮件标题:"+subject+"&邮件内容:"+content);
        emailInfoService.insertSelective(emailInfo);

        logger.info("JavaMailUtil类提示:---邮件已发送---to("+recipient+")");
    }

    /**
     * 发送包含简单html文本的邮件
     * @param title
     * @param text
     */
    public void sendHtml(String email,String title,String text) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        //发送到传进来的邮箱
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setFrom("614171641@qq.com");
        mimeMessageHelper.setSubject(title);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head><body>");
        sb.append(text);
        sb.append("</body></html>");
        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        mailSender.send(mimeMessage);

        logger.info("JavaMailUtil类提示:---邮件已发送---to("+email+")");
    }

    /**
     * 发送包含验证码的邮件
     */
    public void sendCode(String email,String code) throws Exception{
        //调用方法前传递随机生成的code
        code = "<h3>如果非本人操作，请忽略此验证码。</h3><p>验证码："+code+"</p><p>链接：**.com</p>";
        sendHtml(email,"验证码",code);
    }
}
