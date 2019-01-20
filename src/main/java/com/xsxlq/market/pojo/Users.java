package com.xsxlq.market.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Users {
    private Integer userId;

    private String userName;

    private String userPwd;

    private String userEmail;

    private String userImageUrl;

    private String userNickName;

    private String userSays;

    private String userPhone;

    private Byte userSex;

    private String userQq;

    private String userAddress;

    private String userLastLoginIp;

    private Date userBirthday;

    private String userDescription;

    private String userSchool;

    private Date userRegisterTime;

    private String userRegisterIp;

    private Byte userLock;

    private Byte userFreeze;

    private Byte userRank;

}