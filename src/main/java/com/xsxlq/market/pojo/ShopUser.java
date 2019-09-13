package com.xsxlq.market.pojo;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ShopUser {
    private Integer id;

    private String name;

    private String phone;

    private String pwd;

    private String email;

    private Timestamp registertime;

    private Timestamp logintime;

    private Integer status;

    private String code;

    private String perms;

    private String strLogTime;

    private String strRegTime;

    private List<LoginLog> loginLogs;

    private Integer loveMsg;

    private Integer sayMsg;

}