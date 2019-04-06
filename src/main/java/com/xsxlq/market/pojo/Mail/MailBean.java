package com.xsxlq.market.pojo.Mail;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装邮件接收者信息
 */
@Data
public class MailBean implements Serializable {
    private String recipient;
    private String subject;
    private String content;
}
