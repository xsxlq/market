package com.xsxlq.market.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class UserGoods {
    private Integer userId;

    private Integer goodsId;

    private Date addTime;

    private Integer status;

    //临时记录
    private String addTimeStr;

}