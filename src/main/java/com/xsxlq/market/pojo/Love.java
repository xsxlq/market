package com.xsxlq.market.pojo;

import lombok.Data;

@Data
public class Love {
    private Integer userId;

    private Integer goodsId;

    //用于拓展，首页展示用
    private Integer num;

}