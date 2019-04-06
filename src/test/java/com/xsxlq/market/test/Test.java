package com.xsxlq.market.test;

import com.xsxlq.market.pojo.Mail.MailBean;
import com.xsxlq.market.util.MailUtil;

import java.sql.Timestamp;
import java.util.Date;

public class Test {
    @org.junit.Test
    public void show() throws Exception {
        MailBean mailBean = new MailBean();
        mailBean.setContent("test");
        mailBean.setRecipient("3381295097@qq.com");
        mailBean.setSubject("Test");
    }
}
