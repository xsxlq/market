package com.xsxlq.market.pojo;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class LoginLog {
    private Integer id;

    private Timestamp loginLogTime;

    //用于格式化传值
    private String strTime;
}