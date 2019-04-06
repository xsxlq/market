package com.xsxlq.market.pojo;

import lombok.Data;

/**
 * 商品表
 */
@Data
public class ShopGoods {
    private Integer goodsId;

    private String goodsName;

    private Double goodsPrice;

    private Integer goodsTypeId;

    private String goodsImg;

    private String goodsDesc;

    private Integer goodsExchange;

    private Integer goodsIntegral;

    private String goodsDetail;

    private String goodsIntroduct;

    private Integer goodsQuality;

    private Integer status;

    //做拓展存储使用

    //被收藏数
    private Integer allLoveNum = 0;

    //商品类型
    private ShopGoodsType shopGoodsType;
    //商品成色
    private ShopQuality shopQuality;

    //是否喜欢
    private Boolean isLove;

    //所属用户
    private UserGoods userGoods;

    //用于临时存储用户信息
    private ShopUser shopUser;

    //用于临时存储用户详细信息
    private ShopUserInfo shopUserInfo;


}