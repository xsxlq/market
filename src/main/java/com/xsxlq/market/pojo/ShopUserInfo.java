package com.xsxlq.market.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class ShopUserInfo {
    private Integer id;

    private String realName;

    private Date birthday;

    private String idCard;

    private Integer gender;

    private String code;

    private String xy;

    private String profession;

    private String userClass;

    private String icon;

    private String status;

    private String school;

    private ShopUser shopUser;

}