package com.xsxlq.market.controller;

import com.xsxlq.market.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MailUtil mailUtil;
    @PostMapping("/sendMail")
    public String sendMial(String recipient){
        logger.debug(recipient);
        try {
            String subject = "【验证码】";
            String content = "【二手市场】您的验证码为："+System.currentTimeMillis()+"，有效期为5分钟。";
            mailUtil.sendText(recipient,subject,content);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
        return "ok";
    }
}
