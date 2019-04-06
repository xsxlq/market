package com.xsxlq.market.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class EmailInfo {
    private Integer id;

    private String content;

    private String recipient;

    private Date t;

    private Integer status;

}